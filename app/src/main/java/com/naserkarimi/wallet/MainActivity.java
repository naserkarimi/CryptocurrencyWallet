package com.naserkarimi.wallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.naserkarimi.wallet.pojo.History;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniUI();
        ViewModel.getHistory("tb1q9q8mmlwuxy75utn6v0dr72n3s5pec436dzkchu", this::handleResults, this::handleError);
    }

    private void iniUI() {
        //todo show total total available balance
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter();
        recyclerView.setAdapter(historyAdapter);
    }

    private void handleResults(Object object) {
        if (object != null && object instanceof List) {
            List<History> historyList = (List<History>) object;
            if (historyList.size() != 0) {
                //todo create section to show history of all addresses
                historyAdapter.setData(historyList);
            } else {
                Toast.makeText(this, "NO RESULTS FOUND",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void handleError(Throwable t) {

        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}