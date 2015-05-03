package com.example.nikhpand.dress_recommender;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class TrouserFAdd extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trouser_fadd);

                GridView gridview = (GridView) findViewById(R.id.gridview);
                gridview.setAdapter(new ImageAdapter_ta(TrouserFAdd.this));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TrouserAdd.femaleAddImages.add(position);

             //   Toast.makeText(TrouserFAdd.this, "" + position, Toast.LENGTH_SHORT).show();

                }
        });

            final Button button = (Button) findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i;
                i = new Intent(TrouserFAdd.this , TrouserF.class);
                startActivity(i);
            }
        });


    }







};

class ImageAdapter_ta extends BaseAdapter {

    String filePath ;
    FileOutputStream outputStream;

    private Context mContext;

    public ImageAdapter_ta(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }


        imageView.setImageResource(mThumbIds[position]);
       // TrouserFAdd.posToDressId.put(position,mThumbIds[position]);
        return imageView;
    }



    // references to our images
//    public static Integer[] mThumbIds = {
//            R.drawable.fadd_1, R.drawable.f_add_2 , R.drawable.f_add_3, R.drawable.f_add_4};

    public static Integer[] mThumbIds = {
             R.drawable.womens_trouser_3, R.drawable.ladiespannt4_1, R.drawable.womens_trouser_2, R.drawable.fadd};



    //  R.drawable.dortmund, R.drawable.ball , R.drawable.bat, R.drawable.chelsea };


    class Node{
        int sid;
        int count;
        Node(int s, int c){
            sid = s;
            count = c;
        }
    }
};
