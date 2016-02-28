package com.quoders.apps.android.treepolis.di;

import android.app.Application;

import com.quoders.apps.android.treepolis.ui.checkin.CheckinPresenter;
import com.quoders.apps.android.treepolis.ui.checkin.CheckinPresenterImpl;
import com.quoders.apps.android.treepolis.utils.IQSharedPrefs;
import com.quoders.apps.android.treepolis.utils.QSharedPrefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by davidguerrerodiaz on 24/02/16.
 */
@Module
public class CheckinModule {

    String mModule;

    public CheckinModule(String module) {
        this.mModule = module;
    }

    @Provides
    @Singleton
    CheckinPresenter provideCheckinPresenter(IQSharedPrefs preferences, Application application) {
        CheckinPresenter presenter = new CheckinPresenterImpl(preferences, application);
        return presenter;
    }

    @Provides
    @Singleton
    IQSharedPrefs providesSharedPreferences(Application application) {
        return new QSharedPrefs(application);
    }
}
