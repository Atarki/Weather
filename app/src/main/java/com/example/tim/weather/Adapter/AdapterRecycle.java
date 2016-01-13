package com.example.tim.weather.Adapter;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.weather.Activity.MainActivity;
import com.example.tim.weather.Data.CityData;
import com.example.tim.weather.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    public List<CityData> cities;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView cityName;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            cityName = (TextView) view.findViewById(R.id.cityName);
        }
    }

    public static List getCityList() throws IOException {
        List<String> cityList = new ArrayList<String>();
        InputStream inputStream = new MainActivity().assetReader();

        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(inputStream)));
        String line;
        while ((line = reader.readLine()) != null) cityList.add(line);
        reader.close();
        inputStream.close();
        return cityList;
    }

    public AdapterRecycle(List<CityData> cities) {
        this.cities = cities;
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        TextView cityName = (TextView) viewHolder.cityName.findViewById(R.id.cityName);
        cityName.setText(cities.get(i).getCityName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}