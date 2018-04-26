package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_slushie_smoothie);

        smallSmoothiePrice = (TextView) findViewById(R.id.small_smoothie_price);
        largeSmoothiePrice = (TextView) findViewById(R.id.large_smoothie_price);
        smallSlushiePrice = (TextView) findViewById(R.id.small_slushie_price);
        largeSlushiePrice = (TextView) findViewById(R.id.large_slushie_price);

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
    }

    public void addToCart(View view) {
        Button b = (Button) view;
        String newItemName = "";
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
        if(LandingPage.cart.containsKey(itemNameWithSize)) {
            double quantity = LandingPage.cart.get(itemName)[1];
            quantity++;
            double price=itemPrice*quantity;
            if((int) quantity == maxQuantity) {
                Toast.makeText(this, "Cannot add anymore of this item! Reached max limit!", Toast.LENGTH_LONG).show();
            } else {
                LandingPage.cart.put(itemNameWithSize, new double[]{price, quantity});
                Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
            }
        } else {
            LandingPage.cart.put(itemNameWithSize, new double[]{itemPrice, 1});
            Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(SelectSizeSlushieSmoothie.this, MenuActivity.class));
        finish();
    }
}
