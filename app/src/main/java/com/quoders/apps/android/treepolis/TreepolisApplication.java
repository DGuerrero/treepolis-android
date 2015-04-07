package com.quoders.apps.android.treepolis;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by davidguerrerodiaz on 31/03/15.
 */
public class TreepolisApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //  Initialize Parse
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        // Initialize Facebook
        ParseFacebookUtils.initialize(getApplicationContext());
    }
}
