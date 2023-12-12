package com.example.tunisiadrive;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Util {

    static public void showToast(Context context, String message) {
        Toast toast =  Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 40);
        toast.show();
    }
}
