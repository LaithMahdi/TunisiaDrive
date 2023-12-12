package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Profil extends AppCompatActivity {
    private SharedPreferencesManager sharedPreferencesManager;
    private FirebaseAuth mAuth;
    TextView mail, uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferencesManager = new SharedPreferencesManager(this);
        mail = findViewById(R.id.profilName);
        uid = findViewById(R.id.profilID);
        mail.setText(Html.fromHtml("<b>Email: </b>"+sharedPreferencesManager.getUserEmail()));
        uid.setText(Html.fromHtml("<b>UID: </b>"+sharedPreferencesManager.getUserUid()));
    }

    public void logout(View view) {
        mAuth.signOut();
        sharedPreferencesManager.setIsAuthenticated(false);
        sharedPreferencesManager.setIsUser(null,null);
        Intent intent = new Intent(Profil.this,Login.class);
        startActivity(intent);
        finish();
    }

    public  void goToEditProfile(View view) {
        Intent intent = new Intent(this,EditProfil.class);
        startActivity(intent);
    }
    public  void history(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

      int id=  dbHelper.getClientIdByEmail(sharedPreferencesManager.getUserEmail());
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("clientId", id);
        startActivity(intent);
    }
    public  void about(View view){}
    public void backToHome(View view){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        finish();
    }
}
