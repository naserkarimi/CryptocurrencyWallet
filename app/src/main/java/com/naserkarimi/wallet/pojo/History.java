package com.naserkarimi.wallet.pojo;

import com.google.gson.annotations.SerializedName;

public class History {
    @SerializedName("txid")
    public String txid;
    @SerializedName("version")
    public Integer version;
    @SerializedName("locktime")
    public Double locktime;
    @SerializedName("size")
    public Integer size;
    @SerializedName("weight")
    public Integer weight;
    @SerializedName("fee")
    public Integer fee;
    @SerializedName("status")
    public Status status;

    public class Status {
        @SerializedName("confirmed")
        public Boolean confirmed;
        @SerializedName("block_height")
        public Double block_height;
        @SerializedName("block_hash")
        public String block_hash;
        @SerializedName("block_time")
        public Double block_time;
    }
}
