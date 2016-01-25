package com.example.tim.weather.Data;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.tim.weather.Activity.Info;
import com.example.tim.weather.Activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSON extends AsyncTask<String, String, ArrayList<Object>> {
    private static String TOKEN = "a5982092ec3e995cac27ef4bf254ffd2";
    private static String httpGroup = "http://api.openweathermap.org/data/2.5/group?id=";
    private static String http = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String map = "&appid=";
    //    private static String map = "&units=metric&appid=";
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    InputStream inputStream = null;
    String citiesID = "710791,710735,710719,709930,709717,707471," +
            "706483,706448,706369,696050,705812,702658,702550,700569," +
            "698740,696643,695594,692194,691650,702569,690548,689558,687700,686967";

    public List<Object> getJsonParsedObjects() {
        return jsonParsedObjects;
    }

    public ArrayList<Object> jsonParsedObjects;


    @Override
    public ArrayList<Object> doInBackground(String... params) {

        try {
            URL url = new URL(http + "Kiev" + map + TOKEN);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            System.out.println(url.toString());// Delete later

            inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder buffer = new StringBuilder();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJSON = buffer.toString();

            //ANDROID REGULAR JSON part
            JSONObject ParentJSON = new JSONObject(finalJSON);
            POJO pojo = new POJO();
            pojo.setName(ParentJSON.getString("name"));
            pojo.setId(ParentJSON.getString("id"));

            System.out.println(pojo.getName());
            System.out.println(pojo.getId());

            JSONArray weatherArray = ParentJSON.getJSONArray("weather");
            JSONObject weatherJSON_01 = weatherArray.getJSONObject(0);

            /*if (weatherArray.getJSONObject(1) != null) {
                JSONObject weatherJSON_02 = weatherArray.getJSONObject(1);
                POJO.Weather weather_02 = new POJO.Weather();
                weather_02.setMain(weatherJSON_02.optString("main"));
                weather_02.setIcon(weatherJSON_02.optString("icon"));
                weather_02.setIcon(weatherJSON_02.optString("description"));

                System.out.println(weather_02.getMain());
                System.out.println(weather_02.getDescription());
                System.out.println(weather_02.getIcon());
            }*/

            POJO.Weather weather_01 = new POJO.Weather();
            weather_01.setMain(weatherJSON_01.optString("main"));
            weather_01.setIcon(weatherJSON_01.optString("icon"));
            weather_01.setIcon(weatherJSON_01.optString("description"));

            System.out.println(weather_01.getMain());
            System.out.println(weather_01.getDescription());
            System.out.println(weather_01.getIcon());

            POJO.Main main = new POJO.Main();
            JSONObject mainJSON = ParentJSON.getJSONObject("main");
            main.setHumidity(mainJSON.getString("humidity"));
            main.setPressure(mainJSON.getString("pressure"));
            main.setTemp(mainJSON.getString("temp"));
            main.setTemp_max(mainJSON.getString("temp_max"));
            main.setTemp_min(mainJSON.getString("temp_min"));

            System.out.println(main.getTemp());
            System.out.println(main.getPressure());

            POJO.Wind wind = new POJO.Wind();
            JSONObject windJSON = ParentJSON.getJSONObject("wind");
            wind.setDeg(windJSON.getString("deg"));
            wind.setSpeed(windJSON.getString("speed"));

            System.out.println(wind.getDeg());
            System.out.println(wind.getSpeed());

            jsonParsedObjects = new ArrayList<>();
            jsonParsedObjects.add(pojo);
            jsonParsedObjects.add(weather_01);
            jsonParsedObjects.add(main);
            jsonParsedObjects.add(wind);

            if (connection == null) connection.disconnect();
            if (reader == null) reader.close();

            return jsonParsedObjects;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Object> result) {
        super.onPostExecute(result);
//        jsonParsedObjects = result;
//        AdapterRecycle adapter = new AdapterRecycle((List<POJO>) result);
        // TODO


    }
}