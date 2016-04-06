package com.dost.anshdeep.dost;

import android.content.Context;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

public class RingProfile {
    Context mContext;
    Ringing r = new Ringing();
    public RingProfile(){
    }
    public RingProfile(Context mContext){
        this.mContext = mContext;
    }
    public void changeRingProfile(String msg, String sender){
        String choice = msg.trim();
        AudioManager amngr = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        String message;
        if (choice.equalsIgnoreCase("Ring")){
            amngr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            amngr.setStreamVolume(AudioManager.STREAM_RING, amngr.getStreamMaxVolume(AudioManager.STREAM_RING), AudioManager.FLAG_ALLOW_RINGER_MODES);
            message = "Changed to Ringer Mode";
            if (r.smsChoice(mContext)){
                Toast.makeText(mContext,"Message will be Sent",Toast.LENGTH_SHORT).show();
                sendMessage(sender,message);
            }
            else {
                Toast.makeText(mContext,"Changed to Ringer Mode", Toast.LENGTH_LONG).show();
            }
        }
        else if (choice.equalsIgnoreCase("Vibrate")){
            amngr.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            message = "Changed to Vibrate Mode";
            if (r.smsChoice(mContext)){
                Toast.makeText(mContext,"Message will be Sent",Toast.LENGTH_SHORT).show();
                sendMessage(sender,message);
            }
            else {
                Toast.makeText(mContext,"Changed to Vibrate Mode", Toast.LENGTH_LONG).show();
            }
        }
        else if (choice.equalsIgnoreCase("Silent")){
            amngr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            message = "Changed to Silent Mode";
            if (r.smsChoice(mContext)){
                Toast.makeText(mContext,"Message will be Sent",Toast.LENGTH_SHORT).show();
                sendMessage(sender,message);
            }
            else {
                Toast.makeText(mContext,message, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void raiseAlarm(String sender){
        String message = "Alarm will Start Ringing Soon";
        AudioManager amngr = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
        amngr.setStreamVolume(AudioManager.STREAM_MUSIC, amngr.getStreamMaxVolume(AudioManager.STREAM_MUSIC),AudioManager.FLAG_PLAY_SOUND);
        amngr.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(mContext,notification);
        ringtone.play();
        if (r.smsChoice(mContext)){
            sendMessage(sender,message);
            Toast.makeText(mContext,"Message will be Sent",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
    public void sendMessage(String sender, String message){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sender,null,message,null,null);
    }
}
