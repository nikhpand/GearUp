package com.example.nikhpand.dress_recommender;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import android.widget.AdapterView.OnItemClickListener;


public class DressDisplay extends ActionBarActivity {

    private final static String STORETEXT="Shirt_4.txt";
    String filePath ;
    FileOutputStream outputStream;
    static int rain;
    static int wind;



    Double temp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Context context = getBaseContext();
        filePath = context.getFilesDir().getPath().toString() + "/" +STORETEXT;

        File myFile = new File(filePath);

        try {
            if (!myFile.exists()) {
                try {
                    myFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //   File file = new createNewFile("anish.txt");

                // First Time Need to write

                outputStream = openFileOutput(STORETEXT, getBaseContext().MODE_PRIVATE);
                outputStream.write("0-3|".getBytes());
                outputStream.write("1-4|".getBytes());
                outputStream.write("2-1|".getBytes());
                outputStream.write("3-2|".getBytes());
                outputStream.write("0-0|".getBytes());
                outputStream.write("1-0|".getBytes());
                outputStream.write("2-4|".getBytes());
                outputStream.write("3-0|".getBytes());
                outputStream.write("0-4|".getBytes());
                outputStream.write("1-0|".getBytes());
                outputStream.write("2-0|".getBytes());
                outputStream.write("3-0|".getBytes());
                outputStream.close();
                Log.d("TAG1", "Creating a File");
            }
        } catch (Exception e){

        }




        Bundle extra = getIntent().getExtras();
        rain = extra.getInt("rain_key");
        wind = extra.getInt("wind_key");
        temp = extra.getDouble("temp_key");
        Log.d("New_Activity_Temp", String.valueOf(temp));
        setContentView(R.layout.activity_dress_display);
//        ImageView imgView=(ImageView) findViewById(R.id.imgView);

//        Drawable drawable  = getResources().getDrawable(R.drawable.fb);
//        imgView.setImageDrawable(drawable);
//    //    setContentView(R.layout.activity_dress_display);


//        if(rain==0 && wind==0)
//            gridview.setAdapter(new ImageAdapterF(this));
//
//        if(rain==1 && wind==0)
//            gridview.setAdapter(new ImageAdapterFr(this));


        GridView gridview = (GridView) findViewById(R.id.gridview);
        // ImageAdapter imageAdapter = new

        if(rain==0 && wind==0)
             gridview.setAdapter(new ImageAdapter(this));

        if(rain==1 && wind==0)
            gridview.setAdapter(new ImageAdapterMr(this));


//        gridview.setOnTouchListener(onSwipeTouchListener);

//        gridview.setOnTouchListener(new OnSwipeTouchListener(context) {
//            public void onSwipeTop() {
//                Toast.makeText(DressDisplay.this, "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(DressDisplay.this, "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(DressDisplay.this, "left", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(DressDisplay.this, "bottom", Toast.LENGTH_SHORT).show();
//            }
//
//
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });


        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //   Toast.makeText(DressDisplay.this, "" + position, Toast.LENGTH_SHORT).show();

                try {


                    if (position == 0) {
                        Intent itrouser = new Intent(DressDisplay.this, Trouser.class);
                        startActivity(itrouser);
                    } else if (position == 1) {
                        Intent ishirt = new Intent(DressDisplay.this, Shirt.class);
                        startActivity(ishirt);
                    } else if (position == 2) {
                        Intent iouter = new Intent(DressDisplay.this, Outer.class);
                        startActivity(iouter);
                    } else if (position == 3) {
                        Intent ifoot = new Intent(DressDisplay.this, Foot.class);
                        startActivity(ifoot);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

//                try {
//
//
//                    InputStream inputStream = openFileInput(STORETEXT);
//
//                    if (inputStream != null) {
//                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                        String receiveString = "";
//                        StringBuilder stringBuilder = new StringBuilder();
//
//                        while ((receiveString = bufferedReader.readLine()) != null) {
//                            stringBuilder.append(receiveString);
//                        }
//
//                        inputStream.close();
//                        Log.d("NTAG1", stringBuilder.toString());
//                    }
//
//                }catch(Exception e){
//                    e.printStackTrace();
//
//                }
            }
        });
    }


}


class ImageAdapterMr extends BaseAdapter {

    private final static String STORETEXT = "Shirt_4.txt";
    String filePath;
    FileOutputStream outputStream;

    private Context mContext;

    public ImageAdapterMr(Context c) {
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
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        //int pos = myNewPos(position);
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.trouser_logo, R.drawable.shirt_logo , R.drawable.outer_wear, R.drawable.foot_logo};

    private Integer[] mThumbIds = {
            R.drawable.mp4main, R.drawable.shirt_logo, R.drawable.outer_wear, R.drawable.foot_logo , R.drawable.umbrella};


    public int myNewPos(int position) {
        Log.d("Tag1", "I came at myNewPos-1");
        try {

            InputStream inputStream = mContext.openFileInput(STORETEXT);

            if (inputStream != null) {
                Log.d("Tag1", "I came at myNewPos-2");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                Log.d("NTAG1", stringBuilder.toString());
                String tmpStr = stringBuilder.toString();
                StringTokenizer st = new StringTokenizer(tmpStr, "|");
                LinkedList<Node> myList = new LinkedList<>();
                int i = 3;
                while (i >= 0) {
                    String s1 = st.nextToken();
                    StringTokenizer nst = new StringTokenizer(s1, "-");
                    String p1 = nst.nextToken();
                    String p2 = nst.nextToken();
                    Node n = new Node(Integer.parseInt(p1), Integer.parseInt(p2));
                    myList.add(n);
                    i--;
                }
                Collections.sort(myList, new Comparator<Node>() {

                    public int compare(Node n1, Node n2) {
                        return n2.count - n1.count;
                    }
                });

                return myList.get(position).sid;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return position;
    }
    class Node{
        int sid;
        int count;
        Node(int s, int c){
            sid = s;
            count = c;
        }
    }
};


class ImageAdapter extends BaseAdapter {

    private final static String STORETEXT="Shirt_4.txt";
    String filePath ;
    FileOutputStream outputStream;

    private Context mContext;

    public ImageAdapter(Context c) {
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
            imageView.setLayoutParams(new GridView.LayoutParams(385, 385));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        //int pos = myNewPos(position);
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
//    private Integer[] mThumbIds = {
//            R.drawable.trouser_logo, R.drawable.shirt_logo , R.drawable.outer_wear, R.drawable.foot_logo};

    private Integer[] mThumbIds = {
            R.drawable.mp4main, R.drawable.shirt_logo , R.drawable.outer_wear, R.drawable.foot_logo};


    public int myNewPos(int position)
    {
        Log.d("Tag1", "I came at myNewPos-1");
        try {

            InputStream inputStream = mContext.openFileInput(STORETEXT);

            if (inputStream != null) {
                Log.d("Tag1", "I came at myNewPos-2");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                Log.d("NTAG1", stringBuilder.toString());
                String tmpStr = stringBuilder.toString();
                StringTokenizer st = new StringTokenizer(tmpStr,"|");
                LinkedList<Node> myList = new LinkedList<>();
                int i = 3;
                while(i >= 0){
                    String s1 = st.nextToken();
                    StringTokenizer nst = new StringTokenizer(s1,"-");
                    String p1 = nst.nextToken();
                    String p2 = nst.nextToken();
                    Node n = new Node(Integer.parseInt(p1),Integer.parseInt(p2));
                    myList.add(n);
                    i--;
                }
                Collections.sort(myList, new Comparator<Node>() {

                    public int compare(Node n1, Node n2) {
                        return n2.count - n1.count;
                    }
                });

                return myList.get(position).sid;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return position;
    }

    class Node{
        int sid;
        int count;
        Node(int s, int c){
            sid = s;
            count = c;
        }
    }
};
