package com.naserkarimi.wallet.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    public Long fee;
    @SerializedName("status")
    public Status status;
    @SerializedName("vin")
    public List<Vin> vin;

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

    public class Vin {
        @SerializedName("txid")
        public String txid;
        @SerializedName("vout")
        public Integer vout;
        @SerializedName("scriptsig")
        public String scriptsig;
        @SerializedName("prevout")
        public Prevout prevout;
    }

    public class Prevout {
        @SerializedName("scriptpubkey")
        public String scriptpubkey;
        @SerializedName("scriptpubkey_asm")
        public String scriptpubkey_asm;
        @SerializedName("scriptpubkey_type")
        public String scriptpubkey_type;
        @SerializedName("scriptpubkey_address")
        public String scriptpubkey_address;
        @SerializedName("value")
        public Long value;
    }
}
