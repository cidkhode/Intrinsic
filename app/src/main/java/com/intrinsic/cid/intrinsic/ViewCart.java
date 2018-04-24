package com.intrinsic.cid.intrinsic;

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
        priceOfCart.setText("$" + String.format("%.2f", totalPrice));

    }

    public void placeOrder(View view) {
        Toast.makeText(this, "Ready to order!", Toast.LENGTH_LONG).show();
    }
}
