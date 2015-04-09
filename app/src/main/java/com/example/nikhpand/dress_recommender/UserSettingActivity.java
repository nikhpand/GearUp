package com.example.nikhpand.dress_recommender;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


public class UserSettingActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences sharedpreferences;

        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);


        //  addPreferencesFromResource(R.xml.settings);

        setContentView(R.layout.activity_setting_user);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioButton rbM = (RadioButton)findViewById(R.id.radio_male);
                RadioButton rbF = (RadioButton)findViewById(R.id.radio_female);

                boolean isM = rbM.isChecked();
                boolean isF = rbF.isChecked();

                String Sex;

                if(isM){
                    Sex = "Male";
                }else if(isF){
                    Sex = "Female";
                }else{
                    Sex = "SexUnknown";
                }

//                String Sex = sex.getText().toString();
                Log.d("DEbug", Sex);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("gender", Sex);


                editor.commit();

                  Intent i;
                  i = new Intent(UserSettingActivity.this , MainActivity.class);
                //  i.putExtra("temp_key" , temp);
                  startActivity(i);

            }

            public void onRadioButtonClicked(View v){}
        });

    }
}


