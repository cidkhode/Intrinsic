package com.intrinsic.cid.intrinsic;

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
    public double getPriceOfItems(){ return getQuantity()* itemPriceOfItems; }
}
