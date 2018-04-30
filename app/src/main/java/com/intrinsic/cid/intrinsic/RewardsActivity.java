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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This activity shows the user how many
rewards they have.
*/

public class RewardsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences displayUserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        int stars = displayUserInfo.getInt("stars", 0);
        String starsStr = Integer.toString(stars); //string to display on top
        String starsStr2 = ""; //string to display on bottom if needed
        String reward = displayUserInfo.getString("reward", "");
        String rewardMessage = "";
        int starsLeft = stars;
        TextView starsView = (TextView) findViewById(R.id.starsCount);
        TextView rewardView = (TextView) findViewById(R.id.rewardView);

        if (stars < 10) {
            starsLeft = 10 - stars;
            starsStr2 = Integer.toString(starsLeft);
            rewardMessage = starsStr2 + " stars until next reward!";
        }
        else if (stars == 10) {
            rewardMessage = "You have a reward to redeem!";
        }
        starsView.setText(starsStr);
        rewardView.setText(rewardMessage);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar); // initiate the progress bar
        progressBar.setMax(10);
        progressBar.setProgress(stars);

        ImageView star1 = (ImageView) findViewById(R.id.starProg1);
        ImageView star2 = (ImageView) findViewById(R.id.starProg2);
        ImageView star3 = (ImageView) findViewById(R.id.starProg3);
        ImageView star4 = (ImageView) findViewById(R.id.starProg4);
        ImageView star5 = (ImageView) findViewById(R.id.starProg5);
        ImageView star6 = (ImageView) findViewById(R.id.starProg6);
        ImageView star7 = (ImageView) findViewById(R.id.starProg7);
        ImageView star8 = (ImageView) findViewById(R.id.starProg8);
        ImageView star9 = (ImageView) findViewById(R.id.starProg9);
        ImageView star10 = (ImageView) findViewById(R.id.starProg10);
        if (stars == 1){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 2){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 3){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 4){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 5){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
            star5.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 6){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
            star5.setColorFilter(getResources().getColor(R.color.yellow));
            star6.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 7){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
            star5.setColorFilter(getResources().getColor(R.color.yellow));
            star6.setColorFilter(getResources().getColor(R.color.yellow));
            star7.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 8){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
            star5.setColorFilter(getResources().getColor(R.color.yellow));
            star6.setColorFilter(getResources().getColor(R.color.yellow));
            star7.setColorFilter(getResources().getColor(R.color.yellow));
            star8.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 9){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
            star5.setColorFilter(getResources().getColor(R.color.yellow));
            star6.setColorFilter(getResources().getColor(R.color.yellow));
            star7.setColorFilter(getResources().getColor(R.color.yellow));
            star8.setColorFilter(getResources().getColor(R.color.yellow));
            star9.setColorFilter(getResources().getColor(R.color.yellow));
        }
        else if (stars == 10){
            star1.setColorFilter(getResources().getColor(R.color.yellow));
            star2.setColorFilter(getResources().getColor(R.color.yellow));
            star3.setColorFilter(getResources().getColor(R.color.yellow));
            star4.setColorFilter(getResources().getColor(R.color.yellow));
            star5.setColorFilter(getResources().getColor(R.color.yellow));
            star6.setColorFilter(getResources().getColor(R.color.yellow));
            star7.setColorFilter(getResources().getColor(R.color.yellow));
            star8.setColorFilter(getResources().getColor(R.color.yellow));
            star9.setColorFilter(getResources().getColor(R.color.yellow));
            star10.setColorFilter(getResources().getColor(R.color.yellow));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(2).setChecked(true);
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
            startActivity(new Intent(RewardsActivity.this, MenuActivity.class));
        } else if (id == R.id.rewards_option) {
            //do nothing
        } else if (id == R.id.music_option) {
            startActivity(new Intent(RewardsActivity.this, SpotifyActivity.class));
        } else if (id == R.id.contact_us_option) {
            startActivity(new Intent(RewardsActivity.this, ContactActivity.class));
        } else if (id == R.id.logout_option) {
           logout();
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
                startActivity(new Intent(RewardsActivity.this, MainActivity.class));
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
}
