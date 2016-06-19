package com.topspeedsnail.loginregister;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import android.util.Log;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = (EditText) findViewById(R.id.NewNameEditText);
        editTextEmail = (EditText) findViewById(R.id.NewEmailEditText);
        editTextPassword = (EditText) findViewById(R.id.NewPasswordEditText);
        registerButton = (Button) findViewById(R.id.NewRegisterButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 格式判断略

                RegisterTask login = new RegisterTask();
                login.execute(editTextName.getText().toString(), editTextEmail.getText().toString(), editTextPassword.getText().toString());

                finish();
            }
        });
    }

    private class RegisterTask extends AsyncTask<String, Void, String> {
        private String registerUrl = "http://192.168.0.108/Register.php";

        @Override
        protected String doInBackground(String... params) {
            String regName = params[0];
            String regEmail = params[1];
            String regPassword = params[2];

            try {
                URL url = new URL(registerUrl);
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("POST");
                connect.setDoOutput(true);

                OutputStream outputStream = connect.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(regName, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(regEmail, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(regPassword, "UTF-8");

                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = connect.getInputStream();
                inputStream.close();

                Log.d("注册成功", regName);

                return "注册成功 " + regName;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }



    }

}
