package com.intrinsic.cid.intrinsic;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        final TextView user_name_display = (TextView) findViewById(R.id.user_name_display);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String oldNum = intent.getStringExtra("phoneNumber");

        user_name_display.setText(name + "'s Account " + oldNum);
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
        final EditText edit_name = (EditText) findViewById(R.id.edit_name);
        final EditText edit_question = (EditText) findViewById(R.id.edit_question);
        final EditText edit_answer = (EditText) findViewById(R.id.edit_answer);
        final EditText edit_birthday = (EditText) findViewById(R.id.edit_birthday);
        final EditText edit_email = (EditText) findViewById(R.id.edit_email);

        final Button edit_user = (Button) findViewById(R.id.edit_user);

        edit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNumber = edit_phone_number.getText().toString();
                final String password = edit_password.getText().toString();
                final String name = edit_name.getText().toString();
                final String secQues = edit_question.getText().toString();
                final String secAns = edit_answer.getText().toString();
                final String birthdate = edit_birthday.getText().toString();
                final String email = edit_email.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                Toast.makeText(LandingPage.this,"Successfully Edited profile!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String warnings = jsonResponse.getString("warnings");
                                AlertDialog.Builder builder = new AlertDialog.Builder(LandingPage.this);
                                builder.setMessage(warnings).setNegativeButton("Retry", null).create().show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                LandingRequest landingRequest = new LandingRequest(oldNum, phoneNumber, password, name, secQues, secAns, birthdate, email,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LandingPage.this);
                queue.add(landingRequest);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_option) {
            startActivity(new Intent(LandingPage.this, MenuActivity.class));
        } else if (id == R.id.order_option) {
            startActivity(new Intent(LandingPage.this, OrderActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            startActivity(new Intent(LandingPage.this, SpotifyActivity.class));
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(LandingPage.this, ContactActivity.class));
        } else if (id == R.id.logout_option) {
            startActivity(new Intent(LandingPage.this, MainActivity.class));
        } else if (id == R.id.my_account_option) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}