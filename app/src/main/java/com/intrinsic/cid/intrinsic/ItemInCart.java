package com.intrinsic.cid.intrinsic;

public class ItemInCart {
    String itemName;
    int itemQuantity;
    double itemPriceOfItem;

    public ItemInCart(String name, double priceOfItems, int quantity) {
        itemName = name;
        itemQuantity = quantity;
        itemPriceOfItem = priceOfItems;
    }

    public String getItemName(){ return itemName; }
    public int getQuantity(){ return itemQuantity; }
    public void setItemQuantity(int quantity) { itemQuantity = quantity; }
    public double getPricePerItem(){ return itemPriceOfItem; }
}
