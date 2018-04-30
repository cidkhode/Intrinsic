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
They have to pass this first checkpoint by entering the
correct phone number and birthdate. If so,
it will bring them to ForgotPassword2.java
*/

public class ForgotPassword extends AppCompatActivity {
//This activity is for a user(s) who forgot their password.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText forgot_password_phone = (EditText) findViewById(R.id.retrieve_password_through_phone);
        final EditText forgot_password_birthdate = (EditText) findViewById(R.id.retrieve_password_through_birthdate);
        final Button retrieve_password_button = (Button) findViewById(R.id.retrieve_password_submit);

        retrieve_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phone = forgot_password_phone.getText().toString();
                final String birthdate = forgot_password_birthdate.getText().toString();
                final String step = "one";

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String secQues = jsonResponse.getString("secQues");
                                String secAns = jsonResponse.getString("secAns");
                                String email = jsonResponse.getString("email");

                                Toast.makeText(ForgotPassword.this,"That was correct, one more question.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ForgotPassword.this, ForgotPassword2.class);
                                intent.putExtra("secQues", secQues);
                                intent.putExtra("secAns", secAns);
                                intent.putExtra("email", email);

                                ForgotPassword.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPassword.this);
                                builder.setMessage("Incorrect, please try again.").setNegativeButton("Retry", null).create().show();
                                //Toast.makeText(ForgotPassword.this,"Incorrect, please try again.", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ForgotRequest forgotRequest = new ForgotRequest(step, phone, birthdate, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ForgotPassword.this);
                queue.add(forgotRequest);
            }
        });
    }
}
