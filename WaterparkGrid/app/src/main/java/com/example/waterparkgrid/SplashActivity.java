package com.example.waterparkgrid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Use a handler to delay the transition to the login activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity (Login screen) after the splash screen
                Intent i = new Intent(SplashActivity.this, MainLogin.class);
                startActivity(i);
                finish(); // Close the SplashActivity
            }
        }, SPLASH_TIME_OUT); // Delay in milliseconds (3000ms = 3 seconds)
    }
}
