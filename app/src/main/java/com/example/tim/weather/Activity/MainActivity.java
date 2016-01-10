package com.example.tim.weather.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tim.weather.Adapter.MyAdapter;
import com.example.tim.weather.Data.CitiesData;
import com.example.tim.weather.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        List<CitiesData> cityList = initializeData();


        mAdapter = new MyAdapter(cityList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public List<CitiesData> initializeData() {
        List<CitiesData> cities = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            cities.add(new CitiesData("City: " + i, "Temperature: " + random.nextInt(30), R.drawable.cloud));
        }
        return cities;
    }
    /*private ArrayList<CitiesData> getDataSet() {
        ArrayList results = new ArrayList<CitiesData>();
        for (int index = 0; index < 20; index++) {
            CitiesData obj = new CitiesData("Some Primary Text " + index, "Secondary " + index, index);
            results.add(index, obj);
        }
        return results;
    }*/
}
