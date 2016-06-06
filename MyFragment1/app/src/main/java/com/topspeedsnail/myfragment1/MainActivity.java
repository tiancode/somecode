package com.topspeedsnail.myfragment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration config = getResources().getConfiguration();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 检测系统是横屏竖屏
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment fragment_h = new Fragment_H();
            fragmentTransaction.replace(android.R.id.content, fragment_h);
        }
        else {
            Fragment_S fragment_s = new Fragment_S();
            fragmentTransaction.replace(android.R.id.content, fragment_s);
        }


        fragmentTransaction.commit();
    }
}
