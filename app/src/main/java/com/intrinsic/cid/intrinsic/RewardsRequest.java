package com.intrinsic.cid.intrinsic;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/*
4-26-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This submits a volley request to redeem a reward.
*/

public class RewardsRequest extends StringRequest{

    private static final String SIGNUP_REQ_URL = "https://web.njit.edu/~cww5/intrinsic/rewards.php";
    private Map<String, String> params;

    public RewardsRequest(String phoneNumber, Response.Listener<String> listener) {
        super(Method.POST, SIGNUP_REQ_URL, listener, null);
        params = new HashMap<>();
        params.put("phoneNumber", phoneNumber);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
