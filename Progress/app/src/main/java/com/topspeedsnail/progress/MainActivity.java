package com.topspeedsnail.progress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.widget.Button;
import android.view.View;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private Button bar, circle;

    private ProgressDialog progressBar;
    private ProgressDialog progressCircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (Button)findViewById(R.id.bar);
        circle = (Button)findViewById(R.id.circle);


        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar = new ProgressDialog(MainActivity.this);
                progressBar.setMessage("下载文件");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setIndeterminate(false);
                progressBar.setProgress(0);
                progressBar.show();
                progressBar.setMax(100);

                // 不可取消
                progressBar.setCancelable(false);
                progressBar.setCanceledOnTouchOutside(false);

                final Thread task = new Thread() {
                    @Override
                    public void run() {
                        int jump = 0;

                        while(jump < progressBar.getMax()) {
                            try {
                                sleep(100);
                                jump += 1;
                                progressBar.setProgress(jump);
                            }

                            catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        // 执行完成之后，消除窗口
                        progressBar.dismiss();
                    }
                };
                task.start();
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressCircle = new ProgressDialog(v.getContext());
                progressCircle.setMessage("等待中...");
                progressCircle.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressCircle.setProgress(0);
                progressCircle.show();

                final Thread task = new Thread() {
                    @Override
                    public void run() {
                        int jump = 0;

                        while(jump < 100) {
                            try {
                                sleep(100);
                                jump += 1;
                            }

                            catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        // 执行完成之后，消除窗口
                        progressCircle.dismiss();
                    }
                };
                task.start();
            }
        });
    }
}
