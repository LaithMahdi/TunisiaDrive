package com.example.tunisiadrive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private List<Reservation> reservations;

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservation reservation = reservations.get(position);
        DatabaseHelper dbHelper = new DatabaseHelper(holder.itemView.getContext());

       int  carID = reservation.getVoitureId();
        Car car= dbHelper.getCarById(carID);
        holder.carImageView.setImageResource(car.getImagePath());
        holder.carNameTextView.setText(car.getNom());
        holder.reservationDateTextView.setText("Date: " + reservation.getReservationDate());
        holder.totalAmountTextView.setText("Total Amount: $" + reservation.getMontantTotal());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView carImageView;
        TextView carNameTextView;
        TextView reservationDateTextView;
        TextView totalAmountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carImageView = itemView.findViewById(R.id.carImageView);
            carNameTextView = itemView.findViewById(R.id.carNameTextView);
            reservationDateTextView = itemView.findViewById(R.id.reservationDateTextView);
            totalAmountTextView = itemView.findViewById(R.id.totalAmountTextView);
        }
    }
}