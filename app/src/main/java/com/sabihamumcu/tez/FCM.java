package com.sabihamumcu.tez;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by sabis on 1/25/2018.
 */

public class FCM extends FirebaseMessagingService {


    public FCM(){

    }

    String title;
    String message;
    String clickAction;
    private final String TAG = "JSA-FCM";
    private NotificationManager notificationManager;
    private static final String NOTIFICATION_ID_EXTRA = "notificationId";
    private static final String IMAGE_URL_EXTRA = "imageUrl";
    private static final String ADMIN_CHANNEL_ID ="admin_channel";
    String jsonTitle;
    String jsonMessage;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            /*Log.e(TAG, "Title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "Body: " + remoteMessage.getNotification().getBody());
            Log.d(TAG,"NotificationClick Action: "+remoteMessage.getNotification().getClickAction());
            String title=remoteMessage.getNotification().getTitle();
            String message=remoteMessage.getNotification().getBody();
            clickAction=remoteMessage.getNotification().getClickAction();

            //sendNotification(title,message);
            Map<String,String> map=remoteMessage.getData();
            String result="";
            for(String key:map.keySet())
                result+=map.get(key);
            Intent intent=new Intent(this,Main2Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("result",result);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle(title)
                    .setContentText("Burda mesaj")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());*/
        }
        if (remoteMessage.getData().size() > 0) {
            /*Log.e(TAG, "Data: " + remoteMessage.getData());
            JSONObject data=new JSONObject(remoteMessage.getData());
            try {
                jsonTitle=data.getString("title");
                jsonMessage=data.getString("body");
                //sendNotification(jsonTitle,jsonMessage);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent intent=new Intent(this,Main2Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("result",jsonTitle);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle(title)
                    .setContentText(jsonMessage)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());*/
        }

        /*if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data: " + remoteMessage.getData());

            JSONObject data=new JSONObject(remoteMessage.getData());
            try {
                jsonTitle=data.getString("title");
                jsonMessage=data.getString("body");
                //sendNotification(jsonTitle,jsonMessage);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            title= URLDecoder.decode(remoteMessage.getNotification().getTitle(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        message=remoteMessage.getNotification().getBody();

        sendNotification(title,message);*/
    }

    private void sendNotification(String title,String messageBody){
        int requestID = (int) System.currentTimeMillis();
        /*Intent intent=new Intent(this,ProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
*/

        Intent intent=new Intent(this,MainActivity.class);
        /*TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent to the top of the stack
        stackBuilder.addNextIntent(intent);*/
        //intent.setAction(Intent.ACTION_MAIN);
        intent.putExtra("title",jsonTitle);
            intent.putExtra("body",jsonMessage);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),requestID,intent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

}
