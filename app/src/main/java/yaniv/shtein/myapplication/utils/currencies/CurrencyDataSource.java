package yaniv.shtein.myapplication.utils.currencies;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;

public class CurrencyDataSource {
    private static final String ADDRESS = "http://data.fixer.io/api/latest?access_key=ebc94a7101457d96e2fe85103197b8c5";
    private static JSONObject ratesObject = new JSONObject();
    private double newRate;

    //fetch data from the api
    public void fetch(MutableLiveData<Currencies> callback, MutableLiveData<Exception> errCallback) {
        new Thread(() -> {
            try {
                URL url = new URL(ADDRESS);
                URLConnection con = url.openConnection();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    StringBuilder stringBuilder = new StringBuilder();

                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String json = stringBuilder.toString();

                    try {
                        Currencies currencies = parse(json);
                        callback.postValue(currencies);
                    } catch (JSONException e) {
                        errCallback.postValue(e);
                    }

                }

            } catch (IOException e) {
                errCallback.postValue(e);
            }
        }).start();


    }

    //parse the jsonObject
    public Currencies parse(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        String date = jsonObject.getString("date");
        setRatesObject(jsonObject.getJSONObject("rates"));


        Iterator<String> iterator = ratesObject.keys();
        HashMap<String, Double> rates = new HashMap<>();

        while (iterator.hasNext()) {
            String key = iterator.next();
            double value = ratesObject.getDouble(key);
            rates.put(key, value);
        }
        return new Currencies(date, rates);
    }

    //returns the amount of money after conversion to wanted currency
    public double convertCurrency(String to, double amount) {
        double newAmount = 0;
        try {
            double fromCurrency = ratesObject.getDouble("ILS");
            double toCurrency = ratesObject.getDouble(to);
            newRate = toCurrency / fromCurrency;

            newAmount = newRate * amount;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newAmount;
    }

    //setter
    public void setRatesObject(JSONObject ratesObject) {
        this.ratesObject = ratesObject;
    }

    //getter
    public double getNewRate() {
        return newRate;
    }

    //getter
    public JSONObject getRatesObject() {
        return ratesObject;
    }

    //check if the currency exists
    public boolean checkRate(String currency) {
        return getRatesObject().has(currency);
    }

}
