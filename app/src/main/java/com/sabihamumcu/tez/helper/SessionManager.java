package com.sabihamumcu.tez.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;

/**
 * Created by sabis on 3/11/2018.
 */

public class SessionManager {

    public static void setDefaults(String key, String email, Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Const.KEY_FIREBASE, key);
        editor.putString(Const.KEY_EMAIL, email);
        editor.putBoolean(Const.IS_LOGIN, true);
        editor.putBoolean(Const.IS_CHECKED,false);
        editor.commit();
    }

    public static String getDefaults(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(Const.KEY_EMAIL, null);
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(Const.IS_LOGIN, false);
    }

    public static boolean isChecked(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(Const.IS_CHECKED, false);
    }
    public static void setCheck(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Const.IS_CHECKED,true);
        editor.commit();
    }
    public static void unCheck(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Const.IS_CHECKED,false);
        editor.commit();
    }

    public static void logoutUser(Context context) {
        // Clearing all data from Shared Preferences
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public static HashMap<String, String> getUserDetails(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Const.KEY_FIREBASE, pref.getString(Const.KEY_FIREBASE, null));
        user.put(Const.KEY_EMAIL, pref.getString(Const.KEY_EMAIL, null));
        return user;
    }

}
