package com.example.tunisiadrive;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "sharedPref";
    private static final String KEY_IS_ONBOARD = "isOnboard";
    private static final String KEY_IS_AUTHENTICATED = "isAuthenticated";
    private static final String KEY_IS_ADD_CARS = "isAddCars";
    private static final String KEY_IS_USER_EMAIL = "isUserEamil";
    private static final String KEY_IS_USER_UID = "isUserUid";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean isOnboard() {
        return sharedPreferences.getBoolean(KEY_IS_ONBOARD, false);
    }

    public void setIsOnboard(boolean isOnboard) {
        editor.putBoolean(KEY_IS_ONBOARD, isOnboard).apply();
    }

    public boolean isAuthenticated() {
        return sharedPreferences.getBoolean(KEY_IS_AUTHENTICATED, false);
    }

    public void setIsAuthenticated(boolean isAuthenticated) {
        editor.putBoolean(KEY_IS_AUTHENTICATED, isAuthenticated).apply();
    }

    public boolean isAddCars() {
        return sharedPreferences.getBoolean(KEY_IS_ADD_CARS, false);
    }

    public void setIsAddCars(boolean isAddCars) {
        editor.putBoolean(KEY_IS_ADD_CARS, isAddCars).apply();
    }


    public String getUserEmail() {
        return sharedPreferences.getString(KEY_IS_USER_EMAIL, "");
    }

    public String getUserUid() {
        return sharedPreferences.getString(KEY_IS_USER_UID, "");
    }
    public void setIsUser(String userEmail,String uid) {
        editor.putString(KEY_IS_USER_UID, uid).apply();
        editor.putString(KEY_IS_USER_EMAIL, userEmail).apply();
    }
}