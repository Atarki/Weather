package com.example.tim.weather.Data;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSON extends AsyncTask<String, String, POJO> {
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
    String chosenCity;


    @Override
    public POJO doInBackground(String... params) {

        try {
            URL url = new URL(http + "Kiev" + map + TOKEN);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            System.out.println(url.toString());// Delete later

            inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String finalJSON = buffer.toString();

            //GSON part
           /* Gson gson = new Gson();

            POJO pojo = gson.fromJson(finalJSON, POJO.class);
            String name = pojo.getName();
//            POJO.Weather[] weather = gson.fromJson("weather", POJO.Weather[].class);
            System.out.println(name);
            System.out.println(pojo.toString());
//            System.out.println(weather);*/

            //JACKSON JSON part
            /*ObjectMapper mapper = new ObjectMapper();
            POJO pojo = mapper.readValue(url, POJO.class);
            POJO.Weather[] weather = pojo.getWeather();
            String icon = weather[0].getIcon();
            String main = weather[0].getMain();

            System.out.println(icon);
            System.out.println(main);
            System.out.println(pojo);*/

            //ANDROID REGULAR JSON part
            JSONObject ParentJSON = new JSONObject(finalJSON);
            POJO pojo = new POJO();
            pojo.setName(ParentJSON.getString("name"));
            pojo.setId(ParentJSON.getString("id"));

            System.out.println(pojo.getName());
            System.out.println(pojo.getId());

            JSONArray weatherArray = ParentJSON.getJSONArray("weather");
            JSONObject weatherJSON_01 = weatherArray.getJSONObject(0);

            if (weatherArray.getJSONObject(1) != null) {
                JSONObject weatherJSON_02 = weatherArray.getJSONObject(1);
                POJO.Weather weather_02 = new POJO.Weather();
                weather_02.setMain(weatherJSON_02.optString("main"));
                weather_02.setIcon(weatherJSON_02.optString("icon"));
                weather_02.setIcon(weatherJSON_02.optString("description"));

                System.out.println(weather_02.getMain());
                System.out.println(weather_02.getDescription());
                System.out.println(weather_02.getIcon());
            }

            //JSON parse
//            POJO.Weather weather = weatherJSON.getJSONObject("0");
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

            return null;

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
    protected void onPostExecute(POJO result) {
        super.onPostExecute(result);
//        AdapterRecycle adapter = new AdapterRecycle((List<POJO>) result);
        // TODO
    }
}