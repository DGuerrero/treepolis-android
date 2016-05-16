package com.quoders.apps.android.treepolis.di;

import android.app.Application;

import com.quoders.apps.android.treepolis.domain.checkin.CheckinTreeStorage;
import com.quoders.apps.android.treepolis.domain.checkin.TreePicturesInteractor;
import com.quoders.apps.android.treepolis.domain.checkin.TreePicturesInteractorImpl;
import com.quoders.apps.android.treepolis.domain.checkin.TreeUploaderInteractor;
import com.quoders.apps.android.treepolis.domain.checkin.TreeUploaderInteractorImpl;
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
    TreeUploaderInteractor provideTreeUploaderInteractor(Application application) {
        TreeUploaderInteractor interactor = new TreeUploaderInteractorImpl(application);
        return interactor;
    }

    @Provides
    @Singleton
    TreePicturesInteractor provideCheckinInteractor(Application application, CheckinTreeStorage treeStorage) {
        TreePicturesInteractor interactor = new TreePicturesInteractorImpl(application, treeStorage);
        return interactor;
    }

    @Provides
    @Singleton
    CheckinPresenter provideCheckinPresenter(Application application, CheckinTreeStorage treeStorage) {
        CheckinPresenter presenter = new CheckinPresenterImpl(application, treeStorage);
        return presenter;
    }

    @Provides
    @Singleton
    IQSharedPrefs providesSharedPreferences(Application application) {
        return new QSharedPrefs(application);
    }

    @Provides
    @Singleton
    CheckinTreeStorage provideTreeStorage(IQSharedPrefs preferences) {
        return new CheckinTreeStorage(preferences);
    }
}
