package com.quoders.apps.android.treepolis.welcome;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class WelcomeInteractorImpl {

    private Context mContext;

    public WelcomeInteractorImpl(Context context) {
        this.mContext = context;
    }

    public void loginWithFacebook() {

        Collection<String> permissions = Arrays.asList("public_profile");

        ParseFacebookUtils.logInWithReadPermissionsInBackground((Activity)mContext, permissions, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                }
            }
        });
    }
}
