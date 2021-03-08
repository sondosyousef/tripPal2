package com.example.trip.HelperClasses.HomeAdapter;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trip.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class discription extends AppCompatActivity {


    private static final String TAG = "discription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discription);
        Log.d(TAG, "onCreate: started.");


        String p1 = getIntent().getStringExtra("point1");
        String img1 = getIntent().getStringExtra("img_point1");
        String des1 = getIntent().getStringExtra("des1");
        String p2 = getIntent().getStringExtra("point2");
        String img2 = getIntent().getStringExtra("img_point2");
        String des2 = getIntent().getStringExtra("des2");
        String p3 = getIntent().getStringExtra("point3");
        String img3 = getIntent().getStringExtra("img_point3");
        String des3 = getIntent().getStringExtra("des3");
        String p4 = getIntent().getStringExtra("point4");
        String img4 = getIntent().getStringExtra("img_point4");
        String des4 = getIntent().getStringExtra("des4");

        Button pointOfINt = findViewById(R.id.pointOfInterst);
        pointOfINt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(discription.this,PointInCity.class);
                i.putExtra("point1",p1);
                i.putExtra("img_point1",img1);
                i.putExtra("des1",des1);
                i.putExtra("point2",p2);
                i.putExtra("img_point2",img2);
                i.putExtra("des2",des2);
                i.putExtra("point3",p3);
                i.putExtra("img_point3",img3);
                i.putExtra("des3",des3);
                i.putExtra("point4",p4);
                i.putExtra("img_point4",img4);
                i.putExtra("des4",des4);

                startActivity(i);

            }
        });

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_Url") && getIntent().hasExtra("image_name")&&getIntent().hasExtra("description")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_Url");
            String imageName = getIntent().getStringExtra("image_name");
            String dec = getIntent().getStringExtra("description");

            setImage(imageUrl, imageName,dec);
        }
    }


    private void setImage(String imageUrl, String imageName,String des){
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView name = findViewById(R.id.name);
        name.setText(imageName);

        ImageView image = findViewById(R.id.image1);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
        TextView descr=findViewById(R.id.description1);
        descr.setText(des);

    }
}