package com.example.tim.weather.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tim.weather.R;

import java.util.List;

public class Info  extends Activity{
    public List<Object> getJsonParsedObjects() {
        return jsonParsedObjects;
    }

    public void setJsonParsedObjects(List<Object> jsonParsedObjects) {
        this.jsonParsedObjects = jsonParsedObjects;
    }

    public List<Object> jsonParsedObjects;
    private TextView textView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Intent intent = new Intent();
//        intent.getParcelableArrayListExtra()

        textView = (TextView) findViewById(R.id.jsonOut);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
