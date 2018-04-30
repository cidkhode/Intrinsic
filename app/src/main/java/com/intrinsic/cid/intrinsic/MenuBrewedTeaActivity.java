package com.intrinsic.cid.intrinsic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This is the activity which shows the user the different
brewed tea options they can order. Each button is clickable,
and allows the user to add a product to their order.
*/

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, MenuActivity.class));
        } else if (id == R.id.rewards_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, RewardsActivity.class));
        } else if (id == R.id.music_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, SpotifyActivity.class));
        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, ContactActivity.class));
        } else if (id == R.id.logout_option) {
            logout();
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(MenuBrewedTeaActivity.this, LandingPage.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout(){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Do you want to logout? Cart will be cleared!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                LandingPage.cart.clear();
                SharedPreferences cartClearer = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = cartClearer.edit();
                editor.putString("CART", "EMPTY");
                startActivity(new Intent(MenuBrewedTeaActivity.this, MainActivity.class));
                editor.putString("logout","1");
                editor.putString("password","");
                editor.apply();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public void selectSize(View view) {
        Button itemButton = (Button) view;
        String itemName = "BREWED TEA: " + itemButton.getText().toString().toUpperCase();
        Intent selectSize = new Intent(MenuBrewedTeaActivity.this, SelectSizeBrewedBubbleMilkshakeCoffee.class);
        selectSize
               .putExtra("itemName", itemName)
               .putExtra("smallSize", smallPrice)
               .putExtra("largeSize", largePrice);
        startActivity(selectSize);
    }
}
