package com.intrinsic.cid.intrinsic;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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

        final ItemInCart p = getItem(position);
        final EditText quantityOfItemInCart = (EditText) v.findViewById(R.id.quantityOfItemInCart);
        TextView itemNameInCart = (TextView) v.findViewById(R.id.itemNameInCart);
        final TextView itemPriceInCart = (TextView) v.findViewById(R.id.itemPriceInCart);
        final Button deleteItemFromCart = (Button) v.findViewById(R.id.deleteItemFromCart);

        System.out.println("----------------------------------------------PRICE OF CART: " + ViewCart.totalPrice);

        if (p != null && !p.getItemName().equals("EMPTY")) {
            price = p.getPricePerItem();
            final String itemName = p.getItemName();
            quantity = p.getQuantity();
            itemPriceInCart.setText("$ " + Double.toString(price));
            quantityOfItemInCart.setText(Integer.toString((int) quantity));
            /*
            quantityOfItemInCart.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(quantityOfItemInCart.getText().length() == 0 || quantityOfItemInCart.getText().toString().equals("0")) {
                        LandingPage.cart.remove(itemName);
                        disableButton(deleteItemFromCart);
                        Toast.makeText(getContext(), "Quantity cannot be zero!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        enableButton(deleteItemFromCart);
                        quantity = (double) Integer.parseInt(quantityOfItemInCart.getText().toString());
                        System.out.println("--------------------------------------BEGIN---------------------------------------------");
                        System.out.println("#################Total price before changing: " + ViewCart.totalPrice);
                        ViewCart.totalPrice -= price;
                        System.out.println("#################Price before change : " + price);
                        System.out.println("#################Total Price after subtracing old price : " + ViewCart.totalPrice);
                        price *= quantity;
                        System.out.println("#################Price after change : " + price);
                        itemPriceInCart.setText(Double.toString(price));
                        LandingPage.cart.put(itemName, new double[]{price, quantity});
                        ViewCart.totalPrice += price;
                        System.out.println("#################Total Price after adding new price : " + ViewCart.totalPrice);
                        System.out.println("---------------------------------------END--------------------------------------------");
                        ViewCart.priceOfCart.setText("$" + String.format("%.2f", ViewCart.totalPrice));
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });*/
            deleteItemFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RelativeLayout rl = (RelativeLayout) view.getParent();
                    TextView title = (TextView) rl.getChildAt(0);
                    String name = title.getText().toString();
                    System.out.println("-----------------------NAME: " + name);
                    ViewCart.totalPrice -= LandingPage.cart.get(name)[0];
                    LandingPage.cart.remove(itemName);
                    disableButton(deleteItemFromCart);
                    ViewCart.priceOfCart.setText("$" + String.format("%.2f", ViewCart.totalPrice));
                    //notifyDataSetChanged();
                }
            });
            itemNameInCart.setText(itemName);
            itemPriceInCart.setText(String.format("%.2f", price));
        } else {
            quantityOfItemInCart.setText("0");
            itemNameInCart.setText("Nothing to show!");
            itemPriceInCart.setText("0");
            disableButton(deleteItemFromCart);
        }

        return v;
    }

    public void disableButton(Button button) {
        button.setEnabled(false);
        button.setBackgroundResource(R.drawable.rounded_red_faded_outline);
        button.setTextColor(button.getResources().getColor(R.color.fadedRed));
    }

    public void enableButton(Button button) {
        button.setEnabled(true);
        button.setBackgroundResource(R.drawable.rounded_red_outline);
        button.setTextColor(button.getResources().getColor(R.color.red));
    }
}
