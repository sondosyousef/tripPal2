package com.example.trip.HelperClasses.HomeAdapter;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trip.R;
import com.example.trip.User.ContectAdapter;
import com.example.trip.User.LvItem;
import com.example.trip.User.NotifierAlarm;
import com.example.trip.User.ReminderActivity;
import com.example.trip.User.Reminders;
import com.example.trip.User.addPlan;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class discriptionPoint extends AppCompatActivity {

    ListView ListV;
    ArrayList<LvItem> arrayList = new ArrayList<>();
    TextView empty;
    String title , t ;
    private static final String TAG = "discription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discription_point);
        Log.d(TAG, "onCreate: started.");
        String imageName = getIntent().getStringExtra("image_name");
        String cityName = getIntent().getStringExtra("city_name");
        String data = imageName + "_in_" + cityName;
        getIncomingIntent();
        Button addPlane= findViewById(R.id.pointOfInterst);
        addPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(discriptionPoint.this, addPlan.class);
                i.putExtra("data",data);
                startActivity(i);

            }
        });
       Button webView = (Button)findViewById(R.id.disc_review);

        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwiP6aLf3tfuAhVNwKQKHX0gDQ4QPAgI"));
                startActivity(intent);
            }
        });
    }



    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_Url") && getIntent().hasExtra("image_name")&&getIntent().hasExtra("description")&&getIntent().hasExtra("city_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_Url");
            String imageName = getIntent().getStringExtra("image_name");
            String dec = getIntent().getStringExtra("description");
            String city1 = getIntent().getStringExtra("city_name");

            setImage(imageUrl, imageName,dec,city1);

        }
    }


    private void setImage(String imageUrl, String imageName,String des,String city1){
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView name = findViewById(R.id.namePoint);
        name.setText(imageName);

        ImageView image1 = findViewById(R.id.imgPoint);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image1);
        TextView descr=findViewById(R.id.description2);
        descr.setText(des);

        TextView city = findViewById(R.id.pointCity);
        city.setText(city1);


    }
}