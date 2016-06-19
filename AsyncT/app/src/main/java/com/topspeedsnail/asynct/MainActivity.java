package com.topspeedsnail.asynct;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button _button;
    private EditText _time;
    private TextView _res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _time = (EditText) findViewById(R.id.sleeptime);
        _button = (Button) findViewById(R.id.run);
        _res = (TextView) findViewById(R.id.result);

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sleepTime = _time.getText().toString();
                DumbTask dumbTask = new DumbTask();
                dumbTask.execute(sleepTime);

            }
        });
    }

    private class DumbTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        private String resp;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("sleep"); // 调用 onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "sleep " + params[0] + " S";

            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }

            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            _res.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "等待 " + _time.getText().toString() + " S");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            _res.setText(text[0]);

        }
    }
}
