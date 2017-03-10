package com.pandacrm.smstransmitter;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransmitReceiver extends BroadcastReceiver {

    private static final String TAG = "TransmitReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = "";
        SmsMessage msg = null;

        String transmitNunmber = MainActivity.getSettingNote(context,"number");

        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());//时间
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                String number = msg.getOriginatingAddress();

                if("".equals(message)){
                    message = "短信助手自动转发\n来自："+number+"\n时间："+receiveTime+"\n内容：";
                }

                message =message+ msg.getDisplayMessageBody();
            }

            if (!transmitNunmber.equals("")){//第一次安装软件时，在没有设置转发号码的时候不转发
                transmitMessageTo(context,transmitNunmber, message);

                Log.i(TAG,message);
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void transmitMessageTo(Context context,String phoneNumber,String message){//转发短信
        SmsManager manager = SmsManager.getDefault();
        /** 切分短信，每七十个汉字切一个，短信长度限制不足七十就只有一个：返回的是字符串的List集合*/
        if(message.length()>70){
            ArrayList<String> texts =manager.divideMessage(message);//这个必须有
            manager.sendMultipartTextMessage(phoneNumber,null,texts,null,null);
        }else{
            PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            manager.sendTextMessage(phoneNumber, null, message, sentIntent, null);
        }
    }
}
