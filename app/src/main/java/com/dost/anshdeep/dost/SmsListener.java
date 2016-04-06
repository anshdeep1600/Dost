package com.dost.anshdeep.dost;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsListener extends BroadcastReceiver {
    final SmsManager sms = SmsManager.getDefault();
    String senderNum,message;
    public void onReceive(Context context, Intent intent) {
        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    senderNum = phoneNumber;
                    message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "SenderNum: "+ senderNum + " Message: " + message);

                    Toast.makeText(context,"SenderNum: "+ senderNum + " | Message: " + message,Toast.LENGTH_LONG).show();

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
        if (message.equalsIgnoreCase("Ring") || message.equalsIgnoreCase("Silent") || message.equalsIgnoreCase("Vibrate")){
            RingProfile change = new RingProfile(context);
            change.changeRingProfile(message,senderNum);
        }
        else if (message.equalsIgnoreCase("Alarm")){
            RingProfile change = new RingProfile(context);
            change.raiseAlarm(senderNum);
        }
    }
}
