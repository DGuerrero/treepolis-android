package com.quoders.apps.android.treepolis.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by davidguerrerodiaz on 21/02/16.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    //void inject(CheckinActivity activity);
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}
