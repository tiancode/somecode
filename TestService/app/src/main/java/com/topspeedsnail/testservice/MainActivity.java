package com.topspeedsnail.testservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 启动服务
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // 停止服务
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }

}
