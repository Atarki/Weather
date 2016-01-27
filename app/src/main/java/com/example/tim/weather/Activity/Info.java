package com.example.tim.weather.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tim.weather.Data.POJO;
import com.example.tim.weather.R;

import java.util.List;

public class Info  extends Activity{
    private POJO pojo;
    private TextView textView;
    private View view;

    public POJO getPojo() {
        return pojo;
    }

    public void setPojo(POJO pojo) {
        this.pojo = pojo;
    }

    @Override
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
    }
}
