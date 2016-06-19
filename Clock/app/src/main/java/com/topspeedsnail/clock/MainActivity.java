package com.topspeedsnail.clock;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextClock;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static private Button swap;

    static private TextClock textClock;
    static private AnalogClock analogClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swap = (Button) findViewById(R.id.button);

        textClock = (TextClock) findViewById(R.id.textClock);
        analogClock = (AnalogClock) findViewById(R.id.analogClock);

        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (textClock.getVisibility() == TextClock.VISIBLE ) {
                    textClock.setVisibility(TextClock.INVISIBLE);
                    analogClock.setVisibility(AnalogClock.VISIBLE);
                }
                else {
                    textClock.setVisibility(TextClock.VISIBLE);
                    analogClock.setVisibility(AnalogClock.INVISIBLE);
                }
            }
        });

    }
}
