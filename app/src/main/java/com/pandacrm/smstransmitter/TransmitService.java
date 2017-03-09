package com.pandacrm.smstransmitter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TransmitService extends Service {
    public void onCreate() {
        new TransmitReceiver();
        Log.i("TransmitReceiver", "onCreate");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
