package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuBrewedTeaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    double smallPrice = 2.00;
    double largePrice = 3.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_brewed_tea);
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
            startActivity(new Intent(MenuBrewedTeaActivity.this, MenuActivity.class));
        } else if (id == R.id.order_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, OrderActivity.class));
        } else if (id == R.id.rewards_option) {

        } else if (id == R.id.music_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, SpotifyActivity.class));
        } else if (id == R.id.specials_option) {

        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, ContactActivity.class));
        }  else if (id == R.id.logout_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, MainActivity.class));
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, LandingPage.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void selectSize(View view) {
        Button itemButton = (Button) view;
        String itemName = "BREWED TEA: " + itemButton.getText().toString();
        Intent selectSize = new Intent(MenuBrewedTeaActivity.this, SelectSize.class);
        selectSize
               .putExtra("itemName", itemName)
               .putExtra("smallSize", smallPrice)
               .putExtra("largeSize", largePrice);
        startActivity(selectSize);
    }
}
