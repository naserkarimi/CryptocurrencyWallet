package com.naserkarimi.wallet;

import android.annotation.SuppressLint;

import com.naserkarimi.wallet.pojo.Address;
import com.naserkarimi.wallet.pojo.History;
import com.naserkarimi.wallet.retrofit.AddressService;
import com.naserkarimi.wallet.retrofit.HistoryService;
import com.naserkarimi.wallet.retrofit.RetrofitHandler;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewModel {

    @SuppressLint("CheckResult")
    public void getHistory(String address, Consumer<? super Object> onNext, Consumer<? super Throwable> onError) {
        HistoryService historyService = RetrofitHandler.getInstance().getRetrofit().create(HistoryService.class);

        Observable<List<History>> historyObservable = historyService.getHistory(address);
        historyObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(onNext, onError);

    }

    //    @SuppressLint("CheckResult")
//    public static void getAddress(String address, Consumer<? super Object> onNext, Consumer<? super Throwable> onError) {
//        AddressService addressService = RetrofitHandler.getInstance().getRetrofit().create(AddressService.class);
//
//        Observable<Address> addressObservable = addressService.getAddress(address);
//        addressObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(result -> result)
//                .subscribe(onNext, onError);
//
//    }
    @SuppressLint("CheckResult")
    public void getAddress(List<String> address, DisposableObserver<Address> addressObserver) {
        AddressService addressService = RetrofitHandler.getInstance().getRetrofit().create(AddressService.class);

        Observable.just(address)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(addresses -> Observable.fromIterable(addresses))
                .flatMap(strindAddress -> addressService.getAddress(strindAddress))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addressObserver);

//    Observable<Address> addressObservable = addressService.getAddress(address);
//    addressObservable.subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map(result -> result)
//            .subscribeWith(addressObserver);

    }
}
