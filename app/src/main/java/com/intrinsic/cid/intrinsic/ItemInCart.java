package com.intrinsic.cid.intrinsic;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This class is represents an individual item in the cart.
*/

public class ItemInCart {
    String itemName;
    int itemQuantity;
    double itemPriceOfItems;

    public ItemInCart(String name, double priceOfItems, int quantity) {
        itemName = name;
        itemQuantity = quantity;
        itemPriceOfItems = priceOfItems;
    }

    public String getItemName(){ return itemName; }
    public int getQuantity(){ return itemQuantity; }
    public void setItemQuantity(int quantity) { itemQuantity = quantity; }
    public double getPriceOfItem(){ return itemPriceOfItems; }
    public double getPriceOfItems(){ return getQuantity()* itemPriceOfItems; }
}
