package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

public class MenuMilkshakeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    double smallPrice = 2.00;
    double largePrice = 3.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_milkshake);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);
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
            startActivity(new Intent(MenuMilkshakeActivity.this, MenuActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            startActivity(new Intent(MenuMilkshakeActivity.this, SpotifyActivity.class));
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(MenuMilkshakeActivity.this, ContactActivity.class));
        } else if (id == R.id.logout_option) {
            startActivity(new Intent(MenuMilkshakeActivity.this, MainActivity.class));
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(MenuMilkshakeActivity.this, LandingPage.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectSize(View view) {
        Button itemButton = (Button) view;
        String itemName = "MILKSHAKE: " + itemButton.getText().toString().toUpperCase();

        Intent selectSize = new Intent(MenuMilkshakeActivity.this, SelectSizeBrewedBubbleMilkshakeCoffee.class);
        selectSize
                .putExtra("itemName", itemName)
                .putExtra("smallSize", smallPrice)
                .putExtra("largeSize", largePrice);
        startActivity(selectSize);
    }
}
