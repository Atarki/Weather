package com.example.tim.weather.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.weather.Activity.Info;
import com.example.tim.weather.Activity.MainActivity;
import com.example.tim.weather.Data.POJO;
import com.example.tim.weather.R;

import java.util.List;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.ViewHolder> {
    private List<POJO> cities;
    private List<String> citiesID;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView cityName;
        private CardView cardView;
        private Context context;

        public static void setContext(Context context) {
            context = context;
        }


        public ViewHolder(View view) {
            super(view);
            this.view = view;
            cardView = (CardView) view.findViewById(R.id.cv);
            cityName = (TextView) view.findViewById(R.id.cityName);
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("test click");
//                    Intent intent = new Intent(context.getApplicationContext(), Info.class);
                    Intent intent = new Intent(context, Info.class);
//                    startActivity(intent);
                    context.startActivity(intent);
                }
            });
        }
    }

    public AdapterRecycle(List<POJO> cities) {
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