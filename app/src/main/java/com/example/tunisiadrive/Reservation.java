package com.example.tunisiadrive;

public class Reservation {
    private int id;
    private int clientId;
    private int voitureId;
    private String reservationDate;
    private double montantTotal;

    // Constructor
    public Reservation(int id, int clientId, int voitureId, String reservationDate, double montantTotal) {
        this.id = id;
        this.clientId = clientId;
        this.voitureId = voitureId;
        this.reservationDate = reservationDate;
        this.montantTotal = montantTotal;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getVoitureId() {
        return voitureId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setVoitureId(int voitureId) {
        this.voitureId = voitureId;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
