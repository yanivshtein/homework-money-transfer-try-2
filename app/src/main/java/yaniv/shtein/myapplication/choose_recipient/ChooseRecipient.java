package yaniv.shtein.myapplication.choose_recipient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import yaniv.shtein.myapplication.R;
import yaniv.shtein.myapplication.databinding.ChooseRecipientBinding;


public class ChooseRecipient extends Fragment {

    private ChooseRecipientBinding binding;
    private ChooseRecipientViewModel chooseRecipientViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        chooseRecipientViewModel =
                new ViewModelProvider(this).get(ChooseRecipientViewModel.class);

        binding = ChooseRecipientBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //button to move on to next screen
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets name of the recipient from the edit text
                String name = binding.etName.getText().toString();
                //checks if the name is valid (2-20 letters)
                if (chooseRecipientViewModel.isValid(name)) {
                    Bundle args = new Bundle();
                    args.putString("name", name);
                    //move to next screen and save the name of the recipient
                    NavHostFragment.findNavController(ChooseRecipient.this)
                            .navigate(R.id.action_fragmentChooseRecipient_to_fragmentSpecifyAmount, args);
                } else
                    binding.etName.setError("name needs more that one letter and less than 20 letters");
            }
        });

        //button for cancelling the action
        binding.btnCancel.setOnClickListener(v -> {
            NavHostFragment.findNavController(ChooseRecipient.this)
                    .navigate(R.id.action_fragmentChooseRecipient_to_fragmentMain);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}