package com.topspeedsnail.recaudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.os.Environment;
import android.media.MediaRecorder;
import android.view.View;
import java.io.IOException;
import android.widget.Toast;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    private Button start,stop,play;
    // 路径文件路径
    private String outputFile = null;

    private MediaRecorder AudioRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.startRec);
        stop = (Button)findViewById(R.id.stopRec);
        play = (Button)findViewById(R.id.PlayRec);

        // 停止按钮和播放按钮不可用
        stop.setEnabled(false);
        play.setEnabled(false);

        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/rec.3gp";

        AudioRecorder=new MediaRecorder();
        AudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        AudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        AudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        AudioRecorder.setOutputFile(outputFile);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AudioRecorder.prepare();
                    AudioRecorder.start();
                }

                catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                start.setEnabled(false);
                stop.setEnabled(true);

                Toast.makeText(getApplicationContext(), "录音开始", Toast.LENGTH_LONG).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioRecorder.stop();
                AudioRecorder.release();
                AudioRecorder  = null;

                stop.setEnabled(false);
                play.setEnabled(true);

                Toast.makeText(getApplicationContext(), "录音结束",Toast.LENGTH_LONG).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) throws IllegalArgumentException,SecurityException,IllegalStateException {
                MediaPlayer m = new MediaPlayer();

                try {
                    m.setDataSource(outputFile);
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    m.prepare();
                }

                catch (IOException e) {
                    e.printStackTrace();
                }

                m.start();
                Toast.makeText(getApplicationContext(), "播放录音", Toast.LENGTH_LONG).show();
            }
        });
    }

}
