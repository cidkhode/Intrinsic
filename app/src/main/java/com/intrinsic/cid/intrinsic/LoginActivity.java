package com.intrinsic.cid.intrinsic;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone_Number = (EditText) findViewById(R.id.phone_number);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login_submit = (Button) findViewById(R.id.login_submit);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNumber = phone_Number.getText().toString();
                final String pass = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String name = jsonResponse.getString("name");
                                String phoneNumber2 = jsonResponse.getString("phoneNumber");

                                Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                                intent.putExtra("name", name);
                                intent.putExtra("phoneNumber", phoneNumber2);

                                LoginActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Unsuccessful").setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(phoneNumber, pass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

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
}
