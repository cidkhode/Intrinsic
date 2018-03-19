package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone_numnber = (EditText) findViewById(R.id.phone_number);
        final EditText password = (EditText) findViewById(R.id.password);

        final Button login_submit = (Button) findViewById(R.id.login_submit);

        final TextView forgotPassword = (TextView) findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    public void landingPage(View view)
    {
        Intent intent = new Intent(LoginActivity.this, LandingActivity.class);
        startActivity(intent);
    }
}
