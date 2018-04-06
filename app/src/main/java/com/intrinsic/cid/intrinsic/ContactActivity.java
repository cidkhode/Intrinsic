package com.intrinsic.cid.intrinsic;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageButton facebook = (ImageButton) findViewById(R.id.facebookButton);
        ImageButton instagram = (ImageButton) findViewById(R.id.instagramButton);
        ImageButton twitter = (ImageButton) findViewById(R.id.twitterButton);

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/intrinsiccafe/"));
                startActivity(browserIntent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/intrinsiccafe/?hl=en"));
                startActivity(browserIntent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/intrinsiccafe?lang=en"));
                startActivity(browserIntent);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(6).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
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
            startActivity(new Intent(ContactActivity.this, MenuActivity.class));
        } else if (id == R.id.order_option) {
            startActivity(new Intent(ContactActivity.this, OrderActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            startActivity(new Intent(ContactActivity.this, SpotifyActivity.class));
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            //do nothing
        } else if (id == R.id.logout_option) {
            startActivity(new Intent(ContactActivity.this, MainActivity.class));
            SharedPreferences UserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = UserInfo.edit();
            editor.putString("logout","1");
            editor.putString("password","");
            editor.apply();
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(ContactActivity.this, LandingPage.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void intrinsicCall(View view)
    {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:2016182689"));
        if (ActivityCompat.checkSelfPermission(ContactActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(ContactActivity.this, "Your device cannot make calls from this app", Toast.LENGTH_SHORT).show();
            //return;
            Intent callIntent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:2016182689"));
            startActivity(callIntent2);
        }
        startActivity(callIntent);
    }
    public void intrinsicEmail(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:intrinsiccafe@gmail.com?subject=" + "" + "&body=" + "");
        intent.setData(data);
        startActivity(intent);
    }
    public void intrinsicWebsite(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.intrinsiccafe.com/"));
        startActivity(spotifyIntent);
    }

}
