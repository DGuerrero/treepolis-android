package com.quoders.apps.android.treepolis.di;

import com.quoders.apps.android.treepolis.ui.checkin.CheckinActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by davidguerrerodiaz on 24/02/16.
 */
@Singleton
@Component(modules={AppModule.class, CheckinModule.class})
public interface CheckinComponent {
    void inject(CheckinActivity activity);
}
