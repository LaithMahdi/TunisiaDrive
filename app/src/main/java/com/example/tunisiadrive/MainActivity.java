package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 2000;
    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        sharedPreferencesManager = new SharedPreferencesManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToNextScreen();
            }
        }, SPLASH_TIME_OUT);
    }

    private void navigateToNextScreen() {
        if (sharedPreferencesManager.isOnboard()) {
            if (sharedPreferencesManager.isAuthenticated()) {
                startActivity(new Intent(MainActivity.this, Home.class));
            } else {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        } else {
            startActivity(new Intent(MainActivity.this, Onboarding.class));
        }
        finish();
    }

}
