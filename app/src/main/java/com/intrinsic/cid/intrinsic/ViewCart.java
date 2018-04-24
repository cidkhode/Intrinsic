package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewCart extends AppCompatActivity {
TextView cart;
TextView priceOfCart;
Button placeOrder;
double totalPrice;
String formattedPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        cart = (TextView) findViewById(R.id.cartView);
        priceOfCart = (TextView) findViewById(R.id.priceOfCartTextView);
        placeOrder = (Button) findViewById(R.id.placeOrder);

        if(LandingPage.cart.size() == 0) {
            cart.setText("Cart: \nNo Items to Show!");
            totalPrice = 0.00;
            placeOrder.setEnabled(false);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Cart: \n---\n");
            for(String key: LandingPage.cart.keySet()) {
                double priceOfEach = LandingPage.cart.get(key);
                totalPrice+=priceOfEach;
                sb.append(key + " \n$" + String.format("%.2f", priceOfEach) + "\n---\n");
            }
            String cartText = sb.toString();
            cart.setText(cartText);
            placeOrder.setEnabled(true);
        }
        formattedPrice = String.format("%.2f", totalPrice);
        priceOfCart.setText("$" + formattedPrice);

    }

    public void payOrder(View view) {
        Intent intent = new Intent(ViewCart.this, PayPalOrder.class);
        intent.putExtra("amount", formattedPrice);
        startActivity(intent);
    }
}
