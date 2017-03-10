package com.pandacrm.smstransmitter;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by pandaxu on 2017/3/10.
 */

public class NotificationIcon {
    private static boolean isShow=false;
    public static void showNotificationIcon(NotificationManager notificationManager, Context context){
        if(!isShow){
            Notification notification = new Notification.Builder(context)
                    .setContentTitle("短信助手")
                    .setContentText("短信助手正在运行中")
                    .setSmallIcon(R.drawable.ic_notify)
                    .getNotification();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setClass(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            notification.flags = Notification.FLAG_ONGOING_EVENT; // 设置常驻 Flag
            PendingIntent contextIntent = PendingIntent.getActivity(context, 0, intent, 0);
            notificationManager.notify(R.drawable.ic_notify, notification);
            isShow = true;
        }
    }
}
