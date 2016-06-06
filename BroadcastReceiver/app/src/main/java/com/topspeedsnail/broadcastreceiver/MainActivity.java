package com.topspeedsnail.broadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 发送自定义广播
    public void broadcastIntent(View view){
        Intent intent = new Intent();
        intent.setAction("com.topspeedsnail.MY_INTENT");
        sendBroadcast(intent);
    }
}
