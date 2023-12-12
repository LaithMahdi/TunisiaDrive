package com.example.tunisiadrive;

public class Car {
    private int id;
    private String nom;
    private String modele;
    private String description;
    private int age;
    private int disponibilite;
    private String typeCarburant;
    private int imagePath;
    private  double price;
    private  int nbReview;

    public Car(int id, String nom, String modele, int age, int disponibilite, String typeCarburant, int imagePath, double price, int nbReview, String description) {
        this.id = id;
        this.nom = nom;
        this.modele = modele;
        this.age = age;
        this.disponibilite = disponibilite;
        this.typeCarburant = typeCarburant;
        this.imagePath = imagePath;
        this.price = price;
        this.nbReview = nbReview;
        this.description = description;
    }

    public Car( String nom, String modele, int age, int disponibilite, String typeCarburant, int imagePath,double price,int nbReview,String description) {
        this.nom = nom;
        this.modele = modele;
        this.age = age;
        this.disponibilite = disponibilite;
        this.typeCarburant = typeCarburant;
        this.imagePath = imagePath;
        this.price = price;
        this.nbReview = nbReview;
        this.description= description;
    }


    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getModele() {
        return modele;
    }

    public int getAge() {
        return age;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public int getImagePath() {
        return imagePath;
    }

    public double getPrice() {return price;}
    public  int getNbReview(){return this.nbReview;}
    public  String getDescription() {return description;}
}
