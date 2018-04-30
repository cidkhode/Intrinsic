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

This submits a volley request to check if the user
signed up properly.
*/

public class SignUpRequest extends StringRequest{

    private static final String SIGNUP_REQ_URL = "https://web.njit.edu/~cww5/intrinsic/sign_up.php";
    private Map<String, String> params;

    public SignUpRequest(String phoneNumber, String password, String confirmPassword, String name, String secQues, String secAns,
                         String birthdate, String email, Response.Listener<String> listener) {
        super(Method.POST, SIGNUP_REQ_URL, listener, null);
        params = new HashMap<>();
        params.put("phoneNumber", phoneNumber);
        params.put("password", password);
        params.put("confirmPassword", confirmPassword);
        params.put("name", name);
        params.put("secQues", secQues);
        params.put("secAns", secAns);
        params.put("birthdate", birthdate);
        params.put("email", email);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
