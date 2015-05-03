package com.example.nikhpand.dress_recommender;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by nikhpand on 3/15/15.
 */


public class TrouserF extends ActionBarActivity {

    private final static String STORETEXT="Trouser_4f1.txt";
    private static final String DEBUG_TAG = "Swipe Direction :";
    String filePath ;
    FileOutputStream outputStream;
    public static Map posToDressId;


    static int index;


    Double temp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        index=0;

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
                outputStream.write("0-4|".getBytes());
                outputStream.write("1-0|".getBytes());
                outputStream.write("2-0|".getBytes());
                outputStream.write("3-0|".getBytes());
                outputStream.write("0-0|".getBytes());
                outputStream.write("1-4|".getBytes());
                outputStream.write("2-0|".getBytes());
                outputStream.write("3-0|".getBytes());
                outputStream.write("0-0|".getBytes());
                outputStream.write("1-0|".getBytes());
                outputStream.write("2-4|".getBytes());
                outputStream.write("3-0|".getBytes());
                outputStream.write("0-0|".getBytes());
                outputStream.write("1-0|".getBytes());
                outputStream.write("2-0|".getBytes());
                outputStream.write("3-4|".getBytes());
                outputStream.close();
                Log.d("TAG1", "Creating a File");
            }
        } catch (Exception e){

        }

//Nikhil
        posToDressId = new HashMap<Integer,Integer>();


        Bundle extra = getIntent().getExtras();
        //   Log.d("New_Activity_Temp", String.valueOf(temp));
        setContentView(R.layout.activity_trouser_f);


        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter_t(this));



        Thread thread = new Thread() {

            public void run() {
                GridView gridview = (GridView) findViewById(R.id.gridview);
                gridview.setAdapter(new ImageAdapter_ft(TrouserF.this));
            }
        };
        thread.start();

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(TrouserF.this, "" + position, Toast.LENGTH_SHORT).show();
                try {



                    InputStream inputStream = openFileInput(STORETEXT);

                    if (inputStream != null) {
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder stringBuilder = new StringBuilder();

//Nikhil New Start

                        int lineCount = 0;

                        while ((receiveString = bufferedReader.readLine()) != null) {
                            Log.d("Nikhil1", receiveString);

                            StringTokenizer st = new StringTokenizer(receiveString,"|");

                            while(st.hasMoreTokens()) {
                                String temp = st.nextToken();

//                                if (lineCount == position) {
                                Integer origImage = (Integer)posToDressId.get(position);
                                Integer origPosition = Arrays.asList(ImageAdapter_ft.mThumbIds).indexOf(origImage);
//                                Integer origPosition = ImageAdapter_f.mThumbIds.indexOf(origImage);
//                                if (posToDressId.get(position).equals(lineCount)) {
                                if (origPosition.equals(lineCount)) {
                                    StringTokenizer nst = new StringTokenizer(temp, "-");
                                    String p1 = nst.nextToken();
                                    String p2 = nst.nextToken();
                                    int p2Val = Integer.parseInt(p2);
                                    p2Val++;
                                    p2 = String.valueOf(p2Val);
                                    temp = p1 + "-" + p2;
                                }
                                stringBuilder.append(temp);
                                stringBuilder.append("|");
                                lineCount++;
                            }
                        }

                        inputStream.close();

                        outputStream = openFileOutput(STORETEXT, getBaseContext().MODE_PRIVATE);
                        outputStream.write(stringBuilder.toString().getBytes());
                        outputStream.close();

                        Log.d("Nikhil2", stringBuilder.toString());

//Nikhil New end
                    }

                }catch(Exception e){
                    e.printStackTrace();

                }
            }
        });

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                  Intent i;
                i = new Intent(TrouserF.this , TrouserFAdd.class);

                startActivity(i);
            }
        });
 }

    public void onBackPressed()
    {
        Intent i;
        i = new Intent(TrouserF.this , DressDisplayFemale.class);
        i.putExtra("temp_key" , 20);
        i.putExtra("rain_key" ,1);
        startActivity(i);

    }
}

class ImageAdapter_ft extends BaseAdapter {



  //  TrouserFAdd trouseraddobject = new TrouserFAdd();
    private final static String STORETEXT="Trouser_4f1.txt";
    String filePath ;
    FileOutputStream outputStream;

    private Context mContext;

    public ImageAdapter_ft(Context c) {
        mContext = c;
    }

    public int getCount() {
       // return TrouserAdd.femaleAddImages.length + mThumbIds.length;

        return TrouserAdd.femaleAddImages.size() + mThumbIds.length;
      //  return
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

        if(position > 3)
            {
                int loc = TrouserAdd.femaleAddImages.get( TrouserF.index);
                imageView.setImageResource(mThumbIdsfull[4+loc]);
                TrouserF.index++;
                return imageView;
            }
        int pos = myNewPos(position);
        imageView.setImageResource(mThumbIdsfull[pos]);
        TrouserF.posToDressId.put(position,mThumbIds[pos]);
        return imageView;
    }

    // references to our images
//    public static Integer[] mThumbIds = {
//            R.drawable.womens_trouser_1, R.drawable.womens_trouser_2 , R.drawable.womens_trouser_3, R.drawable.womens_trouser_3 };

    public static Integer[] mThumbIds = {R.drawable.ladiespant2 , R.drawable.ladiespant1 , R.drawable.ladiespannt4_2 , R.drawable.jeansw1 };
           /// R.drawable.dortmund, R.drawable.ball , R.drawable.bat, R.drawable.chelsea};

    public static Integer[] mThumbIdsfull = {
            R.drawable.ladiespant2 , R.drawable.ladiespant1 , R.drawable.ladiespannt4_2 , R.drawable.jeansw1 , R.drawable.womens_trouser_3, R.drawable.ladiespannt4_1, R.drawable.womens_trouser_2, R.drawable.fadd};
    /// R.drawable.dortmund, R.drawable.ball , R.drawable.bat, R.drawable.chelsea};


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

