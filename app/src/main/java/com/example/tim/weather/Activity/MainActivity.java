package com.example.tim.weather.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tim.weather.Adapter.AdapterRecycle;
import com.example.tim.weather.Data.JSON;
import com.example.tim.weather.Data.POJO;
import com.example.tim.weather.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> citiesID;
    private List<POJO> cityList;
    public Context context;
    private View view;

    public void getCitiesID() throws IOException {
        citiesID = new ArrayList<>();
        InputStream inputStream = getResources().getAssets().open("City_list.txt");

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) citiesID.add(line);
        bufferedReader.close();
        inputStream.close();
        System.out.println(citiesID);

    }

    public List<POJO> getCityList(List<String> citiesID) {
        cityList = new ArrayList<>();
        for (int i = 0; i < citiesID.size(); i++) {
            POJO pojo = new POJO();
            pojo.setCityName(citiesID.get(i));
            cityList.add(pojo);
        }
        return cityList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        try {
            getCitiesID();
            getCityList(citiesID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mAdapter = new AdapterRecycle(cityList, context);
        mRecyclerView.setAdapter(mAdapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JSON().execute();
            }
        });


//        AdapterRecycle.ViewHolder.setContext(context);
    }

    public void callInfo(View view) {
        System.out.println("test click");
//        new JSON().execute();
        Intent intent = new Intent(context, Info.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        JSON json = new JSON();
        json.execute();
        List<Object> jsonParsedObjects = json.getJsonParsedObjects();

        TextView textView = (TextView) findViewById(R.id.jsonOut);

        if (jsonParsedObjects != null) {
            textView.setText(jsonParsedObjects.toString());
        }

    }
}



