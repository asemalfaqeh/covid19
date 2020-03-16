package com.af.covid19.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.af.covid19.R;
import com.af.covid19.model.AllCasesResponse;
import com.af.covid19.model.DataResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView all_deaths, all_recovered, all_cases, new_cases, new_deaths, last_update;
    private RecyclerView recyclerView;
    private DataViewModel dataViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

        all_cases = findViewById(R.id.all_cases);
        all_recovered = findViewById(R.id.recovered_all);
        all_deaths = findViewById(R.id.all_deaths);
        recyclerView = findViewById(R.id.recyclerView);
        new_cases = findViewById(R.id.new_cases);
        new_deaths = findViewById(R.id.new_deaths);
        last_update = findViewById(R.id.last_update);
        swipeRefreshLayout = findViewById(R.id.swipe);

        dataViewModel.getAllCasesViewModel().observe(this, allCasesResponse -> {
            all_cases.setText(allCasesResponse.getTotal_cases() + "");
            all_recovered.setText(allCasesResponse.getTotal_recovered() + "");
            all_deaths.setText(allCasesResponse.getTotal_recovered() + "");
            new_deaths.setText(allCasesResponse.getNew_deaths() + "");
            new_cases.setText(allCasesResponse.getNew_cases() + "");
            last_update.setText(allCasesResponse.getStatistic_taken_at() + "");
        });

        dataViewModel.getDataViewModel().observe(this, this::initRecyclerView);

        swipeRefreshLayout.setOnRefreshListener(() -> {

            swipeRefreshLayout.setRefreshing(true);

            dataViewModel.getDataViewModel().observe(MainActivity.this, this::initRecyclerView);
            dataViewModel.getAllCasesViewModel().observe(this, allCasesResponse -> {
                all_cases.setText(allCasesResponse.getTotal_cases() + "");
                all_recovered.setText(allCasesResponse.getTotal_recovered() + "");
                all_deaths.setText(allCasesResponse.getTotal_recovered() + "");
                new_deaths.setText(allCasesResponse.getNew_deaths() + "");
                new_cases.setText(allCasesResponse.getNew_cases() + "");
                last_update.setText(allCasesResponse.getStatistic_taken_at() + "");
            });

            new Handler().postDelayed(() -> {
                swipeRefreshLayout.setRefreshing(false);
                swipeRefreshLayout.destroyDrawingCache();
                swipeRefreshLayout.clearAnimation();
            },1500);

        });

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dataViewModel.clear();
    }

    private void initRecyclerView(List<DataResponse.CountriesStat> dataResponseList){
        DataAdapter dataAdapter = new DataAdapter(this, (ArrayList<DataResponse.CountriesStat>) dataResponseList);
        recyclerView.setAdapter(dataAdapter);
        StaggeredGridLayoutManager st = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(st);
        dataAdapter.notifyDataSetChanged();
    }

}
