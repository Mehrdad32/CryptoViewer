package ir.mehrdad32.cryptoviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ir.mehrdad32.cryptoviewer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CryptoViewModel model;
    private List<ListItem> list;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        model = new ViewModelProvider(this).get(CryptoViewModel.class);
        model.getCrypto().observe(this, cryptos -> {
            list.clear();
            for (Datum datum: cryptos.data) {
                list.add(new ListItem(datum.name, datum.price_usd, datum.percent_change_24h));
            }
            initRecycler();
        });

        binding.swipeRefresh.setOnRefreshListener(this::loadData);

        binding.swipeRefresh.setRefreshing(false);

        loadData();
    }

    private void loadData() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            //Show waiting on UI thread:
            //runOnUiThread(() -> progressing(true));

            //Run task:
            model.loadCrypto();

            //Return UI thread to ideal mode:
            runOnUiThread(() -> progressing(false));
        });
    }

    private void initRecycler() {
        if(list == null)
            return;

        adapter = new ItemAdapter(MainActivity.this, list);
        binding.cryptoRecycleView.setAdapter(adapter);
        binding.cryptoRecycleView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void progressing(boolean b) {
        if(b)
            binding.loadProgressBar.setVisibility(View.VISIBLE);
        else {
            binding.loadProgressBar.setVisibility(View.GONE);
            binding.swipeRefresh.setRefreshing(false);
        }
    }
}