package com.intrinsic.cid.intrinsic;

import java.util.HashMap;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This class is the cart for storing user orders.
*/

public class Cart {
    public HashMap<String, double[]> items_with_prices;
    public Cart(HashMap<String, double[]> item_with_prices) {
        this.items_with_prices = item_with_prices;
    }

    public HashMap<String, double[]> getItems_with_prices() {
        System.out.println("---CART: " + items_with_prices.toString() + " ---");
        return items_with_prices;
    }

    public void addItem(String item, double price, double quantity) {
        items_with_prices.put(item, new double[]{price, quantity});
        System.out.println("---PUT ITEM: " + item + " IN THE CART WITH PRICE: " + price + " + AND QUANTITY: " + quantity + " !");
    }

    public double getPriceOfItem(String item) {
        if(items_with_prices.containsKey(item)) {
            return items_with_prices.get(item)[0];
        }
        else {
            System.out.println("---KEY: " + item + " DOES NOT EXIST---");
            return 0.00;
        }
    }

    public double getQuantityOfItem(String item) {
        if(items_with_prices.containsKey(item)) {
            return items_with_prices.get(item)[1];
        }
        else {
            System.out.println("---KEY: " + item + " DOES NOT EXIST---");
            return 0;
        }
    }
}
