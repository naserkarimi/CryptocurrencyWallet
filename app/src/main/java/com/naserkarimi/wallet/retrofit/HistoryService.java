package com.naserkarimi.wallet.retrofit;

import com.naserkarimi.wallet.pojo.History;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HistoryService {
    String BASE_URL = "https://blockstream.info/testnet/api/";

    @GET("address/{address}/txs")
    Observable<List<History>> getHistory(@Path("address") String address);
}
