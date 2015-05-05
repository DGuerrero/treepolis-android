package com.quoders.apps.android.treepolis.signup;

import android.content.Context;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupInteractorImpl implements SignupInteractor {

    private Context mContext;


    public SignupInteractorImpl(Context context) {

        this.mContext = context;
    }


    public void createAccount(String email, String password) {

        /*
        ParseUser user = new ParseUser();
        user.setUsername("my name");
        user.setPassword("my pass");
        user.setEmail("email@example.com");

        // other fields can be set just like with ParseObject
        user.put("phone", "650-555-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
        */
    }


}
