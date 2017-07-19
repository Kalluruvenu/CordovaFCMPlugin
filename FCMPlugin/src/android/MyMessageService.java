package cordova-plugin-pushAPI;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.System;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat.Builder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class PushIdBaseMessageService
  extends FirebaseMessagingService
{
  
  
    public void onMessageReceived(RemoteMessage remoteMessage)
  {
  Log.d(TAG, "From: " + remoteMessage.getFrom());
    //I am setting in here.
    setBadge(getApplicationContext(),Conts.notificationCounter  );
    Log.e("notificationNUmber",":"+ Conts.notificationCounter);

    // Check if message contains a data payload.
    if (remoteMessage.getData().size() > 0) {
        Log.d(TAG, "Message data payload: " + remoteMessage.getData());
    }


    // Check if message contains a notification payload.
    if (remoteMessage.getNotification() != null) {
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
    } 
     }
  