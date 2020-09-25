package com.naserkarimi.wallet;

import com.naserkarimi.wallet.pojo.History;
import com.naserkarimi.wallet.retrofit.HistoryService;
import com.naserkarimi.wallet.retrofit.RetrofitHandler;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ViewModel {

    public static void getHistory(String address, Consumer<? super Object> onNext, Consumer<? super Throwable> onError) {
        HistoryService historyService = RetrofitHandler.getInstance().getRetrofit().create(HistoryService.class);

        Observable<List<History>> historyObservable = historyService.getHistory(address);
        historyObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(onNext, onError);

//        Observable<List<History>> cryptoObservable = historyService.getHistory(address);
//        cryptoObservable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(result -> result)
//                .subscribe(onNext, onError);
    }
}
