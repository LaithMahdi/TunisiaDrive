package com.example.tunisiadrive;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth mAuth;
    private Util util;
    private SharedPreferencesManager sharedPreferencesManager;
    LottieAnimationView animationView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        util = new Util();
        sharedPreferencesManager = new SharedPreferencesManager(this);
        animationView = findViewById(R.id.animationView);
        animationView.setVisibility(View.GONE);
        button =findViewById(R.id.bntRent);
        button.setVisibility(View.VISIBLE);
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }

    public void login(View view) {
        email = findViewById(R.id.email);
        String emaill = email.getText().toString();
        DatabaseHelper dbHelper = new DatabaseHelper(Login.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_EMAIL, emaill);
        long result = db.insert(DatabaseHelper.TABLE_CLIENT, null, values);

        db.close();
        animationView.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
        if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
            util.showToast(getBaseContext(), "Please enter your email address and password");
            animationView.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
        } else {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                sharedPreferencesManager.setIsAuthenticated(true);
                                Intent intent = new Intent(Login.this, Home.class);
                                sharedPreferencesManager.setIsUser(user.getEmail(), user.getUid());                                startActivity(intent);
                                animationView.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);

                                finish();
                            } else {
                                animationView.setVisibility(View.GONE);
                                button.setVisibility(View.VISIBLE);
                                Log.w("Login", "signInWithEmail:failure", task.getException());
                                util.showToast(getBaseContext(), "Authentication failed.");
                            }
                        }
                    });
        }
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private boolean isEmailExists(String email, SQLiteDatabase db) {
        String[] columns = {DatabaseHelper.COLUMN_EMAIL};
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(DatabaseHelper.TABLE_CLIENT, columns, selection, selectionArgs, null, null, null);

        boolean exists = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }

        return exists;
    }
}
