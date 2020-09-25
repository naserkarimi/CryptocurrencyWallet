package com.naserkarimi.wallet;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.naserkarimi.wallet.pojo.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<History> historyList;


    public HistoryAdapter() {
        historyList = new ArrayList<>();
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
        //todo reload data from server mechanism
        History history = historyList.get(position);
        holder.txtFee.setText(String.valueOf(history.fee));
        holder.txtLocktime.setText(String.valueOf(history.locktime));
        holder.txtSize.setText(String.valueOf(history.size));
        if (history.status.confirmed) {
            holder.cardView.setCardBackgroundColor(Color.GREEN);
        } else {
            holder.cardView.setCardBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void setData(List<History> data) {
        this.historyList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtFee;
        public TextView txtLocktime;
        public TextView txtSize;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            txtFee = view.findViewById(R.id.txtFee);
            txtLocktime = view.findViewById(R.id.txtLocktime);
            txtSize = view.findViewById(R.id.txtSize);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
