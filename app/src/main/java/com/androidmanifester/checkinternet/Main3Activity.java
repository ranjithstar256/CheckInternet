package com.androidmanifester.checkinternet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    EditText message;
    Button send;
    String messagestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        message=findViewById(R.id.editText);
        send=findViewById(R.id.button);

    }

    public void sendsms(View view) {
        messagestr=message.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage("+918148580586",null,messagestr,null,null);

        Toast.makeText(this, "Message sent!", Toast.LENGTH_SHORT).show();
    }
}
