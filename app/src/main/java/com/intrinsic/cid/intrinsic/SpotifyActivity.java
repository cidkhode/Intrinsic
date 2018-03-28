package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SpotifyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spotify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spotify, menu);
        return true;
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

        if (id == R.id.home_option) {
            // Handle the camera action
        } else if (id == R.id.menu_option) {

        } else if (id == R.id.order_option) {
            startActivity(new Intent(SpotifyActivity.this, OrderActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            //do nothing
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(SpotifyActivity.this, ContactUsActivity.class));
        } else if (id == R.id.my_account_option) {

        } else if (id == R.id.logout_option) {
            startActivity(new Intent(SpotifyActivity.this, MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openPlaylist1(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/spotify/playlist/37i9dQZF1DWSqmBTGDYngZ?si=D7BfmebvRN24JxaclSmteA"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist2(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/thewolfeman99/playlist/68mX4U9iqFeibp1t9X82gy?si=PrKa9hKFQ1Wp_cpOpSNk2Q"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist3(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/1225813554/playlist/0cUoffCqblGCNdQftuCpSj?si=IJofllCkQtqvW3dWebd-oQ"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist4(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/spotify/playlist/37i9dQZF1DX2MyUCsl25eb?si=h5YIo_OOTryizuJXBV586A"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist5(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/intrinsiccafe/playlist/3ktL1Q2N0rsntPJpfYGuSB?si=kcefN64CRr26gTXXa2xudQ"));
        startActivity(spotifyIntent);
    }
    public void openPlaylist6(View view)
    {
        Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://open.spotify.com/user/intrinsiccafe/playlist/3ktL1Q2N0rsntPJpfYGuSB?si=URMUNyBmT2a_ccg5qqIbdQ"));
        startActivity(spotifyIntent);
    }
}
