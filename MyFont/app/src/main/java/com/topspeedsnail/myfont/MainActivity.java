package com.topspeedsnail.myfont;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.textViewFont1);
        text2 = (TextView)findViewById(R.id.textViewFont2);

        Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/HomemadeApple.ttf");
        text1.setTypeface(font1);

        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Sunshiney.ttf");
        text2.setTypeface(font2);
    }

}
