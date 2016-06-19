package com.topspeedsnail.loginregister;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button loginButton, registerButton;

    // 判断是否是正确的email格式
    private static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.EmailEditText);
        editTextPassword = (EditText) findViewById(R.id.PasswordEditText);
        loginButton = (Button) findViewById(R.id.LoginButton);
        registerButton = (Button) findViewById(R.id.RegisterButton);

        // 登录按钮
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 判断EditText控件是否为空
                if (editTextEmail.getText().toString().trim().length() == 0 || editTextPassword.getText().toString().trim().length() == 0) {
                    Toast.makeText(MainActivity.this, "填写邮箱和密码", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!isValidEmail(editTextEmail.getText())) {
                    Toast.makeText(MainActivity.this, "邮箱格式不对", Toast.LENGTH_LONG).show();
                    return;
                }

                String loginEmail = editTextEmail.getText().toString();
                String loginPassword = editTextPassword.getText().toString();
                LoginTask login = new LoginTask();
                login.execute(loginEmail, loginPassword);

            }
        });

        // 注册按钮
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private class LoginTask extends AsyncTask<String, Void, String> {

        private String loginUrl = "http://192.168.0.108/Login.php";

        @Override
        protected String doInBackground(String... params) {
            String loginEmail = params[0];
            String loginPassword = params[1];

            try {
                URL url = new URL(loginUrl);
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("POST");
                connect.setDoOutput(true);
                connect.setDoInput(true);

                // 给服务器发送认证信息
                OutputStream outputStream = connect.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(loginEmail, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(loginPassword, "UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // 获得的回应消息
                InputStream inputStream = connect.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String dataResponse = "";
                String inputLine = "";
                while ((inputLine = bufferedReader.readLine()) != null) {
                    dataResponse += inputLine;
                }
                bufferedReader.close();
                inputStream.close();
                connect.disconnect();

                // Log.d("myStupidApp", dataResponse);

                return dataResponse;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String res) {
            String[] dataResponse = res.split("[,]");

            String Login = dataResponse[0];
            String name = dataResponse[1];
            String email = dataResponse[2];
            if (Login.equals("true")) {
                // 登录成功，启动另一个Activity
                // Intent intent = new Intent(MainActivity.this, LoginActivity);
                // startActivity(intent);

                Log.d("登录成功", name + " " + email);
            } else {
                Log.d("登录失败", name + " " + email);
            }
        }

    }


}
