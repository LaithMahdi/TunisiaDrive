package com.example.tunisiadrive;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfil extends AppCompatActivity {
    FirebaseFirestore firestore;
    SharedPreferencesManager sharedPreferencesManager;

    void addToFirebase(String nom, String email, String adresse, String numeroTelephone,String dateOfBirth,String genderes) {
        Map<String, Object> clientMap = new HashMap<>();
        clientMap.put("nom", nom);
        clientMap.put("email", email);
        clientMap.put("adresse", adresse);
        clientMap.put("numeroTelephone", numeroTelephone);
        clientMap.put("dateOfBirth",dateOfBirth);
        clientMap.put("gender",genderes);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("clients")
                .document(sharedPreferencesManager.getUserUid())
                .set(clientMap)
                .addOnSuccessListener(aVoid -> {
               })
                .addOnFailureListener(e -> {
                    Toast.makeText(EditProfil.this, "Failed to add client to Firestore", Toast.LENGTH_SHORT).show();
                });
    }
    public void backToProfil(View view) {
        Intent intent = new Intent(this,Profil.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil);

        firestore = FirebaseFirestore.getInstance();
        sharedPreferencesManager = new SharedPreferencesManager(this);

        EditText nomEditText = findViewById(R.id.fullname);
        EditText emailEditText = findViewById(R.id.emailadress);
        EditText adresseEditText = findViewById(R.id.adress);
        EditText numeroTelephoneEditText = findViewById(R.id.phonenumber);
        EditText dateOfBirth = findViewById(R.id.dateofbirth);
        RadioButton r1= findViewById(R.id.homme);
        RadioButton r2= findViewById(R.id.femme);
        emailEditText.setText(sharedPreferencesManager.getUserEmail().toString());
        Button okButton = findViewById(R.id.button);

        if (sharedPreferencesManager.isAuthenticated()) {
            firestore.collection("clients")
                    .document(sharedPreferencesManager.getUserUid())
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String nom = documentSnapshot.getString("nom");
                            String email = documentSnapshot.getString("email");
                            String adresse = documentSnapshot.getString("adresse");
                            String numeroTelephone = documentSnapshot.getString("numeroTelephone");
                            String date= documentSnapshot.getString("dateOfBirth");
                            String gender = documentSnapshot.getString("gender");

                            nomEditText.setText(nom);
                            emailEditText.setText(email);
                            adresseEditText.setText(adresse);
                            numeroTelephoneEditText.setText(numeroTelephone);
                            dateOfBirth.setText(date);
                            System.out.println("gender : " + gender);
                            if ("Homme".equals(gender)) {
                                r1.setChecked(true);
                            } else {
                                r2.setChecked(true);
                            }
                        } else {
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(EditProfil.this, "Failed to fetch user data from Firestore", Toast.LENGTH_SHORT).show();
                    });
        }

        okButton.setOnClickListener(v -> {
            String nom = nomEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String adresse = adresseEditText.getText().toString();
            String numeroTelephone = numeroTelephoneEditText.getText().toString();

            DatabaseHelper dbHelper = new DatabaseHelper(EditProfil.this);

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_NOM, nom);
            values.put(DatabaseHelper.COLUMN_EMAIL, email);
            values.put(DatabaseHelper.COLUMN_ADRESSE, adresse);
            values.put(DatabaseHelper.COLUMN_NUMERO_TELEPHONE, numeroTelephone);

            long clientId = db.insert(DatabaseHelper.TABLE_CLIENT, null, values);

            db.close();
            String genderes;
            if (clientId != -1) {
                if (r1.isChecked()){
                     genderes= "Homme";
                }else {
                    genderes= "Femme";
                }
                addToFirebase(nom, email, adresse, numeroTelephone,dateOfBirth.getText().toString(),genderes);

                Toast.makeText(EditProfil.this, "Client added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,Home.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(EditProfil.this, "Failed to add client", Toast.LENGTH_SHORT).show();
            }
        });
    }}