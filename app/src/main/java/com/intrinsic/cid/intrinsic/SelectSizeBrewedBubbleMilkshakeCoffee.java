package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class SelectSizeBrewedBubbleMilkshakeCoffee extends AppCompatActivity {

    String itemName;
    double itemPrice = 0.0;
    double smallSize;
    double largeSize;
    TextView smallPrice;
    TextView largePrice;
    final int maxQuantity = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_brewed_bubble_milkshake_coffee);

        smallPrice = (TextView) findViewById(R.id.smallPriceTextView);
        largePrice = (TextView) findViewById(R.id.largePriceTextView);
        Intent intent = this.getIntent();
        itemName = intent.getStringExtra("itemName");
        smallSize = intent.getDoubleExtra("smallSize", 0);
        largeSize = intent.getDoubleExtra("largeSize", 0);
        smallPrice.setText("Price: $" + String.format("%.2f", smallSize));
        largePrice.setText("Price: $" + String.format("%.2f", largeSize));
    }

    public void addToCart(View view) {
        Button b = (Button) view;
        String itemNameWithSize = "";
        if(b.getText().toString().equalsIgnoreCase("S M A L L")) {
            itemPrice = smallSize;
            itemNameWithSize = "SMALL " + itemName;
        } else if(b.getText().toString().equalsIgnoreCase("L A R G E")) {
            itemPrice = largeSize;
            itemNameWithSize = "LARGE " + itemName;
        }
        if(LandingPage.cart.containsKey(itemNameWithSize)) {
            System.out.println("------EXECUTING THIS IF---------------");
            double quantity = LandingPage.cart.get(itemNameWithSize)[1];
            quantity++;
            System.out.println("-----------------QUANTITY: " + quantity + " and ITEM PRICE: " + itemPrice + " and multiplied: " + itemPrice*quantity);
           // double price = itemPrice*quantity;
            if((int) quantity == maxQuantity) {
                Toast.makeText(this, "Cannot add anymore of this item! Reached max limit!", Toast.LENGTH_LONG).show();
            } else {
                LandingPage.cart.put(itemNameWithSize, new double[]{itemPrice, quantity});
                Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
            }
        } else {
            System.out.println("------EXECUTING THIS ELSE---------------");
            LandingPage.cart.put(itemNameWithSize, new double[]{itemPrice, 1});
            Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(SelectSizeBrewedBubbleMilkshakeCoffee.this, MenuActivity.class));
        finish();
    }
}
