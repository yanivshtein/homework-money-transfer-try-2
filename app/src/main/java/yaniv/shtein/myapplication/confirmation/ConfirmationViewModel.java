package yaniv.shtein.myapplication.confirmation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import yaniv.shtein.myapplication.utils.DBManager;
import yaniv.shtein.myapplication.utils.transaction.Transaction;

public class ConfirmationViewModel extends AndroidViewModel {
    public ConfirmationViewModel(@NonNull Application application) {
        super(application);
    }

    //save the transaction to the database
    public void addTransaction(String name, float amount, String currency) {
        new DBManager(getApplication()).insert(new Transaction(name, amount, currency));
    }
}
