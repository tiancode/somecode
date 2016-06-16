package com.topspeedsnail.audioman;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AudioManager myAudioManager;

    private Button mode, ring, vibrate, silent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ring = (Button) findViewById(R.id.ringButton);
        vibrate = (Button) findViewById(R.id.virButton);
        silent = (Button) findViewById(R.id.slientButton);
        mode = (Button) findViewById(R.id.getCurrentButton);

        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(MainActivity.this, "进入普通模式", Toast.LENGTH_LONG).show();
            }
        });

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(MainActivity.this, "进入震动模式", Toast.LENGTH_LONG).show();
            }
        });

        silent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                Toast.makeText(MainActivity.this, "进入静音模式", Toast.LENGTH_LONG).show();
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mod = myAudioManager.getRingerMode();

                if (mod == AudioManager.RINGER_MODE_VIBRATE) {
                    Toast.makeText(MainActivity.this, "震动模式", Toast.LENGTH_LONG).show();
                } else if (mod == AudioManager.RINGER_MODE_NORMAL) {
                    Toast.makeText(MainActivity.this, "普通模式", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "静音模式", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
