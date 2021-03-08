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

public class PointInCity extends AppCompatActivity {
    Button btn1,btn2,btn3,btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_in_city);
        btn1=findViewById(R.id.btn_des1);
        btn2=findViewById(R.id.btn_des2);
        btn3=findViewById(R.id.btn_des3);
        btn4=findViewById(R.id.btn_des4);

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


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PointInCity.this,des1.class);
                i.putExtra("point1",p1);
                i.putExtra("img_point1",img1);
                i.putExtra("des1",des1);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PointInCity.this,des1.class);
                i.putExtra("point2",p2);
                i.putExtra("img_point2",img2);
                i.putExtra("des2",des2);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PointInCity.this,des1.class);
                i.putExtra("point3",p3);
                i.putExtra("img_point3",img3);
                i.putExtra("des3",des3);
                startActivity(i);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PointInCity.this,des1.class);
                i.putExtra("point4",p4);
                i.putExtra("img_point4",img4);
                i.putExtra("des4",des4);
                startActivity(i);
            }
        });


        getIncomingIntent();

    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("point1") && getIntent().hasExtra("img_point1")
                &&getIntent().hasExtra("point2") && getIntent().hasExtra("img_point2")
                &&getIntent().hasExtra("point3") && getIntent().hasExtra("img_point3")
                &&getIntent().hasExtra("point4") && getIntent().hasExtra("img_point4")
                &&getIntent().hasExtra("des1") && getIntent().hasExtra("des2")
                &&getIntent().hasExtra("des3") && getIntent().hasExtra("des4")){


            String imageUrl = getIntent().getStringExtra("img_point1");
            String imageName = getIntent().getStringExtra("point1");
            String imageUrl2 = getIntent().getStringExtra("img_point2");
            String imageName2 = getIntent().getStringExtra("point2");
            String imageUrl3 = getIntent().getStringExtra("img_point3");
            String imageName3 = getIntent().getStringExtra("point3");
            String imageUrl4 = getIntent().getStringExtra("img_point4");
            String imageName4 = getIntent().getStringExtra("point4");


            setImage(imageUrl, imageName,imageName2,imageUrl2,imageName3,imageUrl3,imageName4,imageUrl4);
        }
    }

    private void setImage(String imageUrl, String imageName,String imageName2,String imageUrl2,String imageName3,String imageUrl3,String imageName4,String imageUrl4 ) {
        TextView name = findViewById(R.id.point1);
        name.setText(imageName);

        ImageView image = findViewById(R.id.img_point1);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
        TextView name2 = findViewById(R.id.point2);
        name2.setText(imageName2);

        ImageView image2 = findViewById(R.id.img_point2);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl2)
                .into(image2);
        TextView name3 = findViewById(R.id.point3);
        name3.setText(imageName3);

        ImageView image3 = findViewById(R.id.img_point3);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl3)
                .into(image3);
        TextView name4 = findViewById(R.id.point4);
        name4.setText(imageName4);

        ImageView image4 = findViewById(R.id.img_point4);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl4)
                .into(image4);
    }
}