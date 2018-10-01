package com.sabihamumcu.tez.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sabis on 3/11/2018.
 */

public class Helper {
    public static void displayMessageToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }
}
