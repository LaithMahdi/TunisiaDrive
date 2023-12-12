package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailCar extends AppCompatActivity {
    TextView nomCar, modeleCar, ageCar, description, disponibilite, typeCarburant, price, nbReview;
    ImageView carImage;
    Car selectedCar;
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_car);

        int carId = getIntent().getIntExtra("selectedCar", -1);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
         selectedCar = dbHelper.getCarById(carId);
        if (selectedCar != null) {
            nomCar = findViewById(R.id.nomCar);
            modeleCar = findViewById(R.id.modeleCar);
            ageCar = findViewById(R.id.ageCar);
            carImage = findViewById(R.id.imageCar);
            description = findViewById(R.id.decriptionCar);
            disponibilite = findViewById(R.id.disponibiliteCar);
            typeCarburant = findViewById(R.id.typeCarburantCar);
            price = findViewById(R.id.priceCar);
            nbReview = findViewById(R.id.nbReviewCar);

            nomCar.setText(Html.fromHtml("<b>Nom:</b> " + selectedCar.getNom()));
            modeleCar.setText(Html.fromHtml("<b>Modèle:</b> " + selectedCar.getModele()));
            ageCar.setText(Html.fromHtml("<b>Age:</b> " + selectedCar.getAge()+" années"));
            carImage.setImageResource(selectedCar.getImagePath());
            description.setText(Html.fromHtml("<b>Description:</b> " + selectedCar.getDescription()));
            if (selectedCar.getDisponibilite() == 1) {
                disponibilite.setText(Html.fromHtml("<b>Disponibilité:</b> Non"));
            } else {
                disponibilite.setText(Html.fromHtml("<b>Disponibilité:</b> Oui"));
            }
            typeCarburant.setText(Html.fromHtml("<b>Type carburant:</b> " + selectedCar.getTypeCarburant()));
            price.setText(Html.fromHtml("<b>Price:</b> <br/> " + selectedCar.getPrice()+" TND"));
            nbReview.setText(Html.fromHtml("<b>Nombre des reviews:</b> " + selectedCar.getNbReview()));
        }
    }

    public void rentCar(View view){


        Intent intent = new Intent(this, PayementDetail.class);
        intent.putExtra("car", selectedCar.getId());
        startActivity(intent);


    }
    public void backToHome(View view){
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        finish();
    }


}
