package com.example.tim.weather.Data;

import android.os.AsyncTask;
import android.widget.TextView;
import com.example.tim.weather.Activity.MainActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSON extends AsyncTask<String, String, String> {
    HttpURLConnection connection = null;
    BufferedReader reader = null;

    int Cherkasy = 710791;
    int Chernihiv = 710735;
    int Chernivtsi = 710719;
    int Dnipropetrovsk = 709930;
    int Donetsk = 709717;
    int Ivano = 707471;
    int Kharkiv = 706483;
    int Kherson = 706448;
    int Khmelnytskyi = 706369;
    int Kievi = 696050;
    int Kirovohrad = 705812;
    int Luhansk = 702658;
    int Lviv = 702550;
    int Mykolaiv = 700569;
    int Odessa = 698740;
    int Poltava = 696643;
    int Rivne = 695594;
    int Sumy = 692194;
    int Ternopil = 691650;
    int Lutsk = 702569;
    int Uzhhorod = 690548;
    int Vinnytsia = 689558;
    int Zaporizhia = 687700;
    int Zhytomyr = 686967;
    String citiesID = "710791,710735,710719,709930,709717,707471," +
            "706483,706448,706369,696050,705812,702658,702550,700569," +
            "698740,696643,695594,692194,691650,702569,690548,689558,687700,686967";

    private static String TOKEN = "3949b2dbd68ccfa5fe4a429b0de9c965";
    private static String http = "http://api.openweathermap.org/data/2.5/group?id=";
    private static String map = "&units=metric&appid=";

    @Override
    public String doInBackground(String... params) {

        try {
            URL url = new URL(http+citiesID+map+TOKEN);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }


            //JSON part
            String finalJSON = buffer.toString();
            JSONObject parentObject = new JSONObject(finalJSON);
//            JSONArray parentArray = parentObject.getJSONArray("weather");

//            JSONObject main = parentObject.getJSONObject("main");
//            String temperature = main.getString("temp");
//            String pressure = main.getString("pressure");

            return parentObject.toString();
//            return "Температура: " + temperature + "\n" + "Давление: " + pressure;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        TextView textView = MainActivity.getTextView();

        textView.setText(result);
    }
}