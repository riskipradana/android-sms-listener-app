package com.example.riskipradana.smslistenerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null){
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i=0; i<pdusObj.length; i++){
                    SmsMessage currentMessage =
                            getIncomingMessage(pdusObj[i], bundle);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver", "SenderNum: "+senderNum+"; message: "+message);

                    Intent showSmsIntent = new Intent(context, SmsReceiverActivity.class);
                    showSmsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, phoneNumber);
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE, message);
                    context.startActivity(showSmsIntent);

                }
             }
        }catch (Exception e){
            Log.e("SmsReveiver", "Exception smsReceiver"+e);
        }
    }

    private SmsMessage getIncomingMessage(Object o, Bundle bundle) {

        SmsMessage currentSMS;
        //M = 23
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) o, format);
        }else {
            currentSMS = SmsMessage.createFromPdu((byte[]) o);
        }

        return currentSMS;
    }
}
