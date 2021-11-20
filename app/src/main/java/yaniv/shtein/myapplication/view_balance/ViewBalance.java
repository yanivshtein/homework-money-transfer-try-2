package yaniv.shtein.myapplication.view_balance;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yaniv.shtein.myapplication.R;
import yaniv.shtein.myapplication.databinding.ViewBalanceBinding;
import yaniv.shtein.myapplication.utils.SharedPrefs;


public class ViewBalance extends Fragment {

    private ViewBalanceBinding binding;
    private ViewBalanceViewModel balanceViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        balanceViewModel = new ViewModelProvider(this).get(ViewBalanceViewModel.class);


        binding = ViewBalanceBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//button to go back to main menu
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewBalance.this)
                        .navigate(R.id.action_fragmentViewBalance_to_fragmentMain);
            }
        });

        //shows the account balance
        binding.tvBalance.setText(balanceViewModel.getBalance());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}