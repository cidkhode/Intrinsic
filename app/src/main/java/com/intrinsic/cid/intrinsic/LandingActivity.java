package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        final TextView user_name_display = (TextView) findViewById(R.id.user_name_display);
        final TextView phone_number_display = (TextView) findViewById(R.id.phone_number_display);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        user_name_display.setText(name);
        phone_number_display.setText(phoneNumber);

    }
}