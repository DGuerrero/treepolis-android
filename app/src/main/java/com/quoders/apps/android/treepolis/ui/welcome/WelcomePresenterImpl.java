package com.quoders.apps.android.treepolis.ui.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.parse.ParseException;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.ui.home.HomeActivity;
import com.quoders.apps.android.treepolis.ui.signup.SignupActivity;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public class WelcomePresenterImpl implements WelcomePresenter, WelcomeInteractorImpl.WelcomeInteractorListener {

    private WelcomeView mView;
    private WelcomeInteractor mInteractor;
    private Context mContext;


    public WelcomePresenterImpl(WelcomeView welcomeView) {

        this.mView = welcomeView;
        this.mInteractor = new WelcomeInteractorImpl((Activity)welcomeView, this);
        this.mContext = (Context)welcomeView;
    }


    @Override
    public void onCreateAccountClick() {

        mView.launchActivity(new Intent((Context)mView, SignupActivity.class));
    }


    @Override
    public void onActivityResultCalled(int requestCode, int result) {

        if(requestCode == SignupActivity.REQUEST_ID_SIGNUP && result == Activity.RESULT_OK) {
            mView.launchActivity(new Intent((Context)mView, HomeActivity.class));
        }
    }


    @Override
    public void onLoginClick() {

        if(ValidateLoginFields()) {
            mView.showProgressDialog(R.string.login_progress);
            mInteractor.loginWithUserName(mView.getUsernameFieldText(), mView.getPasswordFieldText());
        }
    }


    @Override
    public void onLoginSuccessful() {
        mView.stopProgressDialog();
        mView.launchActivity(new Intent(mContext, HomeActivity.class));
        mView.closeActivity();
    }


    @Override
    public void onLoginError(ParseException exception) {
        mView.stopProgressDialog();
        mView.showAlertDialog(R.string.login_error_title, getLoginErrorTextMessage(exception));
    }


    private boolean ValidateLoginFields() {

        boolean result = true;

        //  Check empty fields
        if(mView.getUsernameFieldText().isEmpty()) {
            mView.setFieldUsernameError(R.string.field_error_empty);
            result = false;
        }

        if(mView.getPasswordFieldText().isEmpty()) {
            mView.setFieldPasswordError(R.string.field_error_empty);
            result = false;
        }

        return result;
    }

    int getLoginErrorTextMessage(ParseException e) {

        int message = R.string.unknown_error;

        switch (e.getCode()) {
            case ParseException.OBJECT_NOT_FOUND:
                message = R.string.login_error_user_not_found;
                break;
        }

        return message;
    }
}
