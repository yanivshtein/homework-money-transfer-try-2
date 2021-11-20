package yaniv.shtein.myapplication.view_transactions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yaniv.shtein.myapplication.R;
import yaniv.shtein.myapplication.databinding.ViewTransactionsBinding;
import yaniv.shtein.myapplication.utils.transaction.TransactionAdapter;


public class ViewTransactions extends Fragment {

    private ViewTransactionsBinding binding;
    private ViewTransactionsVIewModel viewTransactionsVIewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewTransactionsVIewModel = new ViewModelProvider(this).get(ViewTransactionsVIewModel.class);

        binding = ViewTransactionsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setting up the recycler view
        viewTransactionsVIewModel.getTransactions().observe(getViewLifecycleOwner(), transactions -> {
            TransactionAdapter adapter = new TransactionAdapter(transactions);
            binding.rvTransactions.setAdapter(adapter);
            //setting it so that new transactions are at the top of the recycler view
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
            binding.rvTransactions.setLayoutManager(linearLayoutManager);
        });

        //back button to go to main menu
        binding.btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewTransactions.this)
                        .navigate(R.id.action_fragmentViewTransactions_to_fragmentMain);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}