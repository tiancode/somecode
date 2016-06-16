package com.topspeedsnail.myclip;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText copyText, pasteText;
    private Button copyButton, pasteButton;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyText = (EditText) findViewById(R.id.editTextCopy);
        pasteText = (EditText) findViewById(R.id.editTextPaste);

        copyButton = (Button) findViewById(R.id.buttonCopy);
        pasteButton = (Button) findViewById(R.id.buttonPaste);

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = copyText.getText().toString();

                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "已复制", Toast.LENGTH_SHORT).show();
            }
        });

        pasteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData myClip = myClipboard.getPrimaryClip();
                ClipData.Item item = myClip.getItemAt(0);

                String text = item.getText().toString();
                pasteText.setText(text);

                Toast.makeText(getApplicationContext(), "已粘贴", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
