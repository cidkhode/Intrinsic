package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectSizeBrewedBubbleMilkshakeCoffee extends AppCompatActivity {

    String itemName;
    double itemPrice = 0.0;
    double smallSize;
    double largeSize;
    final int maxQuantity = 10;

    TextView smallPrice;
    TextView largePrice;

    Spinner selectFlavor1;
    Spinner selectFlavor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_brewed_bubble_milkshake_coffee);

        smallPrice = (TextView) findViewById(R.id.smallPriceTextView);
        largePrice = (TextView) findViewById(R.id.largePriceTextView);
        selectFlavor1 = (Spinner) findViewById(R.id.flavorOption1_brewed_bubble_milkshake_coffee);
        selectFlavor2 = (Spinner) findViewById(R.id.flavorOption2_brewed_bubble_milkshake_coffee);

        Intent intent = this.getIntent();
        itemName = intent.getStringExtra("itemName");
        smallSize = intent.getDoubleExtra("smallSize", 0);
        largeSize = intent.getDoubleExtra("largeSize", 0);
        smallPrice.setText("Price: $" + String.format("%.2f", smallSize));
        largePrice.setText("Price: $" + String.format("%.2f", largeSize));

        Flavors flavors = new Flavors();

        ArrayList<String> additionalFlavors = flavors.getSlushie_smoothie_milkshakes_flavors();
        System.out.println("-----------NAME: " + itemName);
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
        String itemWithFlavors = "";
        String itemNameWithSize = "";
        if(b.getText().toString().equalsIgnoreCase("S M A L L")) {
            itemPrice = smallSize;
            itemNameWithSize = "SMALL " + itemName;
        } else if(b.getText().toString().equalsIgnoreCase("L A R G E")) {
            itemPrice = largeSize;
            itemNameWithSize = "LARGE " + itemName;
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
            System.out.println("------EXECUTING THIS IF---------------");
            double quantity = LandingPage.cart.get(itemWithFlavors)[1];
            quantity++;
            System.out.println("-----------------QUANTITY: " + quantity + " and ITEM PRICE: " + itemPrice + " and multiplied: " + itemPrice*quantity);
            if((int) quantity == maxQuantity) {
                Toast.makeText(this, "Cannot add anymore of this item! Reached max limit!", Toast.LENGTH_LONG).show();
            } else {
                LandingPage.cart.put(itemWithFlavors, new double[]{itemPrice, quantity});
                Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
            }
        } else {
            System.out.println("------EXECUTING THIS ELSE---------------");
            LandingPage.cart.put(itemWithFlavors, new double[]{itemPrice, 1});
            Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(SelectSizeBrewedBubbleMilkshakeCoffee.this, MenuActivity.class));
        finish();
    }
}
