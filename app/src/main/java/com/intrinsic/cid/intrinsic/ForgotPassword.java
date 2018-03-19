package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {
//This activity is for a user(s) who forgot their password.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText forgot_password_text = (EditText) findViewById(R.id.retrieve_password_through_email);
        //final Button retrieve_password_submit = (Button) findViewById(R.id.retrieve_password_submit);
    }

    public void mainPage(View view)
    {
        Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
        startActivity(intent);
    }

}
