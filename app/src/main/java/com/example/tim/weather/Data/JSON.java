package com.example.tim.weather.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tim.weather.Adapter.AdapterRecycle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JSON extends AsyncTask<String, String, CityData> {
    private static String TOKEN = "a5982092ec3e995cac27ef4bf254ffd2";
    private static String httpGroup = "http://api.openweathermap.org/data/2.5/group?id=";
    private static String http = "http://api.openweathermap.org/data/2.5/find?id=";
    private static String map = "&units=metric&appid=";
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    InputStream inputStream = null;
    String citiesID = "710791,710735,710719,709930,709717,707471," +
            "706483,706448,706369,696050,705812,702658,702550,700569," +
            "698740,696643,695594,692194,691650,702569,690548,689558,687700,686967";
    String chosenCity;


    @Override
    public CityData doInBackground(String... params) {

        try {
            URL url = new URL(http + "Kiev" + map + TOKEN);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            //JSON part
            String finalJSON = buffer.toString();
            CityData cityData = new CityData();
            JSONObject ParentJSON = new JSONObject(finalJSON);


            JSONArray weatherArray = ParentJSON.getJSONArray("weather");
            JSONObject weatherJSON = weatherArray.getJSONObject(0);

            //JSON parse
            CityData.Zero zero = new CityData.Zero();
            zero.setMain(weatherJSON.optString("main"));
            zero.setIcon(weatherJSON.optString("icon"));

            CityData.Main main = new CityData.Main();
            JSONObject mainJSON = ParentJSON.getJSONObject("main");
            main.setHumidity(mainJSON.getString("humidity"));
            main.setPressure(mainJSON.getString("pressure"));
            main.setTemp(mainJSON.getString("temp"));
            main.setTemp_max(mainJSON.getString("temp_max"));
            main.setTemp_min(mainJSON.getString("temp_min"));

            CityData.Wind wind = new CityData.Wind();
            JSONObject windJSON = mainJSON.getJSONObject("wind");
            wind.setDeg(windJSON.getString("deg"));
            wind.setSpeed(windJSON.getString("speed"));

            return cityData;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.disconnect();
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(CityData result) {
        super.onPostExecute(result);
//        AdapterRecycle adapter = new AdapterRecycle((List<CityData>) result);
        // TODO
    }
}