package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewCart extends AppCompatActivity {
TextView cart;
ListView cartListView;
static TextView priceOfCart;
static Button placeOrder;
static double totalPrice;
String formattedPrice;
ArrayList<ItemInCart> itemNames;
CartListAdapter cartListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        cart = (TextView) findViewById(R.id.cartView);
        cartListView = (ListView) findViewById(R.id.cartListView);
        priceOfCart = (TextView) findViewById(R.id.priceOfCartTextView);
        placeOrder = (Button) findViewById(R.id.placeOrder);
        itemNames = new ArrayList<ItemInCart>();
        totalPrice = 0;
        if(LandingPage.cart.size() == 0) {
            cart.setText("Cart: No Items to Show!");
            itemNames.add(new ItemInCart("EMPTY", 0, 0));
            totalPrice = 0.00;
            placeOrder.setEnabled(false);
        } else {
            cart.setText("Cart:");
            for(String key: LandingPage.cart.keySet()) {
                double priceOfEach = LandingPage.cart.get(key)[0];
                int quantityOfEach = (int) LandingPage.cart.get(key)[1];
                itemNames.add(new ItemInCart(key, priceOfEach, quantityOfEach));
                totalPrice+=(priceOfEach*quantityOfEach);
            }
            placeOrder.setEnabled(true);
        }

        cartListAdapter = new CartListAdapter(this, R.layout.cart_list, itemNames);
        cartListView.setAdapter(cartListAdapter);

        formattedPrice = String.format("%.2f", totalPrice);
        priceOfCart.setText("$" + formattedPrice);
    }

    public void payOrder(View view) {
        Intent intent = new Intent(ViewCart.this, PayPalOrder.class);
        String priceOfCartText = priceOfCart.getText().toString();
        String price = priceOfCartText.substring(1, priceOfCartText.length());
        intent.putExtra("amount", price);
        startActivity(intent);
    }
}
