package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private int clientId;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

        if (getIntent().getExtras() != null) {
            clientId = getIntent().getExtras().getInt("clientId");
        }

        databaseHelper = new DatabaseHelper(this);

        TextView clientNameTextView = findViewById(R.id.clientNameTextView);
        RecyclerView reservationRecyclerView = findViewById(R.id.reservationRecyclerView);

        // Get client name from database based on clientId and set it to the TextView
        String clientName = databaseHelper.getClientNameById(clientId);
        clientNameTextView.setText(clientName);

        // Get reservations for the client from the database
        List<Reservation> reservations = databaseHelper.getReservationsByClientId(clientId);

        // Set up RecyclerView with ReservationAdapter
        ReservationAdapter adapter = new ReservationAdapter(reservations);
        reservationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reservationRecyclerView.setAdapter(adapter);
    }
    public void backToProfile(View view){
        Intent intent = new Intent(this,Profil.class);
        startActivity(intent);
        finish();
    }
}
