package yaniv.shtein.myapplication.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import yaniv.shtein.myapplication.utils.transaction.Transaction;

public class SharedPrefs {

    //to change the amount balance
    public static void saveBalance(Context context, float amount) {
        context.getSharedPreferences("balance", Context.MODE_PRIVATE).edit().putFloat("balance", amount).apply();

    }

    //to read how much money in balance
    public static float readBalance(Context context) {
        return context.getSharedPreferences("balance", Context.MODE_PRIVATE).getFloat("balance", -1);
    }

    //checks if there is enough money balance to be able to send the amount
    public static float transaction(Context context, float amount) {
        if (readBalance(context) < amount)
            return -1;
        else {
            saveBalance(context, readBalance(context) - amount);
            return amount;
        }
    }

}
