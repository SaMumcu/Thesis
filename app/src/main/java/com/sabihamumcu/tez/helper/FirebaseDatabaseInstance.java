package com.sabihamumcu.tez.helper;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sabis on 3/11/2018.
 */

public class FirebaseDatabaseInstance {

    private static FirebaseDatabase firebaseDatabase;

    public static FirebaseDatabase getInstance() {
            firebaseDatabase = FirebaseDatabase.getInstance("https://tuserdata.firebaseio.com/");
            return firebaseDatabase;
    }
}
