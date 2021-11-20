package yaniv.shtein.myapplication.view_balance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import yaniv.shtein.myapplication.utils.SharedPrefs;

public class ViewBalanceViewModel extends AndroidViewModel {
    public ViewBalanceViewModel(@NonNull Application application) {
        super(application);
    }

    //returns the current balance
    public String getBalance() {
        return Float.toString(SharedPrefs.readBalance(getApplication())) + " ILS";
    }

}
