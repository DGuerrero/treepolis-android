package com.quoders.apps.android.treepolis.ui.welcome;

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
public class WelcomeInteractorImpl implements WelcomeInteractor {

    private Context                     mContext;
    private WelcomeInteractorListener   mListener;


    public WelcomeInteractorImpl(Context context, WelcomeInteractorListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }


    @Override
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


    @Override
    public void loginWithUserName(String username, String password) {

        ParseUser.logInInBackground(username, password, new LogInCallback() {

            public void done(ParseUser user, ParseException e) {

                if (mListener != null) {

                    if (user != null) {
                        mListener.onLoginSuccessful();
                    }
                    else {
                        mListener.onLoginError(e);
                    }
                }
            }
        });
    }


    public interface WelcomeInteractorListener {
        void onLoginSuccessful();
        void onLoginError(ParseException exception);
    }
}
