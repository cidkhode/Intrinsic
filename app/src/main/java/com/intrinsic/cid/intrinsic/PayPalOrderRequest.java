package com.intrinsic.cid.intrinsic;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class PayPalOrderRequest extends StringRequest{
    private static final String LOGIN_REQ_URL = "https://web.njit.edu/~cww5/intrinsic/paypal_order.php";
    private Map<String, String> params;

    public PayPalOrderRequest(String phoneNumber, String amount, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQ_URL, listener, null);
        params = new HashMap<>();
        params.put("phoneNumber", phoneNumber);
        params.put("amount", amount);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
