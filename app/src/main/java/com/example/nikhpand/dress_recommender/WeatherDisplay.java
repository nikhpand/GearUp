package com.example.nikhpand.dress_recommender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import static java.lang.Thread.sleep;


public class WeatherDisplay extends ActionBarActivity {


    SharedPreferences sharedpreferences;
    Double temperature;
    String pressure;
    String humidity;
    Double wind;
    Double winddegree;
    String main;
    String main_desc;
    String icon;
    byte[] iconData;


    TextView  cityText , temp , condDescr , hum , press , windSpeed , windDeg ,detailsField;
    ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MediaPlayer[] myplayer = new MediaPlayer[1];

        Bundle extra = getIntent().getExtras();
        temperature = extra.getDouble("temp_key");
        pressure = extra.getString("pressure_key");
        humidity = extra.getString("humidity_key");
        wind = extra.getDouble("wind_key");
        winddegree = extra.getDouble("winddegree_key");
        main_desc = extra.getString("maindesc_key");
        icon = extra.getString("icon_key");



        icon = "01d";

        setContentView(R.layout.activity_weather_display_rain);

        //If icon is rainy
        if (icon.equals("10n")) {
           // setContentView(R.layout.activity_weather_display_rain);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.n10);
            Thread thread = new Thread() {

                public void run() {
                    myplayer[0] = MediaPlayer.create(WeatherDisplay.this, R.raw.rain);
                    myplayer[0].start();
                }

            };
            thread.start();


        } else if (icon.equals("10d"))
        {
           // setContentView(R.layout.activity_weather_display_rain);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.d10);
            Thread thread = new Thread() {

                public void run() {
                    myplayer[0] = MediaPlayer.create(WeatherDisplay.this, R.raw.rain);
                    myplayer[0].start();
                }

            };
            thread.start();


        }
        //Sunny
        else if (icon.equals("01d"))
        {
           // setContentView(R.layout.activity_weather_display_clear_day);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.d01);
        }
        else if (icon.equals("01n"))
        {
           // setContentView(R.layout.activity_weather_display_clear_night);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.n01);
        }
        //Snowy
        else if (icon.equals("13d"))
        {
          //  setContentView(R.layout.activity_weather_display_day_snow);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.d13);
        }
        else if (icon.equals("13n"))
        {
          //  setContentView(R.layout.activity_weather_display_night_snow);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.n13);
        }
        else if (icon.equals("02d") || icon.equals("04d"))
        {
          //  setContentView(R.layout.activity_weather_display_cloudy);
            imgView = (ImageView) findViewById(R.id.condIcon);
            imgView.setImageResource(R.drawable.n13);
        }
        else
        {
          //  setContentView(R.layout.activity_weather_display);
            imgView = (ImageView) findViewById(R.id.condIcon);
          //  setContentView(R.layout.activity_weather_display);
          //  imgView.setImageResource(R.drawable.n10);
        }



        Log.d("New_Activity_Temp", String.valueOf(temperature));

        Log.d("ICON-2", icon);

        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);




        cityText.setText("Bryan US");

//        temp.setText(temperature.toString());
//        press.setText(pressure);
//        hum.setText(humidity);
//        windSpeed.setText(wind.toString());
//        windDeg.setText(winddegree.toString());
//        condDescr.setText(main_desc);
//        condDescr.setText(main_desc);

        detailsField = (TextView) findViewById(R.id.details_field);

        detailsField.setText(
                main_desc.toUpperCase(Locale.US) +
                        "\n" + "Humidity: " + humidity + "%" +
                        "\n" + "Pressure: " + pressure + " hPa" +
                        "\n" + "Wind Speed:" + wind + "Kmph" + "\n");

        temp.setText(
                String.format("%.2f", temperature)+ " ℃");


//        Bitmap img = null;
//
//        if (iconData != null && iconData.length > 0) {
//            Log.d("Anish" , "I come here");
//            img = BitmapFactory.decodeByteArray(iconData, 0, iconData.length);
//
//        }
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        imgView.setImageBitmap(img);






      //  imgView.setImageResource(R.drawable.sunny);
       // temp.setText(temperature);

        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        final Button button = (Button) findViewById(R.id.Cloth_page);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {





                if (sharedpreferences.contains("gender"))
                {
                    Intent i;

                    String gen = sharedpreferences.getString("gender" , "defualt");

                    if(gen.equals("Male"))
                    {
                        i = new Intent(WeatherDisplay.this , DressDisplay.class);
                        i.putExtra("temp_key" , temperature);
                        startActivity(i);
                    }
                    else
                    {
//                        Log.d("Video", "Video-1");
//                        Intent myIntent = new Intent(WeatherDisplay.this, VideoViewActivity.class);
//                        startActivity(myIntent);
//
//                        try {
//                            sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        Log.d("Video", "Video-2");
                        i = new Intent(WeatherDisplay.this , DressDisplayFemale.class);
                        i.putExtra("temp_key" , temperature);
                        if(icon.equals("10n") || icon.equals("10d"))
                            i.putExtra("rain_key" , 1);
                            i.putExtra("wind_key" , 0); //-----> For Windy Weather

                        startActivity(i);
                        Log.d("Video", "Video-3");
                    }

                }



            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}
