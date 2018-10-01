package com.sabihamumcu.tez.fcmnotificationdeneme;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sabihamumcu.tez.Main2Activity;
import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.activity.NewProductsActivity;

/**
 * Created by sabis on 3/10/2018.
 */

public class MyFirebaseMessageService extends FirebaseMessagingService {

    public MyFirebaseMessageService() {

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        /*if(remoteMessage.getData()!=null){
            Toast.makeText(getApplicationContext(),remoteMessage.getData().toString(),Toast.LENGTH_SHORT).show();
        }*/
        /*final FirebaseMessaging messaging= FirebaseMessaging.getInstance();
        messaging.unsubscribeFromTopic("news");*/
        Intent intent = new Intent(this, NewProductsActivity.class);
        /*intent.putExtra("title", "Yeni Ürünler");
        intent.putExtra("message", remoteMessage.getNotification().getBody());*/
        //intent.putExtra("productTitle", remoteMessage.getData().get("title"));
        //remoteMessage.getData().get("body");
        //intent.putExtra("productDetail", remoteMessage.getData().get("dataBody"));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setContentTitle(remoteMessage.getNotification().getTitle());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(R.drawable.ic_notification);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
        System.out.println(remoteMessage.getNotification().getTitle() + " " + remoteMessage.getNotification().getBody());
    }

    //https://stackoverflow.com/questions/37358462/firebase-onmessagereceived-not-called-when-app-in-background
    @Override
    public void handleIntent(Intent intent) {
        super.handleIntent(intent);
    }
}
