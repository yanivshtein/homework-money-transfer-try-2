package yaniv.shtein.myapplication.specify_amount;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yaniv.shtein.myapplication.R;
import yaniv.shtein.myapplication.databinding.SpecifyAmountBinding;
import yaniv.shtein.myapplication.utils.SharedPrefs;


public class SpecifyAmount extends Fragment {

    private SpecifyAmountBinding binding;
    private SpecifyAmountViewModel specifyAmountViewModel;
    private String recipientString = "recipient: ";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        specifyAmountViewModel = new ViewModelProvider(this).get(SpecifyAmountViewModel.class);

        binding = SpecifyAmountBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get arguments from last screen
        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("name");
            //set the textview text as the name of the recipient
            binding.tvRecipientName.setText(recipientString + name);
        }

        //button to continue the transaction
        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the input amount is lower than the balance amount, if yes returns -1, else returns the the input amount
                float amount = specifyAmountViewModel.getAmount(binding.etAmount.getText().toString());
                //checks is the input currency exists
                boolean currencyExists = specifyAmountViewModel.checkCurrency(binding.etCurrencyName.getText().toString());
                //set error in text field if the currency doesn't exist
                if (!currencyExists) {
                    binding.etCurrencyName.setError("this currency does not exist");
                }
                //checks if the currency and amount input are valid, can continue, else set error
                if (amount >= 0 && currencyExists) {
                    //move to next screen and save the amount and currency name
                    args.putString("currency", binding.etCurrencyName.getText().toString());
                    args.putFloat("amount", amount);
                    NavHostFragment.findNavController(SpecifyAmount.this)
                            .navigate(R.id.action_fragmentSpecifyAmount_to_fragmentConfirmation, args);
                } else
                    binding.etAmount.setError("you don't have enough money");
            }
        });

        //cancel transaction button
        binding.btnCancel1.setOnClickListener(v -> {
            NavHostFragment.findNavController(SpecifyAmount.this)
                    .navigate(R.id.action_fragmentSpecifyAmount_to_fragmentChooseRecipient);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}