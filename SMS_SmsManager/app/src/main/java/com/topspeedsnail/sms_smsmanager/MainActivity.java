package com.topspeedsnail.sms_smsmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.util.Log;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button sendBtn;
    EditText phoneNo;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button) findViewById(R.id.btnSendSMS);
        phoneNo = (EditText) findViewById(R.id.phone_id);
        message = (EditText) findViewById(R.id.sms_text);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }

    protected void sendSMSMessage() {
        Log.i("开始发送短信", "");
        String phone_no = phoneNo.getText().toString();
        String message_body = message.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone_no, null, message_body, null, null);
            Toast.makeText(getApplicationContext(), "已发送.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "发送短信失败.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
