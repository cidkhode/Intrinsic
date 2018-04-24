package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
        navigationView.getMenu().getItem(4).setChecked(true);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_option) {
            startActivity(new Intent(SpotifyActivity.this, MenuActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            //do nothing
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(SpotifyActivity.this, ContactActivity.class));
        } else if (id == R.id.logout_option) {
            startActivity(new Intent(SpotifyActivity.this, MainActivity.class));
            SharedPreferences UserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = UserInfo.edit();
            editor.putString("logout","1");
            editor.putString("password","");
            editor.apply();
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(SpotifyActivity.this, LandingPage.class));
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
