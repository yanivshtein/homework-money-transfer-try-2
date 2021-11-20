package yaniv.shtein.myapplication.utils.transaction;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    //to get all the transactions
    @Query("Select * From `Transaction`")
    LiveData<List<Transaction>> getAllTransactions();

    //to insert a transaction
    @Insert
    void insertAll(Transaction... transactions);
}
