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

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText create_phone_number = (EditText) findViewById(R.id.create_phone_number);
        final EditText create_password = (EditText) findViewById(R.id.create_password);
        final EditText create_name = (EditText) findViewById(R.id.create_name);
        final EditText create_question = (EditText) findViewById(R.id.create_question);
        final EditText create_answer = (EditText) findViewById(R.id.create_answer);
        final EditText create_birthday = (EditText) findViewById(R.id.create_birthday);
        final EditText create_email = (EditText) findViewById(R.id.create_email);

        final Button create_user = (Button) findViewById(R.id.create_user);

        create_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNumber = create_phone_number.getText().toString();
                final String password = create_password.getText().toString();
                final String name = create_name.getText().toString();
                final String secQues = create_question.getText().toString();
                final String secAns = create_answer.getText().toString();
                final String birthdate = create_birthday.getText().toString();
                final String email = create_email.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                Toast.makeText(SignUpActivity.this,"Signup Successful! If you don't see an email right away, please check your spam folder.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                SignUpActivity.this.startActivity(intent);
                            }
                            else{
                                String warnings = jsonResponse.getString("warnings");
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(warnings).setNegativeButton("Retry", null).create().show();
                                //Toast.makeText(SignUpActivity.this,"Signup Unsuccessful. Please Try Again!", Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                SignUpRequest signUpRequest = new SignUpRequest(phoneNumber, password, name, secQues, secAns, birthdate, email,responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(signUpRequest);
            }
        });
    }
}
