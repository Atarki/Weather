package com.example.tim.weather.Activity;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tim.weather.Adapter.AdapterRecycle;
import com.example.tim.weather.Data.CityData;
import com.example.tim.weather.Data.JSON;
import com.example.tim.weather.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<CityData> cityList;

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public static TextView getTextView() {
        return textView;
    }

    private static TextView textView;
    private Button button;


    public InputStream assetReader() throws IOException {
        return getAssets().open("City_list.txt");

    }
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
        try {
            cityList = AdapterRecycle.getCityList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAdapter = new AdapterRecycle(cityList);
        mRecyclerView.setAdapter(mAdapter);

        //HTTP
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //http://api.openweathermap.org/data/2.5/group?id=524901,703448,2643743&units=metric&appid=2de143494c0b295cca9337e1e96b00e0
                new JSON().execute("http://api.openweathermap.org/data/2.5/weather?q=kiev&appid=2de143494c0b295cca9337e1e96b00e0", "", "");
//                json.doInBackground("http://api.openweathermap.org/data/2.5/weather?q=kiev&appid=2de143494c0b295cca9337e1e96b00e0");
            }
        });
    }
}



