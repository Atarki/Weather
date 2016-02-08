package com.example.tim.weather.Activity;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.weather.Data.POJO;
import com.example.tim.weather.R;

public class Info extends DialogFragment {
    private POJO pojo;
    private TextView textView;
    private View view;


    public POJO getPojo() {
        return pojo;
    }

    public void setPojo(POJO pojo) {
        this.pojo = pojo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        View infoView = inflater.inflate(R.layout.info, viewGroup, false);

        pojo = (POJO) getArguments().getSerializable("pojo");


        assert pojo != null;
        POJO.Main main = pojo.getMain();
        System.out.println("MAIN " + main.getTemp());

        CardView cardView = (CardView) viewGroup.findViewById(R.id.cv);

        textView = (TextView) viewGroup.findViewById(R.id.name);
        textView.setText(pojo.getName());


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

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
