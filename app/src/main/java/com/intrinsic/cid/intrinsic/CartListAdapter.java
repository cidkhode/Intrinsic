package com.intrinsic.cid.intrinsic;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class CartListAdapter extends ArrayAdapter<ItemInCart> {
    double price;
    double quantity;

    public CartListAdapter(Context context, int resource) {
        super(context, resource);
    }

    public CartListAdapter(Context context, int resource, List<ItemInCart> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cart_list, null);
        }

        if(ViewCart.totalPrice == 0) {
            ViewCart.placeOrder.setEnabled(false);
            ViewCart.placeOrder.setBackgroundResource(R.color.fadedBlack);
            ViewCart.placeOrder.setTextColor(getContext().getResources().getColor(R.color.fadedWhite));
        } else {
            ViewCart.placeOrder.setEnabled(true);
            ViewCart.placeOrder.setBackgroundResource(R.color.black);
            ViewCart.placeOrder.setTextColor(getContext().getResources().getColor(R.color.white));
        }

        final ItemInCart p = getItem(position);
        final Spinner quantityOfItemInCart = (Spinner) v.findViewById(R.id.quantityOfItemInCart);
        TextView itemNameInCart = (TextView) v.findViewById(R.id.itemNameInCart);
        final TextView itemPriceInCart = (TextView) v.findViewById(R.id.itemPriceInCart);
        final Button deleteItemFromCart = (Button) v.findViewById(R.id.deleteItemFromCart);

        if (p != null && !p.getItemName().equals("EMPTY")) {
            price = p.getPriceOfItems();
            final String itemName = p.getItemName();
            quantity = p.getQuantity();
            if((int) quantity == 1) {
                quantityOfItemInCart.setSelection(0);
            } else if((int) quantity == 2) {
                quantityOfItemInCart.setSelection(1);
            } else if((int) quantity == 3) {
                quantityOfItemInCart.setSelection(2);
            } else if((int) quantity == 4) {
                quantityOfItemInCart.setSelection(3);
            } else if((int) quantity == 5) {
                quantityOfItemInCart.setSelection(4);
            } else if((int) quantity == 6) {
                quantityOfItemInCart.setSelection(5);
            } else if((int) quantity == 7) {
                quantityOfItemInCart.setSelection(6);
            } else if((int) quantity == 8) {
                quantityOfItemInCart.setSelection(7);
            } else if((int) quantity == 9) {
                quantityOfItemInCart.setSelection(8);
            } else if((int) quantity == 10) {
                quantityOfItemInCart.setSelection(9);
            }

            itemPriceInCart.setText("$ " + String.format("%.2f", price));

            quantityOfItemInCart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    RelativeLayout rl = (RelativeLayout) ((ViewGroup) view.getParent().getParent());
                    TextView title = (TextView) rl.getChildAt(0);
                    String name = title.getText().toString();
                    price = p.getPriceOfItems();
                    ViewCart.totalPrice -= price;
                    quantity = i+1;
                    p.setItemQuantity((int)quantity);
                    price = p.getPriceOfItems();
                    ViewCart.totalPrice += price;
                    ViewCart.priceOfCart.setText("$" + String.format("%.2f", ViewCart.totalPrice));
                    LandingPage.cart.put(name, new double[]{price, quantity});
                    itemPriceInCart.setText("$ " + String.format("%.2f", price));
                    //Toast.makeText(getContext(), "Quantity: " + quantity + " for: " + title.getText().toString() + " and price is now: " + price, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            deleteItemFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout rl = (RelativeLayout) view.getParent();
                    Spinner quantitySelector = (Spinner) rl.getChildAt(1);
                    TextView title = (TextView) rl.getChildAt(0);
                    String name = title.getText().toString();
                    ViewCart.totalPrice -= LandingPage.cart.get(name)[0];
                    LandingPage.cart.remove(itemName);
                    disableButton(deleteItemFromCart, quantitySelector, itemPriceInCart);
                    ViewCart.priceOfCart.setText("$" + String.format("%.2f", ViewCart.totalPrice));
                    if(ViewCart.totalPrice == 0) {
                        ViewCart.placeOrder.setEnabled(false);
                        ViewCart.placeOrder.setBackgroundResource(R.color.fadedBlack);
                        ViewCart.placeOrder.setTextColor(getContext().getResources().getColor(R.color.fadedWhite));
                    } else {
                        ViewCart.placeOrder.setEnabled(true);
                        ViewCart.placeOrder.setBackgroundResource(R.color.black);
                        ViewCart.placeOrder.setTextColor(getContext().getResources().getColor(R.color.white));
                    }
                }
            });
            itemNameInCart.setText(itemName);
            itemPriceInCart.setText("$ " + String.format("%.2f", price));
        } else {
            quantityOfItemInCart.setVisibility(View.GONE);
            itemNameInCart.setText("Nothing to show!");
            itemPriceInCart.setText("$ " + String.format("%.2f", 0.00));
            disableButton(deleteItemFromCart, quantityOfItemInCart, itemPriceInCart);
        }
        return v;
    }

    public void disableButton(Button button, Spinner quantity, TextView price) {
        button.setEnabled(false);
        button.setBackgroundResource(R.drawable.rounded_red_faded_outline);
        button.setTextColor(button.getResources().getColor(R.color.fadedRed));
        quantity.setEnabled(false);
        quantity.setBackgroundResource(R.drawable.rounded_black_faded_outline);
        price.setBackgroundResource(R.drawable.rounded_blue_faded_button);
        price.setTextColor(getContext().getResources().getColor(R.color.fadedBlue));
        if(ViewCart.totalPrice == 0) {
            ViewCart.placeOrder.setEnabled(false);
            ViewCart.placeOrder.setBackgroundResource(R.color.fadedBlack);
            ViewCart.placeOrder.setTextColor(getContext().getResources().getColor(R.color.fadedWhite));
        }
    }
}
