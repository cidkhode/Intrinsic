package com.intrinsic.cid.intrinsic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
4-7-2018    CS 491 - Senior Project     Intrinsic Cafe App
Group Members:
Connor Watson   (PM, Developer)
Chidanand Khode (Co-PM, Developer)
Kevin Le        (Developer)
Spruha Shah     (UX/UI, Web Developer)

This is the first activity a user interacts with.
They will have two options:
1) Log In to their existing account
2) Sign Up for a new account
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginPage(View view)
    {   //This method starts the login page
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void signUpPage(View view)
    {   //This method starts the sign up page
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {   //Do nothing to prevent logging back in
    }
}
