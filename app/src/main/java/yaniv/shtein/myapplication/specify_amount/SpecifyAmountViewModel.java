package yaniv.shtein.myapplication.specify_amount;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import yaniv.shtein.myapplication.utils.SharedPrefs;
import yaniv.shtein.myapplication.utils.currencies.CurrencyDataSource;

public class SpecifyAmountViewModel extends AndroidViewModel {
    public SpecifyAmountViewModel(@NonNull Application application) {
        super(application);
    }

    //returns either -1 or the amount the user input
    public float getAmount(String etAmount) {
        return SharedPrefs.transaction(getApplication(), Float.parseFloat(etAmount));
    }

    //checks if the currency exists
    public boolean checkCurrency(String currency) {
        CurrencyDataSource currencyDataSource = new CurrencyDataSource();
        return currencyDataSource.checkRate(currency);
    }
}
