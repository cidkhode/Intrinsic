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
import android.widget.Button;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This is the activity which shows the user the different
coffee and other related options they can order. Each button is clickable,
and allows the user to add a product to their order.
*/

public class MenuCoffeeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    double smallLattePrice = 2.65;
    double largeLattePrice = 3.65;

    double smallCappuccinoPrice = 3.15;
    double largeCappuccinoPrice = 4.15;

    double smallCaramelMacchiatoPrice = 3.15;
    double largeCaramelMacchiatoPrice = 4.15;

    double smallMochaPrice = 2.90;
    double largeMochaPrice = 3.90;

    double smallWhiteBlackMochasPrice = 3.15;
    double largeWhiteBlackMochasPrice = 4.15;

    double smallCoffeesPrice = 3.50;
    double largeCoffeesPrice = 4.50;

    double smallLoverPrice = 2.90;
    double largeLoverPrice = 3.40;

    double smallEspressoPrice = 1.90;
    double largeEspressoPrice = 3.40;

    double smallConPannaPrice = 2.15;
    double largeConPannaPrice = 2.90;

    double smallAmericanoPrice = 1.90;
    double largeAmericanoPrice = 2.65;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_coffee);
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
            startActivity(new Intent(MenuCoffeeActivity.this, MenuActivity.class));
        } else if (id == R.id.rewards_option) {
            startActivity(new Intent(MenuCoffeeActivity.this, RewardsActivity.class));
        } else if (id == R.id.music_option) {
            startActivity(new Intent(MenuCoffeeActivity.this, SpotifyActivity.class));
        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(MenuCoffeeActivity.this, ContactActivity.class));
        }  else if (id == R.id.logout_option) {
            logout();
        } else if (id == R.id.my_account_option) {
            startActivity(new Intent(MenuCoffeeActivity.this, LandingPage.class));
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
                startActivity(new Intent(MenuCoffeeActivity.this, MainActivity.class));
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
        String buttonText = itemButton.getText().toString().toUpperCase();
        String itemName = "COFFEE: " + buttonText;

        double smallPrice = 0.00;
        double largePrice = 0.00;

        int buttonId = itemButton.getId();

        if(buttonId == R.id.latte_coffee_button) {
            smallPrice = smallLattePrice;
            largePrice = largeLattePrice;
        } else if(buttonId == R.id.cappuccino_coffee_button) {
            smallPrice = smallCappuccinoPrice;
            largePrice = largeCappuccinoPrice;
        } else if(buttonId == R.id.caramel_macchiato_coffee_button) {
            smallPrice = smallCaramelMacchiatoPrice;
            largePrice = largeCaramelMacchiatoPrice;
        } else if(buttonId == R.id.mocha_coffee_button) {
            smallPrice = smallMochaPrice;
            largePrice = largeMochaPrice;
        } else if(buttonId == R.id.white_mocha_coffee_button || buttonId == R.id.black_white_mocha_coffee_button) {
            smallPrice = smallWhiteBlackMochasPrice;
            largePrice = largeWhiteBlackMochasPrice;
        } else if(buttonId == R.id.coffee_button || buttonId == R.id.vietnamese_coffee_button) {
            smallPrice = smallCoffeesPrice;
            largePrice = largeCoffeesPrice;
        } else if(buttonId == R.id.lover_coffee_button) {
            smallPrice = smallLoverPrice;
            largePrice = largeLoverPrice;
        } else if(buttonId == R.id.espresso_coffee_button) {
            smallPrice = smallEspressoPrice;
            largePrice = largeEspressoPrice;
        } else if(buttonId == R.id.con_panna_coffee_button) {
            smallPrice = smallConPannaPrice;
            largePrice = largeConPannaPrice;
        } else if(buttonId == R.id.americano_coffee_button) {
            smallPrice = smallAmericanoPrice;
            largePrice = largeAmericanoPrice;
        }

        Intent selectSize = new Intent(MenuCoffeeActivity.this, SelectSizeBrewedBubbleMilkshakeCoffee.class);
        selectSize
                .putExtra("itemName", itemName)
                .putExtra("smallSize", smallPrice)
                .putExtra("largeSize", largePrice);
        startActivity(selectSize);
    }
}
