package com.naserkarimi.wallet.retrofit;

import com.naserkarimi.wallet.pojo.Address;
import com.naserkarimi.wallet.pojo.History;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AddressService {
    String BASE_URL = "https://blockstream.info/testnet/api/";

    @GET("address/{address}")
    Observable<Address> getAddress(@Path("address") String address);
}
