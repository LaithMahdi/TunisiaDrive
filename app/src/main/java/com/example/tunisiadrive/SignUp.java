package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    EditText email, password, confirmPassword;
    private FirebaseAuth mAuth;
    private Util util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmpassword);
        util = new Util();
    }

    public void handleSignUp(View view) {
        if (email.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
            util.showToast(getBaseContext(), "Please enter your email address and password");
        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            util.showToast(getBaseContext(), "Password and confirm password are not match");
        } else {
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                goToSignIN(view);
                            } else {
                                Log.w("Login", "signInWithEmail:failure", task.getException());
                                Util.showToast(getBaseContext(), "Authentication failed.");
                            }
                        }
                    });
        }
    }

    public void goToSignIN(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}