package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class ForgotPassword extends AppCompatActivity {
    private EditText email;
    private FirebaseAuth mAuth;
    private Util util;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        email = findViewById(R.id.email);
        util = new Util();
        mAuth = FirebaseAuth.getInstance();
    }

    public void handleForgotPassword(View view) {
        String userEmail = email.getText().toString().trim();
        if (userEmail.isEmpty()) {
            util.showToast(getBaseContext(), "Please enter your email address");
        } else {
            mAuth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                util.showToast(getBaseContext(), "Password reset email sent. Check your inbox.");
                                back(view);
                            } else {
                                Exception exception = task.getException();
                                if (exception instanceof FirebaseAuthInvalidUserException) {
                                    util.showToast(getBaseContext(), "No account found with this email.");
                                } else {
                                    util.showToast(getBaseContext(), "Failed to send password reset email.");
                                }
                            }
                        }
                    });
        }
    }

    public void back(View view) {
        Intent intent = new Intent(ForgotPassword.this,Login.class);
        startActivity(intent);
        finish();
    }


}