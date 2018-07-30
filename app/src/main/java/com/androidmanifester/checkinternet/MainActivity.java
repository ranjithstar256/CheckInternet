package com.androidmanifester.checkinternet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.crash.FirebaseCrash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseCrash.logcat(Log.ERROR, "crussh", "NPE caught");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Contact via:");
                builder.setPositiveButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Sms", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 6);
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPrefs.getBoolean("firstStart", true);
                if (isFirstStart) {
                    final Intent i = new Intent(MainActivity.this, IntroActivity.class);
                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            startActivity(i);
                        }
                    });
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        adView.loadAd(adRequest);

        t.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 6) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
               // initializeView();
            } else { //Permission is not available
                //Toast.makeText(this,"Draw over other app permission not available. Closing the application",
                        //Toast.LENGTH_SHORT).show();
//                finish();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            startActivity(new Intent(MainActivity.this,MainActivity.class));
        }
    }

    public void srtsvc(View view) {
        Intent i=new Intent(MainActivity.this,MyService.class);
        startService(i);
    }

    public void stpsvc(View view) {
        Intent i=new Intent(MainActivity.this,MyService.class);
        stopService(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String s=item.getTitle().toString();
        if (s.equals("About Us")){
            Uri uri=Uri.parse("http://www.androidmanifester.com/");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
        if (s.equals("Contact Us")){
            android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Contact via:");
            builder.setPositiveButton("Email", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("Sms", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                }
            });
            builder.show();
        }
        if (s.equals("Contributor")){
            Uri uri=Uri.parse("https://www.instagram.com/isantoshandres/?hl=en");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
        if (s.equals("Credit")){
            Uri uri=Uri.parse("https://www.linkedin.com/in/androidranjith/");
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }if (s.equals("Info")){
            AlertDialog.Builder alertDialog;
            alertDialog= new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Credits");
            alertDialog.setMessage("" +
                    "App Idea by AndroidManifester Ranjith" +
                    "\n and This App is Developed by My Student \n E.Santosh. \n\n" +
                    "Thanks,  \n" +
                    "K.P. Ranjith Kumar B.E,\n" +
                    "+91-8148580586,\n" +
                    "Ranjithpsundar@gmail.com, \n" +
                    "Senior Android Trainer and Developer,\n" +
                    "Founder & CEO AndroidManifester,\n" +
                    "Software Entrepreneur,\n" +
                    "https://g.co/kgs/gPGHKz\n" +
                    "www.AndroidManifester.com,\n" +
                    "http://bit.ly/MyLifeWithAndroid\n" +
                    "FB.com/AndroidManifester \n");
            alertDialog.setIcon(android.R.drawable.ic_media_play); //using buildin icon
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
