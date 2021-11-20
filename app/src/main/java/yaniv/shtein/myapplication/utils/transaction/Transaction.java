package yaniv.shtein.myapplication.utils.transaction;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import yaniv.shtein.myapplication.utils.currencies.CurrencyDataSource;

@Entity
public class Transaction {

    //properties:
    private String name;
    private float amount;
    private String currency;
    private double amountInCurrency;
    private String dateTime;
    private double conversionRate;

    //so that it is possible to have the same data (only id is different)
    @PrimaryKey(autoGenerate = true)
    private int id;

    //cnstr:
    public Transaction(String name, float amount, String currency) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        CurrencyDataSource currencyDataSource = new CurrencyDataSource();
        amountInCurrency = currencyDataSource.convertCurrency(currency, amount);
        conversionRate = currencyDataSource.getNewRate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        dateTime = formatter.format(date);
    }

    public Transaction() {
    }

    //getters/setters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmountInCurrency() {
        return amountInCurrency;
    }

    public void setAmountInCurrency(double amountInCurrency) {
        this.amountInCurrency = amountInCurrency;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //toString:
    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", id='" + id + '\'' +
                '}';
    }
}
