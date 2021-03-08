package com.example.trip.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trip.R;
import com.example.trip.User.UserDashboard;


public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;


    //variables
    ImageView backroundImag;
    TextView logoNameLine;

    //Animations
    Animation sideAnim,bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks

        backroundImag = findViewById(R.id.background_image);
       // logoNameLine = findViewById(R.id.logo_name_line);

        //Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //set Animations on elements
        backroundImag.setAnimation(sideAnim);
        backroundImag.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean( "firstTime",true );

                if (isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean( "firstTime",false );
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();

                }else {
                    Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                    startActivity(intent);
                    finish();

                }




            }
        },SPLASH_TIMER);
    }
}