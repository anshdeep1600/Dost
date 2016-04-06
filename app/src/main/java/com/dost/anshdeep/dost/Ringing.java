package com.dost.anshdeep.dost;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Ringing extends AppCompatActivity {
    Button r;
    TextView ringDesc;
    String keyMessage;
    private static RadioGroup radioGroup;
    private static RadioButton radioButton;
    private boolean smsChoice, smsChoice1;
    private String prefFile= "SmsPreference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed by AnshDeep1600", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        int MY_Permission_Request_SEND_SMS = 1;
        final Switch sw = (Switch)findViewById(R.id.sms);
        SharedPreferences smsPrefs = getSharedPreferences(prefFile,MODE_PRIVATE);
        smsChoice = smsPrefs.getBoolean("sms_status",false);
        if (smsChoice){
            sw.setChecked(true);
        }
        else {
            sw.setChecked(false);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_Permission_Request_SEND_SMS);
        }
        radioGroup = (RadioGroup) findViewById(R.id.mode);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(checkedId);
                Fragment fragment;
                String rad = radioButton.getText().toString();
                switch (rad) {
                    case "Normal":
                        keyMessage = "Send a text message with  ‘Ring’ as keyword to change profile to Ringing Mode";
                        //fragment = new Normal();
                        //Toast.makeText(Ringing.this,"Inside Normal",Toast.LENGTH_SHORT).show();
                        break;
                    case "Silent":
                        keyMessage = "Send a text message with  ‘Silent’ as keyword to change profile to Silent Mode";
                        //fragment = new Silent();
                        //Toast.makeText(Ringing.this,"Inside Silent",Toast.LENGTH_SHORT).show();
                        break;
                    case "Vibrate":
                        keyMessage = "Send a text message with  ‘Vibrate’ as keyword to change profile to Vibrate Mode";
                        //fragment = new Vibrate();
                        //Toast.makeText(Ringing.this,"Inside Vibrate",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        keyMessage = "Send a text message with  ‘Ring’ as keyword to change profile to Ringing Mode";
                        //fragment = new Normal();
                        //Toast.makeText(Ringing.this,"Inside Default",Toast.LENGTH_SHORT);
                }
                /*FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.profileDescription, fragment);
                ft.commit();*/
                ringDesc = (TextView)findViewById(R.id.tvRinging);
                ringDesc.setText(keyMessage);
            }
        });

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(Ringing.this, "Message will be Sent on Profile Change", Toast.LENGTH_SHORT).show();
                    //smsChoice = true;
                } else {
                    Toast.makeText(Ringing.this, "No Message will be sent on Profile Change", Toast.LENGTH_SHORT).show();
                    //smsChoice = false;
                }
                SharedPreferences.Editor editor = getSharedPreferences(prefFile, MODE_PRIVATE).edit();
                editor.putBoolean("sms_status", sw.isChecked());
                editor.commit();
            }
        });
    }
    public  void goBackFromRinging(View view){
        r = (Button)findViewById(R.id.back2);
        finish();
    }
    public boolean smsChoice(Context m){
        SharedPreferences smsPrefs = m.getSharedPreferences(prefFile,MODE_PRIVATE);
        smsChoice = smsPrefs.getBoolean("sms_status",false);
        return smsChoice;
    }

}
