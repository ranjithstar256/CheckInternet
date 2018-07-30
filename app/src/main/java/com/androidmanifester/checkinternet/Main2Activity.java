package com.androidmanifester.checkinternet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    EditText s,e;
    Button send;
    String subjectstr,emailstr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        s=findViewById(R.id.editText);
        e=findViewById(R.id.editText2);
        send=findViewById(R.id.button);

    }

    public void sendemail(View view) {
        subjectstr=s.getText().toString();
        emailstr=e.getText().toString();
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ranjithpsundar@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,subjectstr);
        intent.putExtra(Intent.EXTRA_TEXT,emailstr);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client:"));
    }
}
