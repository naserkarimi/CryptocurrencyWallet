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
import com.naserkarimi.wallet.model.ModelValues;
import com.naserkarimi.wallet.pojo.Address;
import com.naserkarimi.wallet.pojo.History;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    private static final String ARG_Address = "address";

    private String mAddress;
    View view;
    ViewModel viewModel;
    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    TextView currentAddress;

    public HistoryFragment() {
        viewModel = new ViewModel();
    }

    public static HistoryFragment newInstance(String param1) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_Address, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAddress = getArguments().getString(ARG_Address);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);

        iniUI();
        List<History> oldHistoryList = ModelValues.stringListHashMap.get(mAddress);
        Address addressInfo = ModelValues.stringAddressHashMap.get(mAddress);
        if (oldHistoryList == null || oldHistoryList.size() != addressInfo.chain_stats.tx_count) {
            viewModel.getHistory(mAddress, HistoryFragment.this::handleResults, HistoryFragment.this::handleError);
        } else {
            historyAdapter.setData(oldHistoryList);
        }

        return view;
    }

    private void iniUI() {
        recyclerView = view.findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        historyAdapter = new HistoryAdapter(mAddress);
        recyclerView.setAdapter(historyAdapter);

        currentAddress = view.findViewById(R.id.current_address);
        currentAddress.setText(mAddress);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getHistory(mAddress, HistoryFragment.this::handleResults, HistoryFragment.this::handleError);
            }
        });
    }

    private void handleResults(Object object) {
        if (object != null && object instanceof List) {
            List<History> historyList = (List<History>) object;
            if (historyList.size() != 0) {
                historyAdapter.setData(historyList);
            } else {
                Toast.makeText(getContext(), "NO RESULTS FOUND",
                        Toast.LENGTH_LONG).show();
            }
        }
        if (swipeContainer.isRefreshing())
            swipeContainer.setRefreshing(false);
    }

    private void handleError(Throwable t) {
        if (swipeContainer.isRefreshing())
            swipeContainer.setRefreshing(false);
        Toast.makeText(getContext(), "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }

}