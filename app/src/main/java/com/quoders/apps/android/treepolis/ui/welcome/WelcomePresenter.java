package com.quoders.apps.android.treepolis.ui.welcome;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public interface WelcomePresenter {

    void onCreateAccountClick();

    void onActivityResultCalled(int requestCode, int result);

    void onLoginClick();
}
