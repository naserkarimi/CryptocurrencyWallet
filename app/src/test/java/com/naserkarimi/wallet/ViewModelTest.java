package com.naserkarimi.wallet;

import androidx.annotation.NonNull;

import com.naserkarimi.wallet.pojo.Address;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;

import static org.junit.Assert.assertTrue;

public class ViewModelTest {

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Scheduler.Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void testGetHistory() {
        Consumer<? super Object> onNext = Assert::assertNotNull;
        Consumer<? super Throwable> onError = (Consumer<Throwable>) throwable -> {

        };
        ViewModel viewModel = new ViewModel();
        viewModel.getHistory("tb1q9q8mmlwuxy75utn6v0dr72n3s5pec436dzkchu", onNext, onError);
    }

    @Test
    public void testGetAddress() {
        ViewModel viewModel = new ViewModel();
        viewModel.getAddress(Collections.singletonList("tb1q9q8mmlwuxy75utn6v0dr72n3s5pec436dzkchu"), new DisposableObserver<Address>() {
            @Override
            public void onNext(Address address) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                assertTrue(true);
            }
        });
    }

}
