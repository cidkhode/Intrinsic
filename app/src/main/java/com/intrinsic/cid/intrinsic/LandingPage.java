package com.intrinsic.cid.intrinsic;

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
import android.widget.TextView;

public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        final TextView user_name_display = (TextView) findViewById(R.id.user_name_display);
        Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        final String phoneNumber = intent.getStringExtra("phoneNumber");

        user_name_display.setText(name + "'s Account");
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


        /*
        final Button order_otg = (Button) findViewById(R.id.order_button);
        order_otg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, OrderActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("name", name);
                LandingPage.this.startActivity(intent);
            }
        });
        final Button musicBut = (Button) findViewById(R.id.music_button);
        order_otg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, MusicActivity.class);
                LandingPage.this.startActivity(intent);
            }
        });
        final Button contactBut = (Button) findViewById(R.id.contact_us_button);
        order_otg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, ContactUsActivity.class);
                LandingPage.this.startActivity(intent);
            }
        });*/
    }

    public void menuPage(View view)
    {
        Intent intent = new Intent(LandingPage.this, MenuActivity.class);
        startActivity(intent);
    }

    public void musicPage(View view)
    {
        Intent intent = new Intent(LandingPage.this, SpotifyActivity.class);
        startActivity(intent);
    }

    public void contactUsPage(View view)
    {
        Intent intent = new Intent(LandingPage.this, ContactActivity.class);
        startActivity(intent);
    }

    public void orderOTGPage(View view)
    { //Here I need to pass in the phone number...
        Intent intent = new Intent(LandingPage.this, OrderActivity.class);
        startActivity(intent);
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