package yaniv.shtein.myapplication.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import yaniv.shtein.myapplication.utils.currencies.Currencies;
import yaniv.shtein.myapplication.utils.currencies.CurrencyDataSource;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<Currencies> currencies = new MutableLiveData<>();
    private MutableLiveData<Exception> exception = new MutableLiveData<>();

    public MainViewModel(Application application) {
        super(application);
        new CurrencyDataSource().fetch(currencies, exception);
    }
}
