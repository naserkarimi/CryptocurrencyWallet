package com.naserkarimi.wallet.UI;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.naserkarimi.wallet.R;
import com.naserkarimi.wallet.model.ModelValues;
import com.naserkarimi.wallet.pojo.History;
import com.naserkarimi.wallet.util.ConvertorUtil;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<History> historyList;
    private String currentAddress;


    public HistoryAdapter(String mAddress) {
        historyList = new ArrayList<>();
        currentAddress = mAddress;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_history, parent, false);
        HistoryAdapter.ViewHolder viewHolder = new HistoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        History history = historyList.get(position);
        String fee = ConvertorUtil.getBitcoinFromSatoshi(history.fee);
        holder.txtFee.setText(fee);
        holder.txtHash.setText(String.valueOf(history.txid));
        String totalInput = ConvertorUtil.getBitcoinFromSatoshi(history.vin.get(0).prevout.value);
        holder.txtTotalInput.setText(totalInput);
        if (history.status.confirmed) {
            holder.cardView.setCardBackgroundColor(Color.GREEN);
            holder.txtStatus.setText("Confirmed");
        } else {
            holder.cardView.setCardBackgroundColor(Color.GRAY);
            holder.txtStatus.setText("Unconfirmed");
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void setData(List<History> data) {
        this.historyList = data;
        notifyDataSetChanged();
        ModelValues.stringListHashMap.put(currentAddress, data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtFee;
        public TextView txtHash;
        public TextView txtStatus;
        public TextView txtTotalInput;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            txtFee = view.findViewById(R.id.txtFee);
            txtHash = view.findViewById(R.id.txtHash);
            txtStatus = view.findViewById(R.id.txtStatus);
            txtTotalInput = view.findViewById(R.id.txtTotalInput);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
