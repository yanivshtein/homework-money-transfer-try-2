package yaniv.shtein.myapplication.utils;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import yaniv.shtein.myapplication.utils.transaction.Transaction;

public class DBManager {

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    private final AppDatabase db;

    //create database with name "transaction-db"
    public DBManager(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "transaction-db").build();
    }

    //to get all the transactions
    public LiveData<List<Transaction>> getTransactions() {
        return db.transactionDao().getAllTransactions();
    }

    //to insert  a transaction to the database
    public void insert(Transaction transaction) {
        service.execute(() -> db.transactionDao().insertAll(transaction));
    }

}
