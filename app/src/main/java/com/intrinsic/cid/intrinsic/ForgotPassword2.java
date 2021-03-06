package com.intrinsic.cid.intrinsic;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This class is for allowing users to reset their password.
They have to pass this second checkpoint by entering the
correct answer to their security question. If so,
it will bring them to ForgotPassword3.java
*/

public class ForgotPassword2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);

        final TextView security_question = (TextView) findViewById(R.id.security_question);
        final EditText security_answer = (EditText) findViewById(R.id.security_answer);
        Intent intent = getIntent();
        String secQ = intent.getStringExtra("secQues");
        final String email = intent.getStringExtra("email");

        security_question.setText(secQ);

        final Button get_password_button = (Button) findViewById(R.id.retrieve_password_submit);
        get_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String secAns = security_answer.getText().toString();
                final String step = "two";

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(ForgotPassword2.this,"Please reset your password.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ForgotPassword2.this, ForgotPassword3.class);
                                intent.putExtra("email", email);
                                ForgotPassword2.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword2.this);
                                builder.setMessage("Incorrect, please try again.").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ForgotRequest forgotRequest = new ForgotRequest(step, secAns, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ForgotPassword2.this);
                queue.add(forgotRequest);
            }
        });
    }
}