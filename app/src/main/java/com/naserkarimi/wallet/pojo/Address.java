package com.naserkarimi.wallet.pojo;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("address")
    public String address;
    @SerializedName("chain_stats")
    public Chain_stats chain_stats;


    public class Chain_stats {
        @SerializedName("tx_count")
        public Integer tx_count;
        @SerializedName("funded_txo_sum")
        public Double funded_txo_sum;
        @SerializedName("spent_txo_sum")
        public Double spent_txo_sum;
    }

}
