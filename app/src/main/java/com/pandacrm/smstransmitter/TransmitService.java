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
        super.onCreate();

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("短信助手")
                .setContentText("短信助手正在运行中")
                .setSmallIcon(R.drawable.ic_notify)
                .getNotification();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        notification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻 Flag
        PendingIntent contextIntent = PendingIntent.getActivity(this, 0, intent, 0);
        notificationManager.notify(R.drawable.ic_notify, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
