package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectSizeSlushieSmoothie extends AppCompatActivity {

    String itemName;
    double itemPrice = 0.0;
    double smallSmoothieSize;
    double largeSmoothieSize;
    double smallSlushieSize;
    double largeSlushieSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_size_slushie_smoothie);
        Intent intent = this.getIntent();
        itemName = intent.getStringExtra("itemName");
        smallSmoothieSize = intent.getDoubleExtra("smallSmoothieSize", 0);
        largeSmoothieSize = intent.getDoubleExtra("largeSmoothieSize", 0);
        smallSlushieSize = intent.getDoubleExtra("smallSlushieSize", 0);
        largeSlushieSize = intent.getDoubleExtra("largeSlushieSize", 0);
    }

    public void addToCart(View view) {
        Button b = (Button) view;
        String newItemName = "";

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
        } else if(b.getText().toString().equalsIgnoreCase("L A R G E")) {
            if(newItemName.contains("SMOOTHIE")) {
                itemPrice = largeSmoothieSize;
            } else if(newItemName.contains("SLUSHIE")){
                itemPrice = largeSlushieSize;
            }
        }
        LandingPage.cart.put(newItemName, itemPrice);
        Toast.makeText(this, "Added to cart!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SelectSizeSlushieSmoothie.this, MenuActivity.class));
    }
}
