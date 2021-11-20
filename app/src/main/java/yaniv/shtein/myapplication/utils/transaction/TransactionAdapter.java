package yaniv.shtein.myapplication.utils.transaction;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import yaniv.shtein.myapplication.databinding.TransactionBinding;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.VH> {

    private List<Transaction> transactions;

    public TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    static class VH extends RecyclerView.ViewHolder {
        TransactionBinding binding;

        public VH(TransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TransactionBinding binding = TransactionBinding.inflate(inflater, parent, false);


        return new VH(binding);
    }

    //setting up recycler view data
    @Override
    public void onBindViewHolder(TransactionAdapter.VH holder, int position) {
        Transaction transaction = transactions.get(position);
        DecimalFormat df = new DecimalFormat("##.00");
        holder.binding.tvRvName.setText(transaction.getName());
        holder.binding.tvRvAmount.setText(df.format(transaction.getAmount()) + " ILS was sent");
        holder.binding.tvOtherCurrency.setText("transaction was sent in " + transaction.getCurrency());
        holder.binding.tvConversionRate.setText("conversion rate: " + df.format(transaction.getConversionRate()));
        holder.binding.tvAmountInCurrency.setText(df.format(transaction.getAmountInCurrency()) + " " + transaction.getCurrency());
        holder.binding.tvOtherCurrency.setText(transaction.getCurrency());
        holder.binding.tvDateTime.setText(transaction.getDateTime());


    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

}
