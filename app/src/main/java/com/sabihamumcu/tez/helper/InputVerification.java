package com.sabihamumcu.tez.helper;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.sabihamumcu.tez.R;

/**
 * Created by sabis on 3/11/2018.
 */

public class InputVerification {

    public static boolean isValidEmail(String email) {
        if (email.contains("@")) {
            return true;
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (password.matches(pattern)) {
            return true;
        }
        return false;
    }


    public static String isValidInput(String email, String password) {
        String validateMail = "", validatePassword;
        if (InputVerification.isValidEmail(email)) {
            validateMail = "1";
        } else {
            validateMail = "0";
        }
        if (InputVerification.isValidPassword(password)) {
            validatePassword = "1";
        } else {
            validatePassword = "0";
        }
        return validateMail + validatePassword;

    }

    public static boolean verify(String verification, Activity activity) {
        if (verification.equals("11")) {
            Helper.displayMessageToast(activity, "Giriş başarılı.");
            return true;
        } else if (verification.equals("10")) {
            Helper.displayMessageToast(activity, activity.getResources().getString(R.string.wrongLogInInputError));
            return false;
        } else if (verification.equals("01")) {
            Helper.displayMessageToast(activity, "Hatalı bir mail girdiniz.");
            return false;
        } else if (verification.equals("00")) {
            Helper.displayMessageToast(activity, "Hatalı mail ve şifre girdiniz.");
            return false;
        }
        return false;
    }

}
