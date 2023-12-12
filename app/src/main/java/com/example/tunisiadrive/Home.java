package com.example.tunisiadrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private List<Car> cars;
    private DatabaseHelper dbHelper;
    private RecyclerView carRecyclerView;
    private SharedPreferencesManager sharedPreferencesManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        dbHelper = new DatabaseHelper(this);
        sharedPreferencesManager = new SharedPreferencesManager(this);
        initializeCategoryRecyclerView();
        retrieveCarsFromDatabase();
        setupCarRecyclerView();
        if (!sharedPreferencesManager.isAddCars()) {
            addCarsToDatabase();
            sharedPreferencesManager.setIsAddCars(true);
        }
    }

    private void initializeCategoryRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Ford", R.drawable.ford));
        categories.add(new Category(2, "Hyundai", R.drawable.hyundai));
        categories.add(new Category(3, "Mazda", R.drawable.mazda));
        categories.add(new Category(2, "Toyota", R.drawable.toyota));
        CategoryAdapter adapter = new CategoryAdapter(categories, this);
        recyclerView.setAdapter(adapter);
    }

    private void retrieveCarsFromDatabase() {
        cars = dbHelper.getAllCars();
    }

    private void addCarsToDatabase() {
        cars = new ArrayList<>();
        cars.add(new Car("Chevrolet Prisma", "Chevrolet", 5, 0, "Essence ", R.drawable.chevrolet_prisma, 150, 40,"La Chevrolet Prisma est une berline 3 volumes, version tricorps de la Celta basée sur l'Opel Corsa de 1993. Elle est vendue exclusivement au Brésil depuis 2006."));
        cars.add(new Car("Fiat Argo", "Fiat", 7, 0, "Essence", R.drawable.car_fiat_argo, 70, 20,"La Fiat Argo est une citadine produite par le constructeur automobile italien Fiat depuis 2017 dans son usine géante Fiat-Betim au Brésil."));
        cars.add(new Car("Mazda 3 2018", "Mazda", 6, 0, "Essence / Diesel", R.drawable.car_mazda, 50, 120,"La Mazda 3, ou Mazda Axela (nom utilisé au Japon pour les trois premières générations), est un modèle de berline compacte du constructeur automobile japonais Mazda. "));
        cars.add(new Car("Hyundai Grand i10", "Hyundai", 3, 0, "Essence", R.drawable.hyundai_i10, 45, 17,"La i10 est une automobile du constructeur automobile Sud-Coréen Hyundai produite depuis 2008. C'est une citadine 5 portes, elle remplace la Hyundai Atos Prime en 2008 sur le marché européen, mais pas en Inde, où l'Atos Prime, appelée Santro, continue d'être produite et coûte moins cher. "));
        for (Car car : cars) {
            dbHelper.addCar(car);
        }
    }

    private void setupCarRecyclerView() {
        carRecyclerView = findViewById(R.id.carRecyclerView);
        carRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        CarAdapter carAdapter = new CarAdapter(cars, this);
        carRecyclerView.setAdapter(carAdapter);
    }

    public void goToProfil(View view) {
        Intent intent = new Intent(this, Profil.class);
        startActivity(intent);
    }


    public void goToDetailCar(View view) {
        int position = carRecyclerView.getChildLayoutPosition(view);
        if (position != RecyclerView.NO_POSITION) {
            Car selectedCar = cars.get(position);
            Intent intent = new Intent(this, DetailCar.class);
            intent.putExtra("selectedCar", selectedCar.getId());
            startActivity(intent);
        }
    }
}
