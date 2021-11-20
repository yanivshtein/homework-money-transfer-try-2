package yaniv.shtein.myapplication.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import yaniv.shtein.myapplication.utils.transaction.Transaction;
import yaniv.shtein.myapplication.utils.transaction.TransactionDao;

@Database(entities = {Transaction.class}, version = 1)
//app database
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionDao transactionDao();
}
