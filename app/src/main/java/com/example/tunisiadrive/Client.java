package com.example.tunisiadrive;

public class Client {
    private int id;
    private String nom;
    private String photoClient;
    private String email;
    private String adresse;
    private String numeroTelephone;

    // Constructor
    public Client(int id, String nom, String photoClient, String email, String adresse, String numeroTelephone) {
        this.id = id;
        this.nom = nom;
        this.photoClient = photoClient;
        this.email = email;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhotoClient() {
        return photoClient;
    }

    public void setPhotoClient(String photoClient) {
        this.photoClient = photoClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    // Optional: Override toString for easy debugging
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", photoClient='" + photoClient + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                '}';
    }
}
