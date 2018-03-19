package com.intrinsic.cid.intrinsic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText create_phone_numnber = (EditText) findViewById(R.id.create_phone_number);
        final EditText create_password = (EditText) findViewById(R.id.create_password);
        final EditText create_name = (EditText) findViewById(R.id.create_name);
        final EditText create_question = (EditText) findViewById(R.id.create_question);
        final EditText create_answer = (EditText) findViewById(R.id.create_answer);
        final EditText create_birthday = (EditText) findViewById(R.id.create_birthday);
        final EditText create_email = (EditText) findViewById(R.id.create_email);

        final Button create_user = (Button) findViewById(R.id.create_user);

    }
}
