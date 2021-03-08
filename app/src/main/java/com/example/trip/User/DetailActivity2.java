package com.example.trip.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trip.HelperClasses.HomeAdapter.discriptionPoint;
import com.example.trip.R;

public class DetailActivity2 extends AppCompatActivity {
TextView title,descript;
ImageView img;
Button add_to_tour_plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        title = findViewById(R.id.title);
        descript=findViewById(R.id.des_location);
        img =findViewById(R.id.Img_location);
        add_to_tour_plan = findViewById( R.id.pointNablus);


        String imageName = getIntent().getStringExtra("title");
        String data = imageName ;


        add_to_tour_plan.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( DetailActivity2.this, addPlan.class);
                i.putExtra("data",data);
                startActivity(i);

            }
        } );


        String t = getIntent().getStringExtra("title");
        String dis = getIntent().getStringExtra("ds");
        String url = getIntent().getStringExtra("url");

        Glide.with(this)
                .asBitmap()
                .load(url)
                .into(img);


        title.setText(t);
        descript.setText(dis);
    }
}