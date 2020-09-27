package com.naserkarimi.wallet.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.naserkarimi.wallet.R;
import com.naserkarimi.wallet.ViewModel;
import com.naserkarimi.wallet.pojo.Address;
import com.naserkarimi.wallet.util.ConvertorUtil;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class AddressesFragment extends Fragment {

    View view;
    ViewModel viewModel;
    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    TextView total;
    double total_satoshi;
    AddressAdapter addressAdapter;
    List<String> addressStrings = Arrays.asList("tb1qvjc7jq8szaln85wutm6dsxgjm7vw8vk9ypdtgz",
            "tb1qn3lrfwgpxhrmt5qef0x9s9er40wevuz2aqglz6",
            "tb1qjynddzmv8q84nagl5hhxfzuhmcy8c4q0whrewd",
            "tb1qdtjlxhugk7d3gj298jqkgfg8tqu02uj44karmd",
            "tb1qjunsfrk5jvvzzfej2smt4thk878ljceu9fp900",
            "tb1q2v9uac0uzc6zd2f997pzjzukycjxd6chlht4hu",
            "tb1qha2q5yu3j02s9vjpnarxz8fy8xgvxqqgl5gvd3",
            "tb1qa2c7d6zfff90lphxpmrs3mteza86wffdypvgv8",
            "tb1qrry6ycll2x0h2r6jfnz8u2lzcl03zv4nahhxjl",
            "tb1qpsuz7zn3ff3t636yyrh4v2u8fkenh2n7d66mug");

    public AddressesFragment() {
        viewModel = new ViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_addresses, container, false);
        iniUI();
        viewModel.getAddress(addressStrings, getObserver());
        return view;
    }

    private void iniUI() {
        recyclerView = view.findViewById(R.id.recyclerViewAddress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addressAdapter = new AddressAdapter(getActivity());
        recyclerView.setAdapter(addressAdapter);

        total = view.findViewById(R.id.total_balance_all);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                total_satoshi = 0;
                viewModel.getAddress(addressStrings, getObserver());
            }
        });
    }

    private DisposableObserver<Address> observer;

    private DisposableObserver<Address> getObserver() {
        if (this.observer != null && !this.observer.isDisposed()) this.observer.dispose();
        observer = new DisposableObserver<Address>() {

            @Override
            public void onNext(Address address) {
                addressAdapter.addData(address);
                total_satoshi += (address.chain_stats.funded_txo_sum - address.chain_stats.spent_txo_sum);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), "ERROR IN FETCHING API RESPONSE. Try again",
                        Toast.LENGTH_LONG).show();
                if (swipeContainer.isRefreshing())
                    swipeContainer.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                if (total_satoshi == 0) {
                    total.setText("0");
                } else {
                    String btc = ConvertorUtil.getBitcoinFromSatoshi((long) total_satoshi);
                    total.setText(btc.toString());
                }
                if (swipeContainer.isRefreshing())
                    swipeContainer.setRefreshing(false);
            }
        };
        return observer;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.observer != null && !this.observer.isDisposed()) this.observer.dispose();
    }

    //    private void handleResults(Object object) {
//        if (object != null && object instanceof Address) {
//            Address address = (Address) object;
//            addressAdapter.addData(address);
//        } else {
//            Toast.makeText(getContext(), "NO RESULTS FOUND",
//                    Toast.LENGTH_LONG).show();
//        }
//    }
//
//    private void handleError(Throwable t) {
//
//        Toast.makeText(getContext(), "ERROR IN FETCHING API RESPONSE. Try again",
//                Toast.LENGTH_LONG).show();
//    }


}