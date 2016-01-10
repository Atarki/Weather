package com.example.tim.weather.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tim.weather.Data.CitiesData;
import com.example.tim.weather.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<CitiesData> cities;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public CardView cv;
        public TextView cityName;
        public TextView temperature;
        public ImageView idPhoto;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
//            cv = (CardView) view.findViewById(R.id.cv);
            cityName = (TextView) view.findViewById(R.id.cityName);
            temperature = (TextView) view.findViewById(R.id.temperature);
            idPhoto = (ImageView) view.findViewById(R.id.idPhoto);
        }
    }


    public MyAdapter(List<CitiesData> cities) {
        this.cities = cities;
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
//        CardView cardView = (CardView) viewHolder.cv.findViewById(R.id.cv);
        TextView cityName = (TextView) viewHolder.cityName.findViewById(R.id.cityName);
        TextView temperature = (TextView) viewHolder.temperature.findViewById(R.id.temperature);
        ImageView idPhoto = (ImageView) viewHolder.idPhoto.findViewById(R.id.idPhoto);

//        cardView.draw();
        cityName.setText(cities.get(i).getCityName());
        temperature.setText(cities.get(i).getTemperature());
        idPhoto.setImageResource(cities.get(i).getIdPhoto());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}