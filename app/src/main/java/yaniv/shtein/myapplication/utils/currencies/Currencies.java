package yaniv.shtein.myapplication.utils.currencies;

import androidx.room.Entity;

import java.util.HashMap;

@Entity
public class Currencies {

    //properties:
    private String date;
    private HashMap<String, Double> rates;

    //cnstr:
    public Currencies(String date, HashMap<String, Double> rates) {
        setDate(date);
        setRates(rates);
    }

    //getters/setters:
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Currencies{" +
                "date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
