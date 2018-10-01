package com.sabihamumcu.tez.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.messaging.FirebaseMessaging;
import com.sabihamumcu.tez.R;

public class NotificationActivity extends AppCompatActivity {
/*
* {
  "to":"/topics/JavaSampleApproach",
  "data":{
  	"extra_information":"This is not a data"
  },
  "notification":{
  	"title":"this is the title",
  	"text":"this is the text",
  	"click_action":"MAINACTIVITY"
  }

}
*
*
*
* */
    private Button btn_subscribe;
    private Button btn_unsubscribe;

    private final String TOPIC = "JavaSampleApproach";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btn_subscribe = (Button) findViewById(R.id.btn_subscribe);
        btn_unsubscribe = (Button) findViewById(R.id.btn_unsubscribe);

        btn_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

                /*Intent intent=new Intent(NotificationActivity.this,ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent=PendingIntent.getActivity(NotificationActivity.this,0,intent,PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(NotificationActivity.this)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("rr")
                        .setContentText("rrrrr")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());*/
            }
        });

        btn_unsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC);
            }
        });
    }
}
