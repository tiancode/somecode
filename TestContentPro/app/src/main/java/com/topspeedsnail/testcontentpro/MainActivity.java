package com.topspeedsnail.testcontentpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.ContentValues;
import android.widget.EditText;
import android.net.Uri;
import android.widget.Toast;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddClient(View view) {
        // 添加一条记录
        ContentValues values = new ContentValues();

        values.put(AccountProvider.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());

        values.put(AccountProvider.AGE,
                ((EditText)findViewById(R.id.age)).getText().toString());

        Uri uri = getContentResolver().insert(
                AccountProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickGetClients(View view) {

        // Retrieve student records
        String URL = "content://com.topspeedsnail.provider.Account/clients";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(AccountProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( AccountProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( AccountProvider.AGE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

}


