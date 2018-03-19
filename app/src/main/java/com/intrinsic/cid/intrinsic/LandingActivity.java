package com.intrinsic.cid.intrinsic;

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
    }
}
