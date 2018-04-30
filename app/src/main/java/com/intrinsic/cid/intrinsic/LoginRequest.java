package com.intrinsic.cid.intrinsic;

import com.android.volley.Request;
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

This submits a volley request to php to
check if the user has a valid account.
*/

public class LoginRequest extends StringRequest{
    private static final String LOGIN_REQ_URL = "https://web.njit.edu/~cww5/intrinsic/login.php";
    private Map<String, String> params;

    public LoginRequest(String phoneNumber, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQ_URL, listener, null);
        params = new HashMap<>();
        params.put("phoneNumber", phoneNumber);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
