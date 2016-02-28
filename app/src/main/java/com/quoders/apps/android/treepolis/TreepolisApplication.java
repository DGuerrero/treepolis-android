package com.quoders.apps.android.treepolis;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.quoders.apps.android.treepolis.di.AppModule;
import com.quoders.apps.android.treepolis.di.CheckinComponent;
import com.quoders.apps.android.treepolis.di.CheckinModule;
import com.quoders.apps.android.treepolis.di.DaggerCheckinComponent;
import com.quoders.apps.android.treepolis.di.DaggerNetComponent;
import com.quoders.apps.android.treepolis.di.NetComponent;
import com.quoders.apps.android.treepolis.di.NetModule;

import io.fabric.sdk.android.Fabric;



/**
 * Created by davidguerrerodiaz on 31/03/15.
 */
public class TreepolisApplication extends Application {

    private NetComponent mNetComponent;
    private CheckinComponent mCheckinComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = com.codepath.dagger.components.DaggerNetComponent.create();
        mNetComponent = DaggerNetComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .netModule(new NetModule("https://api.github.com"))
                .build();

        mCheckinComponent = DaggerCheckinComponent.builder()
                .appModule(new AppModule(this))
                .checkinModule(new CheckinModule(""))
                .build();

        Fabric.with(this, new Crashlytics());

        //  Initialize Parse
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_key));

        // Initialize Facebook
        ParseFacebookUtils.initialize(getApplicationContext());
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public CheckinComponent getCheckinComponent() {
        return mCheckinComponent;
    }
}
