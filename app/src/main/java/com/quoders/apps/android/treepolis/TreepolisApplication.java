package com.quoders.apps.android.treepolis;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by davidguerrerodiaz on 31/03/15.
 */
public class TreepolisApplication extends Application {

    private static final String PARSE_APP_ID = "LVENJltnSKPJ9WuFELQcY4DGY0WbGYR2bTsknara";
    private static final String PARSE_CLIENT_KEY = "SCDgdXIkEB6FfxmuU3EuD5HwSJtAqMEmLAhPSZ6S";


    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, PARSE_APP_ID, PARSE_CLIENT_KEY);
    }
}
