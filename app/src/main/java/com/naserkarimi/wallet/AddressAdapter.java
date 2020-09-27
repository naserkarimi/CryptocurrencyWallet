package com.naserkarimi.wallet;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.naserkarimi.wallet.pojo.Address;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<Address> addressList;
    private HashMap<String, Address> stringAddressHashMap;


    public AddressAdapter() {
        addressList = new ArrayList<>();
        stringAddressHashMap = new HashMap<>();
    }

    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_address, parent, false);
        AddressAdapter.ViewHolder viewHolder = new AddressAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddressAdapter.ViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.txtAddress.setText(String.valueOf(address.address));
        double satoshi = address.chain_stats.funded_txo_sum - address.chain_stats.spent_txo_sum;
        if (satoshi == 0) {
            holder.txtTotalBalance.setText("0");
        } else {
            BigDecimal btc = new BigDecimal(satoshi).movePointLeft(8);
            holder.txtTotalBalance.setText(btc.toString());
        }
        //we can show empty addresses by other color
//        if (address.chain_stats.tx_count==0) {
//            holder.cardView.setCardBackgroundColor(Color.GRAY);
//        } else {
//            holder.cardView.setCardBackgroundColor(Color.GREEN);
//        }
        holder.cardView.setCardBackgroundColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public void addData(Address data) {
        Address oldAddrerss = stringAddressHashMap.get(data.address);
        if (oldAddrerss == null) {
            this.addressList.add(data);
            notifyItemInserted(addressList.indexOf(data));
        } else {
            this.addressList.set(this.addressList.indexOf(oldAddrerss), data);
            notifyItemChanged(addressList.indexOf(data));
        }
        stringAddressHashMap.put(data.address, data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtAddress;
        public TextView txtTotalBalance;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            txtAddress = view.findViewById(R.id.txtAddress);
            txtTotalBalance = view.findViewById(R.id.txt_total_balance);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}
