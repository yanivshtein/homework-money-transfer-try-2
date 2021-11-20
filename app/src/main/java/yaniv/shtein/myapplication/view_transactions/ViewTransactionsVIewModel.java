package yaniv.shtein.myapplication.view_transactions;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import yaniv.shtein.myapplication.utils.DBManager;
import yaniv.shtein.myapplication.utils.transaction.Transaction;

public class ViewTransactionsVIewModel extends AndroidViewModel {

    private LiveData<List<Transaction>> transactions;

    public ViewTransactionsVIewModel(@NonNull Application application) {
        super(application);
        //returns all the transactions from database
        transactions = new DBManager(application).getTransactions();
    }

    //gets all the transactions
    public LiveData<List<Transaction>> getTransactions() {
        return transactions;
    }
}
