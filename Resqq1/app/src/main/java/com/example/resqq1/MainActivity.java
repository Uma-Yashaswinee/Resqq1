package com.example.resqq1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    DisplayMetrics displayMetrics;
    int height = 100, width = 0;
    private PrefManager prefManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
              /*  Intent i = new Intent(MainActivity.this, WelcomeActvity.class);
                startActivity(i);*/
                // close this activity
                //finish();
                launchHomeScreen();
            }
        },SPLASH_TIME_OUT);
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(true);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();

    }
}