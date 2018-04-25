package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectSizeBrewedBubbleMilkshakeCoffee extends AppCompatActivity {

    String itemName;
    double itemPrice = 0.0;
    double smallSize;
    double largeSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_brewed_bubble_milkshake_coffee);
        Intent intent = this.getIntent();
        itemName = intent.getStringExtra("itemName");
        smallSize = intent.getDoubleExtra("smallSize", 0);
        largeSize = intent.getDoubleExtra("largeSize", 0);
    }

    public void addToCart(View view) {
        Button b = (Button) view;
        if(b.getText().toString().equalsIgnoreCase("S M A L L")) {
            itemPrice = smallSize;
        } else if(b.getText().toString().equalsIgnoreCase("L A R G E")) {
            itemPrice = largeSize;
        }
        LandingPage.cart.put(itemName, itemPrice);
        Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SelectSizeBrewedBubbleMilkshakeCoffee.this, MenuActivity.class));
    }
}