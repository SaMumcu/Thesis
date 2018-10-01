package com.sabihamumcu.tez.fcmnotificationdeneme;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by sabis on 3/10/2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService{

    private static final String REG_TOKEN="REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        Log.d(REG_TOKEN,recent_token);
    }
}
