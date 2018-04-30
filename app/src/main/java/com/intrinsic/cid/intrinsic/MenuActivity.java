package com.intrinsic.cid.intrinsic;

import android.content.DialogInterface;
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
import android.view.MenuItem;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This is the first menu activity a user interacts with.
They will have the following options:
View Cart
Bubble Tea
Brewed Tea
Smoothies/Slushies
Milkshakes
Coffee
*/

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
            //do nothing
        } else if (id == R.id.rewards_option) {
            startActivity(new Intent(MenuActivity.this, RewardsActivity.class));
        } else if (id == R.id.music_option) {
            startActivity(new Intent(MenuActivity.this, SpotifyActivity.class));
        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(MenuActivity.this, ContactActivity.class));
        }  else if (id == R.id.logout_option) {
            logout();
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(MenuActivity.this, LandingPage.class));
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
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
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

    public void bubblePage(View view)
    {
        Intent intent = new Intent(MenuActivity.this, MenuBubbleTeaActivity.class);
        startActivity(intent);
    }

    public void brewedPage(View view)
    {
        Intent intent = new Intent(MenuActivity.this, MenuBrewedTeaActivity.class);
        startActivity(intent);
    }

    public void smoothiePage(View view)
    {
        Intent intent = new Intent(MenuActivity.this, MenuSmoothieActivity.class);
        startActivity(intent);
    }

    public void milkshakePage(View view)
    {
        Intent intent = new Intent(MenuActivity.this, MenuMilkshakeActivity.class);
        startActivity(intent);
    }

    public void coffeePage(View view)
    {
        Intent intent = new Intent(MenuActivity.this, MenuCoffeeActivity.class);
        startActivity(intent);
    }

    public void viewCart(View view) {
        Intent intent = new Intent(MenuActivity.this, ViewCart.class);
        startActivity(intent);
    }
}
