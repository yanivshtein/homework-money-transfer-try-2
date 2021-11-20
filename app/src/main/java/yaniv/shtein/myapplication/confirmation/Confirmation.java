package yaniv.shtein.myapplication.confirmation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import yaniv.shtein.myapplication.R;
import yaniv.shtein.myapplication.databinding.ConfirmationBinding;
import yaniv.shtein.myapplication.utils.SharedPrefs;


public class Confirmation extends Fragment {

    private ConfirmationBinding binding;
    private ConfirmationViewModel confirmationViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        confirmationViewModel = new ViewModelProvider(this).get(ConfirmationViewModel.class);

        binding = ConfirmationBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get arguments from last screen
        Bundle args = getArguments();
        //check if there are arguments
        if (args != null) {
            String currency = args.getString("currency");
            String name = args.getString("name");
            float amount = args.getFloat("amount");
            //show the transaction details on screen
            binding.tvCurrency.setText(currency);
            binding.tvName.setText(name);
            binding.tvAmount.setText(Float.toString(amount) + " ILS");
            //add the transaction to the database
            confirmationViewModel.addTransaction(name, amount, currency);
        }

        //Done button return to main menu
        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Confirmation.this)
                        .navigate(R.id.action_fragmentConfirmation_to_fragmentMain);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}