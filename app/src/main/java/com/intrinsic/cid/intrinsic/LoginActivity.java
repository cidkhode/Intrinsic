package com.intrinsic.cid.intrinsic;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.intrinsic.cid.intrinsic.AESCrypt.decrypt;
import static com.intrinsic.cid.intrinsic.AESCrypt.encrypt;

public class LoginActivity extends AppCompatActivity {
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone_Number = (EditText) findViewById(R.id.phone_number);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button login_submit = (Button) findViewById(R.id.login_submit);
        checkbox = (CheckBox) findViewById(R.id.saveCheck);

        SharedPreferences displayUserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String savedPhone = displayUserInfo.getString("phoneNumber", "");
        String savedPassword = displayUserInfo.getString("password", "");
        String logoutcue = displayUserInfo.getString("logout", "");

        try{
            savedPassword = decrypt(savedPassword);
            phone_Number.setText(savedPhone);
            password.setText(savedPassword);
        } catch(Exception e){
            phone_Number.setText(savedPhone);
            password.setText("");
        }
        checkbox.setChecked(true);

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
                                String phoneNumber2 = jsonResponse.getString("phoneNumber");
                                String pass2 = password.getText().toString();
                                int custID = jsonResponse.getInt("custID");
                                String name = jsonResponse.getString("name");
                                String secQues = jsonResponse.getString("secQues");
                                String secAns = jsonResponse.getString("secAns");
                                String birthdate = jsonResponse.getString("birthdate");
                                String email = jsonResponse.getString("email");
                                int stars = jsonResponse.getInt("stars");
                                String reward = jsonResponse.getString("reward");

                                SharedPreferences UserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = UserInfo.edit();
                                editor.putString("name", name);
                                editor.putString("phoneNumber", phoneNumber2);
                                editor.putString("logout","");
                                editor.putString("oldPhoneNumber", phoneNumber2);
                                editor.putString("oldSecQues", secQues);
                                editor.putString("oldSecAns", secAns);
                                editor.putString("oldBirthdate", birthdate);
                                editor.putString("oldEmail", email);
                                editor.putInt("custID", custID);
                                editor.putInt("stars", stars);
                                editor.putString("reward", reward);

                                if(checkbox.isChecked()){
                                    try{
                                        pass2 = encrypt(pass2);
                                        editor.putString("password",pass2);
                                    } catch(Exception e){
                                        editor.putString("password","");
                                    }
                                } else{
                                    editor.putString("password","");
                                }

                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                                /*intent.putExtra("custID", custID);
                                intent.putExtra("phoneNumber", phoneNumber2);
                                intent.putExtra("secQues", secQues);
                                intent.putExtra("secAns", secAns);
                                intent.putExtra("birthdate", birthdate);
                                intent.putExtra("email", email);*/
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

        if(logoutcue == "" && !"".equals(savedPassword)) {
            login_submit.performClick();
        }

    }
}
