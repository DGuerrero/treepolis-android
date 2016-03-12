package com.quoders.apps.android.treepolis.ui.welcome;

import android.app.Activity;

import com.firebase.client.FirebaseError;
import com.quoders.apps.android.treepolis.ui.signup.SignupActivity;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public class WelcomePresenterImpl implements WelcomePresenter, WelcomeInteractorImpl.WelcomeInteractorListener {

    private WelcomeView mView;
    private WelcomeInteractor mInteractor;


    public WelcomePresenterImpl(WelcomeView welcomeView) {

        this.mView = welcomeView;
        this.mInteractor = new WelcomeInteractorImpl((Activity) welcomeView, this);
    }

    @Override
    public void onCreateAccountClick() {
        mView.LaunchSignupActivity();
    }

    @Override
    public void onActivityResultCalled(int requestCode, int result) {

        if (requestCode == SignupActivity.REQUEST_ID_SIGNUP && result == Activity.RESULT_OK) {
            mView.launchHomeActivity();
        }
    }

    @Override
    public void onLoginClick() {

        if (ValidateLoginFields()) {
            mView.showProgressDialogLogin();
            mInteractor.loginWithUserName(mView.getUsernameFieldText(), mView.getPasswordFieldText());
        }
    }

    @Override
    public void onLoginSuccessful() {
        mView.stopProgressDialog();
        mView.launchHomeActivity();
        mView.closeActivity();
    }

    @Override
    public void onLoginError(FirebaseError firebaseError) {
        mView.stopProgressDialog();
        mView.showLoginErrorAlertDialog();
    }

    private boolean ValidateLoginFields() {

        boolean result = true;

        //  Check empty fields
        if (mView.getUsernameFieldText().isEmpty()) {
            mView.setUsernameFieldEmptyError();
            result = false;
        }

        if (mView.getPasswordFieldText().isEmpty()) {
            mView.setPasswordFieldEmptyError();
            result = false;
        }

        return result;
    }
}
