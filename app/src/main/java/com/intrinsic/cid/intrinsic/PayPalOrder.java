package com.intrinsic.cid.intrinsic;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intrinsic.cid.intrinsic.Payment.Payment;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class PayPalOrder extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration configuration = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Payment.PAYPAL_CLIENT_ID);
    Button pay_button;
    TextView amountText;
    String amount="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal_order);

        pay_button = (Button) findViewById(R.id.pay_button);
        amountText = (TextView) findViewById(R.id.amount);

        Intent getAmount = this.getIntent();
        amount = getAmount.getStringExtra("amount");
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
                if(confirmation != null) {
                    try {
                        String details = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails", details)
                                .putExtra("PaymentAmount", amount));

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
