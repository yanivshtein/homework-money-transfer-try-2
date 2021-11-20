package yaniv.shtein.myapplication.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import yaniv.shtein.myapplication.R;
import yaniv.shtein.myapplication.databinding.FragmentMainBinding;

public class FragmentMain extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //start a transaction
        binding.btnSendMoney.setOnClickListener(v -> {
            NavHostFragment.findNavController(FragmentMain.this)
                    .navigate(R.id.action_fragmentMain_to_fragmentChooseRecipient);
        });

        //button to view balance
        binding.btnViewBalance.setOnClickListener(v -> {
            NavHostFragment.findNavController(FragmentMain.this)
                    .navigate(R.id.action_fragmentMain_to_fragmentViewBalance);
        });

        //button to view history of transactions
        binding.btnViewTransactions.setOnClickListener(v -> {
            NavHostFragment.findNavController(FragmentMain.this)
                    .navigate(R.id.action_fragmentMain_to_fragmentViewTransactions);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}