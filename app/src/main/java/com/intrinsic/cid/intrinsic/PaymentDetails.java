package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.intrinsic.cid.intrinsic.Payment.Payment;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView txtId;
    TextView txtStatus;
    TextView txtAmount;
    TextView starStatus;
    TextView rewardStatus;
    //View advanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtId = (TextView) findViewById(R.id.txtId);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        starStatus = (TextView) findViewById(R.id.starStatus);
        rewardStatus = (TextView) findViewById(R.id.rewardStatus);

        //advanceButton = findViewById(R.id.advanceButton);
        //advanceButton.setVisibility(View.GONE);
        Intent intent = getIntent();
        String stars = intent.getStringExtra("stars");
        String reward = intent.getStringExtra("reward");
        String payment_amount = intent.getStringExtra("PaymentAmount");
        SharedPreferences userInfo = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = userInfo.edit();
        int starsI = Integer.parseInt(stars);
        editor.putInt("stars", starsI);
        editor.putString("reward", reward);
        editor.apply();
        try {
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"), payment_amount, stars, reward);
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing to prevent going back to login screen without signing out
    }

    private void showDetails(JSONObject response, String paymentAmount, String starsV, String rewardV){
        try{
            txtId.setText("Payment ID: " + response.getString("id"));
            txtStatus.setText("Your payment was: " + response.getString("state"));
            txtAmount.setText("Price paid: $" + paymentAmount);
            starStatus.setText("Your stars: " + starsV);
            rewardStatus.setText("Do you have a reward? " + rewardV);
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }
    public void landingPage(View view){
        Intent intent = new Intent(PaymentDetails.this, LandingPage.class);
        startActivity(intent);
    }
}
