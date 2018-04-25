package com.intrinsic.cid.intrinsic;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static HashMap<String, double[]> cart;
    String editMode = "false"; //this represents if the user is in edit mode or not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        Intent intent = getIntent();

        //display name using shared preferences
        cart = new HashMap<String, double[]>();
        SharedPreferences displayUserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String name = displayUserInfo.getString("name", "");
        String oldPhoneNumber = displayUserInfo.getString("oldPhoneNumber", "");
        String oldSecQues = displayUserInfo.getString("oldSecQues", "");
        String oldSecAns = displayUserInfo.getString("oldSecAns", "");
        String oldBirthdate = displayUserInfo.getString("oldBirthdate", "");
        String oldEmail = displayUserInfo.getString("oldEmail", "");
        int custIDint = displayUserInfo.getInt("custID", 0);
        final String custID = Integer.toString(custIDint);

        final TextView user_name_display = (TextView) findViewById(R.id.user_name_display);
        user_name_display.setText(name + "'s Account");

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);

        final EditText edit_phone_number = (EditText) findViewById(R.id.edit_phone_number);
        final EditText edit_password = (EditText) findViewById(R.id.edit_password);
        final EditText confirm_edit_password = (EditText) findViewById(R.id.confirm_edit_password);
        final EditText edit_name = (EditText) findViewById(R.id.edit_name);
        final EditText edit_question = (EditText) findViewById(R.id.edit_question);
        final EditText edit_answer = (EditText) findViewById(R.id.edit_answer);
        final EditText edit_birthday = (EditText) findViewById(R.id.edit_birthday);
        final EditText edit_email = (EditText) findViewById(R.id.edit_email);
        final Button edit_user = (Button) findViewById(R.id.edit_user);
        edit_phone_number.setText(oldPhoneNumber);
        //edit_password.setText();
        //confirm_edit_password.setText();
        edit_name.setText(name);
        edit_question.setText(oldSecQues);
        edit_answer.setText(oldSecAns);
        edit_birthday.setText(oldBirthdate);
        edit_email.setText(oldEmail);

        edit_phone_number.setEnabled(false);
        edit_password.setEnabled(false);
        confirm_edit_password.setEnabled(false);
        edit_name.setEnabled(false);
        edit_question.setEnabled(false);
        edit_answer.setEnabled(false);
        edit_birthday.setEnabled(false);
        edit_email.setEnabled(false);

        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMode.equals("false")) {
                    edit_user.setText("Save");
                    editMode = "true";
                    edit_phone_number.setEnabled(true);
                    edit_password.setEnabled(true);
                    confirm_edit_password.setEnabled(true);
                    edit_name.setEnabled(true);
                    edit_question.setEnabled(true);
                    edit_answer.setEnabled(true);
                    edit_birthday.setEnabled(true);
                    edit_email.setEnabled(true);
                    edit_phone_number.setBackgroundResource(R.color.colorBasicFull);
                    edit_password.setBackgroundResource(R.color.colorBasicFull);
                    confirm_edit_password.setBackgroundResource(R.color.colorBasicFull);
                    edit_name.setBackgroundResource(R.color.colorBasicFull);
                    edit_question.setBackgroundResource(R.color.colorBasicFull);
                    edit_answer.setBackgroundResource(R.color.colorBasicFull);
                    edit_birthday.setBackgroundResource(R.color.colorBasicFull);
                    edit_email.setBackgroundResource(R.color.colorBasicFull);
                }
                else {
                    final String phoneNumber = edit_phone_number.getText().toString();
                    final String password = edit_password.getText().toString();
                    final String confirmPassword = confirm_edit_password.getText().toString();
                    final String name = edit_name.getText().toString();
                    final String secQues = edit_question.getText().toString();
                    final String secAns = edit_answer.getText().toString();
                    final String birthdate = edit_birthday.getText().toString();
                    final String email = edit_email.getText().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Toast.makeText(LandingPage.this, "Successfully edited profile! Please check your spam folder if you don't get an email.", Toast.LENGTH_LONG).show();
                                    edit_user.setText("Edit");
                                    editMode = "false";
                                    edit_phone_number.setEnabled(false);
                                    edit_password.setEnabled(false);
                                    confirm_edit_password.setEnabled(false);
                                    edit_name.setEnabled(false);
                                    edit_question.setEnabled(false);
                                    edit_answer.setEnabled(false);
                                    edit_birthday.setEnabled(false);
                                    edit_email.setEnabled(false);
                                    edit_phone_number.setBackgroundResource(R.color.colorBasic);
                                    edit_password.setBackgroundResource(R.color.colorBasic);
                                    confirm_edit_password.setBackgroundResource(R.color.colorBasicFull);
                                    edit_name.setBackgroundResource(R.color.colorBasic);
                                    edit_question.setBackgroundResource(R.color.colorBasic);
                                    edit_answer.setBackgroundResource(R.color.colorBasic);
                                    edit_birthday.setBackgroundResource(R.color.colorBasic);
                                    edit_email.setBackgroundResource(R.color.colorBasic);

                                    //update sharedpreferences
                                    SharedPreferences UserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = UserInfo.edit();
                                    editor.putString("name", name);
                                    editor.putString("logout", "");
                                    editor.putString("oldPhoneNumber", phoneNumber);
                                    editor.putString("oldSecQues", secQues);
                                    editor.putString("oldSecAns", secAns);
                                    editor.putString("oldBirthdate", birthdate);
                                    editor.putString("oldEmail", email);
                                    editor.apply();
                                    user_name_display.setText(name + "'s Account");

                                } else {
                                    String warnings = jsonResponse.getString("warnings");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LandingPage.this);
                                    builder.setMessage(warnings).setNegativeButton("Retry", null).create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LandingRequest landingRequest = new LandingRequest(custID, phoneNumber, password, confirmPassword, name, secQues, secAns, birthdate, email, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LandingPage.this);
                    queue.add(landingRequest);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing to prevent going back to login screen without signing out
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_option) {
            startActivity(new Intent(LandingPage.this, MenuActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            startActivity(new Intent(LandingPage.this, SpotifyActivity.class));
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(LandingPage.this, ContactActivity.class));
        } else if (id == R.id.logout_option) {
            startActivity(new Intent(LandingPage.this, MainActivity.class));
            SharedPreferences UserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = UserInfo.edit();
            editor.putString("logout","1");
            editor.putString("password","");
            editor.apply();
        } else if (id == R.id.my_account_option) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
}