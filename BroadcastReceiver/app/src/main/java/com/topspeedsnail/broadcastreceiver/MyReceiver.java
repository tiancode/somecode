package com.topspeedsnail.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "探测到 Intent", Toast.LENGTH_LONG).show();
    }
}
