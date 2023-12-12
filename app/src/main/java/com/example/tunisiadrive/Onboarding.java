package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class Onboarding extends AppCompatActivity {
    private SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding);
        sharedPreferencesManager = new SharedPreferencesManager(this);
    }

    public void handleClick(View view)  {
        sharedPreferencesManager.setIsOnboard(true);
        Intent intent = new Intent(Onboarding.this, Login.class);
        startActivity(intent);
        finish();
    }
}
