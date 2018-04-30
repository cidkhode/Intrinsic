package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This is the activity which allows the user to select flavors and size
for slushies and smoothies. This also adds the items to the cart.
*/

public class SelectSizeSlushieSmoothie extends AppCompatActivity {

    String itemName;
    double itemPrice = 0.0;
    double smallSmoothieSize;
    double largeSmoothieSize;
    double smallSlushieSize;
    double largeSlushieSize;
    final int maxQuantity = 10;

    TextView smallSmoothiePrice;
    TextView largeSmoothiePrice;
    TextView smallSlushiePrice;
    TextView largeSlushiePrice;

    Spinner selectFlavor1;
    Spinner selectFlavor2;
    SharedPreferences.Editor cartDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_slushie_smoothie);
        cartDetails = PreferenceManager.getDefaultSharedPreferences(this).edit();

        smallSmoothiePrice = (TextView) findViewById(R.id.small_smoothie_price);
        largeSmoothiePrice = (TextView) findViewById(R.id.large_smoothie_price);
        smallSlushiePrice = (TextView) findViewById(R.id.small_slushie_price);
        largeSlushiePrice = (TextView) findViewById(R.id.large_slushie_price);
        selectFlavor1 = (Spinner) findViewById(R.id.flavorOption1_slushie_smoothie);
        selectFlavor2 = (Spinner) findViewById(R.id.flavorOption2_slushie_smoothie);

        Flavors flavors = new Flavors();

        Intent intent = this.getIntent();
        itemName = intent.getStringExtra("itemName");
        smallSmoothieSize = intent.getDoubleExtra("smallSmoothieSize", 0);
        largeSmoothieSize = intent.getDoubleExtra("largeSmoothieSize", 0);
        smallSlushieSize = intent.getDoubleExtra("smallSlushieSize", 0);
        largeSlushieSize = intent.getDoubleExtra("largeSlushieSize", 0);

        smallSmoothiePrice.setText("Price: $" + String.format("%.2f", smallSmoothieSize));
        largeSmoothiePrice.setText("Price: $" + String.format("%.2f", largeSmoothieSize));
        smallSlushiePrice.setText("Price: $" + String.format("%.2f", smallSlushieSize));
        largeSlushiePrice.setText("Price: $" + String.format("%.2f", largeSlushieSize));

        ArrayList<String> additionalFlavors = flavors.getSlushie_smoothie_milkshakes_flavors();
        additionalFlavors.remove(additionalFlavors.indexOf(itemName));
        additionalFlavors.add(0, "None");
        ArrayAdapter<String> flavors1 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        additionalFlavors);

        ArrayAdapter<String> flavors2 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item,
                        additionalFlavors);

        selectFlavor1.setAdapter(flavors1);
        selectFlavor2.setAdapter(flavors2);
    }

    public void addToCart(View view) {
        Button b = (Button) view;
        StringBuilder stringBuilder = new StringBuilder();
        String newItemName = "";
        String itemWithFlavors = "";
        String itemNameWithSize = "";
        int id = b.getId();
        if(id == R.id.small_smoothie || id == R.id.large_smoothie) {
            newItemName = "SMOOTHIE: " + itemName;
        } else if(b.getId() == R.id.small_slushie || b.getId() == R.id.large_slushie) {
            newItemName = "SLUSHIE: " + itemName;
        }
        if(b.getText().toString().equalsIgnoreCase("S M A L L")) {
            if(newItemName.contains("SMOOTHIE")) {
                itemPrice = smallSmoothieSize;
            } else if(newItemName.contains("SLUSHIE")){
                itemPrice = smallSlushieSize;
            }
            itemNameWithSize = "SMALL " + newItemName;
        } else if(b.getText().toString().equalsIgnoreCase("L A R G E")) {
            if(newItemName.contains("SMOOTHIE")) {
                itemPrice = largeSmoothieSize;
            } else if(newItemName.contains("SLUSHIE")){
                itemPrice = largeSlushieSize;
            }
            itemNameWithSize = "LARGE " + newItemName;
        }
        stringBuilder.append(itemNameWithSize);
        String selectedFlavor1 = selectFlavor1.getSelectedItem().toString();
        String selectedFlavor2 = selectFlavor2.getSelectedItem().toString();

        if(selectedFlavor1.equalsIgnoreCase("None")) {
            stringBuilder.append("");
        } else stringBuilder.append(" w/ " + selectedFlavor1);

        if(selectedFlavor2.equalsIgnoreCase("None")) {
            stringBuilder.append("");
        } else stringBuilder.append(" w/ " + selectedFlavor2);

        itemWithFlavors = stringBuilder.toString();
        if(LandingPage.cart.containsKey(itemWithFlavors)) {
            double quantity = LandingPage.cart.get(itemWithFlavors)[1];
            quantity++;
            if((int) quantity > maxQuantity) {
                Toast.makeText(this, "Cannot add anymore of this item! Reached max limit!", Toast.LENGTH_LONG).show();
            } else {
                LandingPage.cart.put(itemWithFlavors, new double[]{itemPrice, quantity});
                Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
            }
        } else {
            LandingPage.cart.put(itemWithFlavors, new double[]{itemPrice, 1});
            Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
        }

        Gson gson = new Gson();
        String json = gson.toJson(LandingPage.cart);
        cartDetails.putString("CART", json);
        cartDetails.commit();

        startActivity(new Intent(SelectSizeSlushieSmoothie.this, MenuActivity.class));
        finish();
    }
}
