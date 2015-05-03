package com.example.nikhpand.dress_recommender;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

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
    String weatherCity;
    byte[] iconData;
    String updatedCity;

    WeatherParameters weatherparam = new WeatherParameters();
    WeatherReport weatherreport = new WeatherReport();

//    SharedPreferences sharedpreferences;
    private static final int RESULT_SETTINGS = 1;
//
    double temp1;
//    String pressure;
//    String humidity;
//    Double wind;
//    Double winddegree;
//    String main;
//    String main_desc;
//    String icon;
//    String weatherCity;




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
        weatherCity = extra.getString("city_key");

        updatedCity = weatherCity;

        temperature = (temperature * 9/5) + 32;
        wind = wind*.6;
        wind = Math.floor(wind*100)/100;

       // icon = "10n";

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




        //cityText.setText("Bryan US");

            cityText.setText(weatherCity);

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
                        "\n" + "Wind Speed: " + wind + "mph" + "\n");

        temp.setText(
                String.format("%.2f", temperature)+ " °F");


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





                if (sharedpreferences.contains("gender")) {
                    Intent i;

                    String gen = sharedpreferences.getString("gender", "defualt");


                    Set<String> workd = sharedpreferences.getStringSet("workingday", new HashSet<String>());
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                    Date d = new Date();
                    String dayOfTheWeek = sdf.format(d);


                    if (gen.equals("Male")) {
                        if (sharedpreferences.contains("workingday")) {
                            Log.d("WorkingDay= ", "WorkingDay= ");
                            if (workd.contains(dayOfTheWeek)) {
                                Log.d("dayOfTheWeek22 = ", "dayOfTheWeek22");
                                i = new Intent(WeatherDisplay.this, FormalDressDisplay.class);
                                i.putExtra("temp_key", temperature);
                                i.putExtra("city_key", updatedCity);
                                startActivity(i);
                            } else {
                                i = new Intent(WeatherDisplay.this, DressDisplay.class);
                                i.putExtra("temp_key", temperature);
                                i.putExtra("city_key", updatedCity);
                                if (icon.equals("10n") || icon.equals("10d"))
                                    i.putExtra("rain_key", 1);
                                i.putExtra("wind_key", 0); //-----> For Windy Weather
                                startActivity(i);
                            }
                        } else {


                            i = new Intent(WeatherDisplay.this, DressDisplay.class);
                            i.putExtra("temp_key", temperature);
                            i.putExtra("city_key", updatedCity);
                            if (icon.equals("10n") || icon.equals("10d"))
                                i.putExtra("rain_key", 1);
                            i.putExtra("wind_key", 0); //-----> For Windy Weather
                            startActivity(i);
                        }

                    } else {
                        if (sharedpreferences.contains("workingday")) {
                            Log.d("WorkingDay= ", "WorkingDay= ");
                            if (workd.contains(dayOfTheWeek)) {
                                Log.d("dayOfTheWeek22 = ", "dayOfTheWeek22");
                                i = new Intent(WeatherDisplay.this, FormalDressDisplayFemale.class);
                                i.putExtra("temp_key", temperature);
                                i.putExtra("city_key", updatedCity);
                                startActivity(i);
                            } else {
                                i = new Intent(WeatherDisplay.this, DressDisplayFemale.class);
                                i.putExtra("temp_key", temperature);
                                i.putExtra("city_key", updatedCity);
                                if (icon.equals("10n") || icon.equals("10d"))
                                    i.putExtra("rain_key", 1);
                                i.putExtra("wind_key", 0); //-----> For Windy Weather
                                startActivity(i);
                            }
                        } else {
                            i = new Intent(WeatherDisplay.this, DressDisplayFemale.class);
                            i.putExtra("temp_key", temperature);
                            i.putExtra("city_key", updatedCity);
                            if (icon.equals("10n") || icon.equals("10d"))
                                i.putExtra("rain_key", 1);
                            i.putExtra("wind_key", 0); //-----> For Windy Weather

                            startActivity(i);
                            Log.d("Video", "Video-3");
                        }

                    }
                }


            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_settings:
                Intent i = new Intent(this, UserSettingActivity.class);
                startActivityForResult(i, RESULT_SETTINGS);
                break;
            case R.id.change_city:
                showInputDialog();

        }

        return true;

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Log.d("TAG" , I came on menu)

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void showInputDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
            }
        });
        builder.show();
    }

    public void changeCity(String city){

        weatherCity= city;
//        WeatherFragment wf = (WeatherFragment)getSupportFragmentManager()
//                .findFragmentById(R.id.container);
//        wf.changeCity(city);
//        new CityPreference(this).setCity(city);
//        wf.changeCity(mAddressOutput);
//        new CityPreference(this).setCity(mAddressOutput);

//                wf.changeCity("Bryan US");
//        new CityPreference(this).setCity("Bryan US");
        try {
            weatherparam = weatherreport.updateWeatherData(city, getApplicationContext());

//          JSONWeatherTask task = new JSONWeatherTask();
//          task.execute(new String[]{city});

        }catch(Exception e){

        }
        Log.d("Temp", String.valueOf(weatherparam.temp));
        Log.d("Pressure", String.valueOf(weatherparam.pressure));
        Log.d("Humidity", String.valueOf(weatherparam.humidity));
        Log.d("ICON-4", String.valueOf(weatherparam.icon));

        temp1 = weatherparam.temp;
        pressure  =String.valueOf(weatherparam.pressure);
        humidity  = String.valueOf(weatherparam.humidity);
        wind = weatherparam.wind;
        winddegree= weatherparam.degreewind;
        main = weatherparam.main;
        main_desc=weatherparam.main_desc;
        icon = weatherparam.icon;


        Intent i = new Intent(this , WeatherDisplay.class);
        i.putExtra("temp_key" , temp1);
        i.putExtra("pressure_key" , pressure);
        i.putExtra("humidity_key" , humidity);
        i.putExtra("wind_key" , wind);
        i.putExtra("winddegree_key" , winddegree);
        i.putExtra("main_key",main);
        i.putExtra("maindesc_key" , main_desc);
        i.putExtra("icon_key" , icon);
        i.putExtra("city_key" , weatherCity);

        //    Log.d("ICON", icon);



        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
