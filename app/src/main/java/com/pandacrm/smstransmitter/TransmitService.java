package com.pandacrm.smstransmitter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TransmitService extends Service {

    public void onCreate() {
        new TransmitReceiver();
        Log.i("TransmitReceiver", "onCreate");
        NotificationIcon.showNotificationIcon((NotificationManager)getSystemService(NOTIFICATION_SERVICE),getApplicationContext());
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
