package com.quoders.apps.android.treepolis;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import io.fabric.sdk.android.Fabric;

/**
 * Created by davidguerrerodiaz on 31/03/15.
 */
public class TreepolisApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        //  Initialize Parse
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        // Initialize Facebook
        ParseFacebookUtils.initialize(getApplicationContext());
    }
}
