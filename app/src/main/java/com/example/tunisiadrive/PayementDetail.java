package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PayementDetail extends AppCompatActivity {
    ImageView carImage;
    Car selectedCar;
    TextView nomCar;
    int userId;
    EditText startdate;
    EditText enddate;
    SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payement_detail);

        int carId = getIntent().getIntExtra("car", -1);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        selectedCar = dbHelper.getCarById(carId);

        sharedPreferencesManager =new  SharedPreferencesManager(this);
        userId= dbHelper.getClientIdByEmail(sharedPreferencesManager.getUserEmail());

        startdate=findViewById(R.id.dateStart);
        enddate=findViewById(R.id.dateEnd);
        if (selectedCar != null) {
            carImage = findViewById(R.id.imageCarRent);
            nomCar = findViewById(R.id.nomCarRent);
            carImage.setImageResource(selectedCar.getImagePath());
            nomCar.setText(selectedCar.getNom());

        }
    }

    public  void reservationCar(View view) {
        String startDateStr = startdate.getText().toString();
        String endDateStr = enddate.getText().toString();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate startDate = LocalDate.parse(startDateStr, formatter);
            LocalDate endDate = LocalDate.parse(endDateStr, formatter);

            long days = ChronoUnit.DAYS.between(startDate, endDate);
        int currentUserID = userId;
        int voitureId = selectedCar.getId();

        String reservationDate = days+" days";
        double montantTotal = selectedCar.getPrice()*days;

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        boolean isReservationCreated = dbHelper.createReservation(currentUserID, voitureId, reservationDate, montantTotal);

        if (isReservationCreated) {
            Toast.makeText(this, "Reservation Created Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to Create Reservation", Toast.LENGTH_SHORT).show();
        }
    }
        public void backToRentCar(View view){
        Intent intent = new Intent(this, DetailCar.class);
        intent.putExtra("selectedCar", selectedCar.getId());
        startActivity(intent);
        finish();
    }
}
