package com.example.tim.weather.Activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.weather.Data.POJO;
import com.example.tim.weather.R;

import java.util.List;

public class Info  extends DialogFragment {
    private POJO pojo;
    private TextView textView;
    private View view;



    public POJO getPojo() {
        return pojo;
    }

    public void setPojo(POJO pojo) {
        this.pojo = pojo;
    }

//    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        View infoView = inflater.inflate(R.layout.info, container, false);

//        Bundle pojo = bundle.getBundle("info");
//        POJO.Main main = pojo.getMain();
//        textView = (TextView) textView.findViewById(R.id.name);
//        textView.setText(pojo.getName());
        return infoView;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pojo = (POJO)getIntent().getSerializableExtra("pojo"); //Obtaining data
        }

        POJO.Main main = pojo.getMain();
        textView = (TextView) findViewById(R.id.name);
        textView.setText(pojo.getName());

        POJO.Weather weather = pojo.getWeather();
        TextView weatherMain = (TextView) findViewById(R.id.weather);
        weatherMain.setText(weather.getMain());

        POJO.Wind wind = pojo.getWind();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }*/
}
