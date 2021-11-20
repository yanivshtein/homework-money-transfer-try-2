package yaniv.shtein.myapplication.choose_recipient;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

public class ChooseRecipientViewModel extends AndroidViewModel {


    public ChooseRecipientViewModel(Application application) {
        super(application);
    }

    //checks if the length of recipient name is more than 1 character and less than 20 characters
    public boolean isValid(String name) {
        return name.length() > 1 && name.length() < 21;
    }
}
