package com.intrinsic.cid.intrinsic;

import java.util.HashMap;

public class Cart {
    public HashMap<String, Double> items_with_prices;
    public Cart(HashMap<String, Double> item_with_prices) {
        this.items_with_prices = item_with_prices;
    }

    public HashMap<String, Double> getItems_with_prices() {
        System.out.println("---CART: " + items_with_prices.toString() + " ---");
        return items_with_prices;
    }

    public void addItem(String item, double price) {
        items_with_prices.put(item, price);
        System.out.println("---PUT ITEM: " + item + " IN THE CART WITH PRICE: " + price + " + !");
    }

    public double getPriceOfItem(String item) {
        if(items_with_prices.containsKey(item)) {
            return items_with_prices.get(item);
        }
        else {
            System.out.println("---KEY: " + item + " DOES NOT EXIST---");
            return 0.00;
        }
    }
}
