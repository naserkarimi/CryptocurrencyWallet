package com.naserkarimi.wallet;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.naserkarimi.wallet.UI.AddressesFragment;
import com.naserkarimi.wallet.UI.HistoryAdapter;

public class MainActivity extends FragmentActivity {

    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniFragment();
    }

    private void iniFragment() {
        Fragment newFragment = new AddressesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, newFragment)
                .disallowAddToBackStack()
                .commit();
     }
}