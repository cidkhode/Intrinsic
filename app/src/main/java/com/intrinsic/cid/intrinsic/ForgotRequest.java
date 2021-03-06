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

This class is for submitting a volley request to the
php code, processing the forgot password sequence.
*/

public class ForgotRequest extends StringRequest{

    private static final String FORGOT_REQ_URL = "https://web.njit.edu/~cww5/intrinsic/forgot.php";
    private Map<String, String> params;

    public ForgotRequest(String step, String field1, String field2, Response.Listener<String> listener) {
        super(Method.POST, FORGOT_REQ_URL, listener, null);
        params = new HashMap<>();
        if (step.equals("one")){
            params.put("step", step);
            params.put("phoneNumber", field1);
            params.put("birthdate", field2);
        }
        else if (step.equals("two")) {
            params.put("step", step);
            params.put("secAns", field1);
            params.put("email", field2);
        }
        else if (step.equals("three")) {
            params.put("step", step);
            params.put("passwords", field1);
            params.put("email", field2);
        }
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
