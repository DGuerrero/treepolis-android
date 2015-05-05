package com.quoders.apps.android.treepolis.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.quoders.apps.android.treepolis.signup.SignupActivity;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public class WelcomePresenterImpl implements WelcomePresenter {

    private WelcomeView mView;
    private WelcomeInteractor mInteractor;


    public WelcomePresenterImpl(WelcomeView welcomeView) {

        this.mView = welcomeView;
        this.mInteractor = new WelcomeInteractorImpl((Activity)welcomeView);
    }


    @Override
    public void onCreateAccountClick() {

        Intent intent = new Intent((Context)mView, SignupActivity.class);
        mView.launchActivity(intent);
    }
}
