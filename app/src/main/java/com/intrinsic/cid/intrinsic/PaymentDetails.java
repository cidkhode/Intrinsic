package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView txtId;
    TextView txtStatus;
    TextView txtAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtId = (TextView) findViewById(R.id.txtId);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtAmount = (TextView) findViewById(R.id.txtAmount);

        Intent intent = getIntent();

        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            txtId.setText(jsonObject.getJSONObject("response").getString("id"));
            txtStatus.setText(jsonObject.getJSONObject("response").getString("state"));
            txtAmount.setText(jsonObject.getJSONObject("response").getString(String.format("$%s",
                                                                   intent.getStringExtra("PaymentAmount"))));
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
}
