package com.pandacrm.smstransmitter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startServiceIntent = new Intent(context,TransmitService.class);
        context.startService(startServiceIntent);
        Log.i("BootReceiver","启动TransmitService");
    }
}
