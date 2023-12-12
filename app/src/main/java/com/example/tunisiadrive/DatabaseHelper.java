package com.example.tunisiadrive;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

// DatabaseHelper.java
public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "CarRent";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_VOITURE = "Voiture";
    public static final String COLUMN_VOITURE_ID = "id";
    public static final String COLUMN_MARQUE = "marque";
    public static final String COLUMN_MODELE = "modele";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_DISPONIBILITE = "disponibilite";
    public static final String COLUMN_TYPE_CARBURANT = "type_carburant";
    public static final String COLUMN_PHOTO_VOITURE = "photo_voiture";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_NB_REVIEW = "nb_review";
    public static final String COLUMN_DESCRIPTION = "description";

        public static final String TABLE_CLIENT = "Client";
        public static final String COLUMN_CLIENT_ID = "id";
        public static final String COLUMN_NOM = "nom";
        public static final String COLUMN_PHOTO_CLIENT = "photo_client";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_ADRESSE = "adresse";
        public static final String COLUMN_NUMERO_TELEPHONE = "numero_telephone";




        public static final String TABLE_RESERVATION = "Reservation";
        public static final String COLUMN_RESERVATION_ID = "id";
        public static final String COLUMN_CLIENT_ID_FK = "client_id";
        public static final String COLUMN_VOITURE_ID_FK = "voiture_id";
        public static final String COLUMN_RESERVATION_DATE = "reservation_date";
        public static final String COLUMN_MONTANT_TOTAL = "montant_total";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String createVoitureTableQuery = "CREATE TABLE " + TABLE_VOITURE + " (" +
                    COLUMN_VOITURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MARQUE + " TEXT, " +
                    COLUMN_MODELE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_AGE + " INTEGER, " +
                    COLUMN_DISPONIBILITE + " INTEGER, " +
                    COLUMN_TYPE_CARBURANT + " TEXT, " +
                    COLUMN_PHOTO_VOITURE + " TEXT, " +
                    COLUMN_PRICE + " REAL, " +
                    COLUMN_NB_REVIEW + " INTEGER)";
            db.execSQL(createVoitureTableQuery);

            String createClientTableQuery = "CREATE TABLE " + TABLE_CLIENT + " (" +
                    COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOM + " TEXT, " +
                    COLUMN_PHOTO_CLIENT + " TEXT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_ADRESSE + " TEXT, " +
                    COLUMN_NUMERO_TELEPHONE + " TEXT)";
            db.execSQL(createClientTableQuery);

            String createReservationTableQuery = "CREATE TABLE " + TABLE_RESERVATION + " (" +
                    COLUMN_RESERVATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CLIENT_ID_FK + " INTEGER, " +
                    COLUMN_VOITURE_ID_FK + " INTEGER, " +
                    COLUMN_RESERVATION_DATE + " TEXT, " +
                    COLUMN_MONTANT_TOTAL + " REAL, " +
                    "FOREIGN KEY(" + COLUMN_CLIENT_ID_FK + ") REFERENCES " + TABLE_CLIENT + "(" + COLUMN_CLIENT_ID + "), " +
                    "FOREIGN KEY(" + COLUMN_VOITURE_ID_FK + ") REFERENCES " + TABLE_VOITURE + "(" + COLUMN_VOITURE_ID + "))";
            db.execSQL(createReservationTableQuery);


        }
    @Override
    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOITURE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION);
            onCreate(db);
        }


    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MARQUE, car.getNom());
        values.put(COLUMN_MODELE, car.getModele());
        values.put(COLUMN_AGE, car.getAge());
        values.put(COLUMN_DISPONIBILITE, car.getDisponibilite());
        values.put(COLUMN_TYPE_CARBURANT, car.getTypeCarburant());
        values.put(COLUMN_PHOTO_VOITURE, car.getImagePath());
        values.put(COLUMN_PRICE, car.getPrice());
        values.put(COLUMN_NB_REVIEW, car.getNbReview());
        values.put(COLUMN_DESCRIPTION, car.getDescription());
        db.insert(TABLE_VOITURE, null, values);
        db.close();
    }

    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VOITURE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Car car = new Car(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_VOITURE_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_MARQUE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_MODELE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_DISPONIBILITE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_CARBURANT)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_PHOTO_VOITURE)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_NB_REVIEW)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                );
                carList.add(car);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return carList;
    }


    @SuppressLint("Range")
    public Car getCarById(int carId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Car car = null;
        String[] columns = {
                COLUMN_VOITURE_ID,
                COLUMN_MARQUE,
                COLUMN_MODELE,
                COLUMN_AGE,
                COLUMN_DISPONIBILITE,
                COLUMN_TYPE_CARBURANT,
                COLUMN_PHOTO_VOITURE,
                COLUMN_PRICE,
                COLUMN_NB_REVIEW,
                COLUMN_DESCRIPTION
        };
        String selection = COLUMN_VOITURE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(carId)};
        Cursor cursor = db.query(
                TABLE_VOITURE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            car = new Car(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_VOITURE_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MARQUE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_MODELE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_AGE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_DISPONIBILITE)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_TYPE_CARBURANT)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_PHOTO_VOITURE)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NB_REVIEW)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
            );
            cursor.close();
        }
        db.close();
        return car;
    }


    public boolean createReservation(int clientId, int voitureId, String reservationDate, double montantTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLIENT_ID_FK, clientId);
        values.put(COLUMN_VOITURE_ID_FK, voitureId);
        values.put(COLUMN_RESERVATION_DATE, reservationDate);
        values.put(COLUMN_MONTANT_TOTAL, montantTotal);

        long result = db.insert(TABLE_RESERVATION, null, values);
        db.close();
        return result != -1;
    }


    public Integer getClientIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Integer clientId = null;
        String[] columns = { COLUMN_CLIENT_ID };
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = { email };
        Cursor cursor = db.query(
                TABLE_CLIENT,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            clientId = cursor.getInt(cursor.getColumnIndex(COLUMN_CLIENT_ID));
            cursor.close();
        }
        db.close();
        return clientId;
    }

    public List<Reservation> getReservationsByClientId(int clientId) {
        List<Reservation> reservationList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_RESERVATION +
                " WHERE " + COLUMN_CLIENT_ID_FK + " = ?";
        String[] selectionArgs = {String.valueOf(clientId)};

        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Reservation reservation = new Reservation(
                        cursor.getInt(cursor.getColumnIndex(COLUMN_RESERVATION_ID)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_CLIENT_ID_FK)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_VOITURE_ID_FK)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_RESERVATION_DATE)),
                        cursor.getDouble(cursor.getColumnIndex(COLUMN_MONTANT_TOTAL))
                );
                reservationList.add(reservation);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return reservationList;
    }
    public String getClientNameById(int clientId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String clientName = null;
        String[] columns = { COLUMN_NOM };
        String selection = COLUMN_CLIENT_ID + " = ?";
        String[] selectionArgs = { String.valueOf(clientId) };

        Cursor cursor = db.query(
                TABLE_CLIENT,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            clientName = cursor.getString(cursor.getColumnIndex(COLUMN_NOM));
            cursor.close();
        }
        db.close();

        return clientName;
    }

}
