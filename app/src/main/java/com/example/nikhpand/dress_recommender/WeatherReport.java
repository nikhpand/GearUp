package com.example.nikhpand.dress_recommender;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nikhpand on 2/25/15.
 */
public class WeatherReport {


    WeatherParameters weatherparam = new WeatherParameters();

    static double temperature=0.0;
    public  WeatherParameters  updateWeatherData(final String city , final Context context) throws InterruptedException {
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(city, context);
                if (json == null) {
                    //TOAST
                } else {
                     renderWeather(json , weatherparam);
                }
              //  return 0.0;
            }
        }.start();

        Thread.sleep(3000);
        return  weatherparam;
    }

    private static void renderWeather(JSONObject json , WeatherParameters  weatherparam){
        try {

            weatherparam.temp = json.getJSONObject("main").getDouble("temp");
            weatherparam.humidity = json.getJSONObject("main").getString("humidity");
            weatherparam.pressure= json.getJSONObject("main").getString("pressure");
            weatherparam.wind = json.getJSONObject("wind").getDouble("speed");
            weatherparam.degreewind = json.getJSONObject("wind").getDouble("deg");
//            weatherparam.main = json.getJSONObject("weather").getString("main");
 //           weatherparam.main_desc = json.getJSONObject("weather").getString("description");
         //   weatherparam.icon=json.getJSONObject("weather").getString("icon");

          //  JSONArray details = new JSONArray();
             //JSONArray details = json.getJSONArray("weather").getJSONObject(0).get("icon");

            JSONObject details= json.getJSONArray("weather").getJSONObject(0);




          //  json.getJSONObject("Weather").
          //  weatherparam.main_desc=details.getString("description");
            weatherparam.icon = details.getString("icon");
            weatherparam.main_desc = details.getString("description");

            Log.d("ICON-0",weatherparam.icon);



        //   return json.getJSONObject("main").getDouble("temp");

        //    return json.getJSONObject("main").getDouble()

//            cityField.setText(json.getString("name").toUpperCase(Locale.US) +
//                    ", " +
//                    json.getJSONObject("sys").getString("country"));
//
//            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
//            JSONObject main = json.getJSONObject("main");
//
//            detailsField.setText(
//                    details.getString("description").toUpperCase(Locale.US) +
//                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
//                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");
//
//            currentTemperatureField.setText(
//                    String.format("%.2f", main.getDouble("temp"))+ " ℃");
//
//            DateFormat df = DateFormat.getDateTimeInstance();
//            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
//            updatedField.setText("Last update: " + updatedOn);
//
//            Log.d("Text" ,"I came at changeCity-5");
//            setWeatherIcon(details.getInt("id"),
//                    json.getJSONObject("sys").getLong("sunrise") * 1000,
//                    json.getJSONObject("sys").getLong("sunset") * 1000);
//
//            Intent i = new Intent(this , DressDisplay.class);
//            i.putExtra("temp",56);
//            startActivity(i);


        }catch(Exception e){
            e.printStackTrace();
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }

    }




            }

class WeatherParameters
{
    double temp;
    String humidity;
    String pressure;
    double wind;
    double min_temp;
    double max_temp;
    double degreewind;
    String main;
    String main_desc;
    String icon;
}


