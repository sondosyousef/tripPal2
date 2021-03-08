package com.example.trip.HelperClasses.HomeAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trip.R;
import com.example.trip.User.addPlan;

public class des1 extends AppCompatActivity {
    TextView name;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des1);
        name = findViewById(R.id.n1);
        getIncomingIntent();
        String s = name.getText().toString();

        Button addPlan= findViewById(R.id.pointOfInterst1);
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(des1.this, com.example.trip.User.addPlan.class);
                i.putExtra("data",s);
                startActivity(i);


            }
        });



    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("point1") && getIntent().hasExtra("img_point1")&&getIntent().hasExtra("des1")){


            String imageUrl = getIntent().getStringExtra("img_point1");
            String imageName = getIntent().getStringExtra("point1");
            String dec = getIntent().getStringExtra("des1");

            setImage(imageUrl, imageName,dec);
        }
        else if(getIntent().hasExtra("point2") && getIntent().hasExtra("img_point2")&&getIntent().hasExtra("des2")){
            String imageUrl = getIntent().getStringExtra("img_point2");
            String imageName = getIntent().getStringExtra("point2");
            String dec = getIntent().getStringExtra("des2");

            setImage(imageUrl, imageName,dec);
        }
        else if(getIntent().hasExtra("point3") && getIntent().hasExtra("img_point3")&&getIntent().hasExtra("des3")){
            String imageUrl = getIntent().getStringExtra("img_point3");
            String imageName = getIntent().getStringExtra("point3");
            String dec = getIntent().getStringExtra("des3");

            setImage(imageUrl, imageName,dec);
        }
        else if(getIntent().hasExtra("point4") && getIntent().hasExtra("img_point4")&&getIntent().hasExtra("des4")){
            String imageUrl = getIntent().getStringExtra("img_point4");
            String imageName = getIntent().getStringExtra("point4");
            String dec = getIntent().getStringExtra("des4");

            setImage(imageUrl, imageName,dec);
        }

    }

    private void setImage(String imageUrl, String imageName, String dec) {

        name.setText(imageName);

        ImageView image = findViewById(R.id.imgPoint11);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
        TextView descr=findViewById(R.id.description21);
        descr.setText(dec);


    }
}