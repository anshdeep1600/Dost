package com.dost.anshdeep.dost;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    private static Button b, b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        int Read_SMS_Permsission = 0;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed by AnshDeep1600", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_SMS},Read_SMS_Permsission);
        }
        /*if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new  String[]{android.Manifest.permission.RECEIVE_SMS},Read_SMS_Permsission);
        }*/
    }
    public void onRingClick(View view){
        //b = (Button)findViewById(R.id.ring);
        b = (Button)findViewById(R.id.ring);
        Intent i = new Intent(this, Ringing.class);
        startActivity(i);
    }

    public void onAlarmClick(View view){
        b2 = (Button)findViewById(R.id.alrm);
        Intent i = new Intent(this, Alarms.class);
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
