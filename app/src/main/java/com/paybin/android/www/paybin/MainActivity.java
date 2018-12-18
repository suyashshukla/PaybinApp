package com.paybin.android.www.paybin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    static String requestOn;
    static String requestOff;
    WebView webView;
    Button submit;
    Button off;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.location,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        startActivity(new Intent(MainActivity.this,MapActivity.class));

        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestOn = "https://cloud.boltiot.com/remote/6cf67013-55ee-421d-adbd-882f2247507a/digitalWrite?pin=0&state=HIGH&deviceName=BOLT3730303";

        requestOff = "https://cloud.boltiot.com/remote/6cf67013-55ee-421d-adbd-882f2247507a/digitalWrite?pin=0&state=LOW&deviceName=BOLT3730303";


        webView = findViewById(R.id.bolt);
        submit = findViewById(R.id.submit);


        webView.loadUrl(requestOff);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Success")
                        .setIcon(R.drawable.correct)
                        .setMessage("OTP Authenticated\nReward Points Credited : ".concat(String.valueOf((int)(Math.random()*100))))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                webView.loadUrl(requestOff);
                            }
                        })
                        .create().show();

                webView.loadUrl(requestOn);

            }
        });





        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);




       // LEDTask ledTask = new LEDTask();
       // ledTask.execute();
    }

}


class LEDTask extends AsyncTask<Void, Void, Void> {



    @Override
    protected Void doInBackground(Void... voids) {


        return null;
    }
}
