package com.example.riskipradana.smslistenerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSmsFrom;
    private TextView tvSmsMessage;
    private Button btClose;
    public static final String EXTRA_SMS_NO = "extra_sms_no";
    public static final String EXTRA_SMS_MESSAGE = "extra_sms_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        tvSmsFrom = findViewById(R.id.tv_no);
        tvSmsMessage = findViewById(R.id.tv_message);
        btClose = findViewById(R.id.bt_close);
        btClose.setOnClickListener(this);

        String senderNO = getIntent().getStringExtra(EXTRA_SMS_NO);
        String senderMessage = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);
        tvSmsFrom.setText("From : "+ senderNO);
        tvSmsMessage.setText(senderMessage);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.bt_close){
            finish();
        }
    }
}
