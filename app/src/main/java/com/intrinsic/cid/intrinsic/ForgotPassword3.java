package com.intrinsic.cid.intrinsic;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
This is where they can actually reset their password.
They must enter a valid password, and this will get sent
hashed to the DB if it is valid.
*/

public class ForgotPassword3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password3);

        final EditText enter_password = (EditText) findViewById(R.id.enter_password);
        final EditText enter_confirmed_password = (EditText) findViewById(R.id.enter_confirmed_password);
        Intent intent = getIntent();
        final String email = intent.getStringExtra("email");

        final Button reset_password_button = (Button) findViewById(R.id.reset_password_submit);
        reset_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String passwords = enter_password.getText().toString() + "<split>" + enter_confirmed_password.getText().toString();
                final String step = "three";

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(ForgotPassword3.this,"Please check your inbox/spam folder for your new password.", Toast.LENGTH_LONG).show();
                                Intent intent2 = new Intent(ForgotPassword3.this, MainActivity.class);
                                ForgotPassword3.this.startActivity(intent2);
                            } else {
                                String warnings = jsonResponse.getString("warnings");
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword3.this);
                                builder.setMessage(warnings).setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ForgotRequest forgotRequest = new ForgotRequest(step, passwords, email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ForgotPassword3.this);
                queue.add(forgotRequest);
            }
        });
    }
}
