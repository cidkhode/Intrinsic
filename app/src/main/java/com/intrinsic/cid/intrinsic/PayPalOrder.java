package com.intrinsic.cid.intrinsic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.intrinsic.cid.intrinsic.Payment.Payment;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Arrays;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This is the first activity a user uses to begin processing
a PayPal order. After ViewCart, this is the activity that starts
up which shows the total price of the order, and the
"Pay with PayPal" button. Upon clicking that, the user
is brought to a new activity through PayPal where they can sign in
with PayPal and process the order. Upon successful
"transaction" the user is brought to Payment Details.java

NOTE: As of right now, the user MUST enter the
Sandbox account details to process the order:
intrinsiccafeweb-buyer@gmail.com
cs491test
*/

public class PayPalOrder extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration configuration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Payment.PAYPAL_CLIENT_ID);
    Button pay_button;
    TextView amountText;
    String amount="";
    //////////////
    int amountInt;
    String stars;
    String reward;
    String phoneNumber;
    String details;
    //////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal_order);

        pay_button = (Button) findViewById(R.id.pay_button);
        amountText = (TextView) findViewById(R.id.amount);

        SharedPreferences displayUserInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String phoneNumber2 = displayUserInfo.getString("oldPhoneNumber", "");
        phoneNumber = phoneNumber2;

        Intent getAmount = this.getIntent();
        amount = getAmount.getStringExtra("amount");
        String[] amountSplit = amount.split("\\.");
        amountInt = Integer.parseInt(amountSplit[0]);
        //amountText.setTextColor(getResources().getColor(R.color.white));
        amountText.setText(amount);

        Intent intent = new Intent(this, PayPalService.class)
                .putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration);
        startService(intent);

        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD",
                        "Testing for Intrinsic App", PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(PayPalOrder.this, PaymentActivity.class)
                        .putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, configuration)
                        .putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
                startActivityForResult(intent, PAYPAL_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PAYPAL_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                //Toast.makeText(this, "CoNgRaTuLaTiOnS", Toast.LENGTH_SHORT).show();
                //can i put a volley request here to php for the rewards
                //_______________________________________________________________________________________
                ////////////////
                if(confirmation != null) {
                    try {
                        //___________________________________________________________________________________________________________
                        if (amountInt >= 4) {
                            String amountIntString = Integer.toString(amountInt);
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonResponse = new JSONObject(response);
                                        boolean success = jsonResponse.getBoolean("success");
                                        if (success) {
                                            stars = jsonResponse.getString("stars");
                                            reward = jsonResponse.getString("reward");
                                            String message = jsonResponse.getString("message");
                                            Toast.makeText(PayPalOrder.this, message, Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(PayPalOrder.this, PaymentDetails.class)
                                                    .putExtra("stars", stars)
                                                    .putExtra("reward", reward)
                                                    .putExtra("PaymentDetails", details)
                                                    .putExtra("PaymentAmount", amount));
                                        } else {
                                            String warnings = jsonResponse.getString("warnings");
                                            Toast.makeText(PayPalOrder.this, warnings, Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            PayPalOrderRequest payPalOrderRequest = new PayPalOrderRequest(phoneNumber, amountIntString, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(PayPalOrder.this);
                            queue.add(payPalOrderRequest);
                        }
                        details = confirmation.toJSONObject().toString(4);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(this, "Cancelled!", Toast.LENGTH_SHORT).show();
                }
            } else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID ) {
                Toast.makeText(this, "Invalid!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
