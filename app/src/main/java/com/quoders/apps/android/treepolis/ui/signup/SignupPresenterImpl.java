package com.quoders.apps.android.treepolis.ui.signup;

import android.content.Context;
import android.content.Intent;

import com.firebase.client.FirebaseError;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.ui.home.HomeActivity;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupPresenterImpl implements SignupPresenter, SignupInteractorImpl.SignupListener {

    public static final String REGEX_EMAIL = "[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}";

    private SignupView          mView;
    private SignupInteractor    mInteractor;
    private Context             mContext;


    public SignupPresenterImpl(SignupView view) {

        mView = view;
        mContext = (Context)view;
        mInteractor = new SignupInteractorImpl(mContext, this);
    }

    @Override
    public void onCreateAccountClick() {

        if(ValidateCreateAccountFields()) {
            mView.showProgressDialog(mContext.getString(R.string.signup_progress));
            mInteractor.SignUpUser(mView.getFieldUserName(), mView.getFieldEmail(), mView.getFieldPassword());
        }
    }

    private boolean ValidateCreateAccountFields() {

        boolean result = true;

        //  Check valid email
        if(!mView.getFieldEmail().matches(REGEX_EMAIL)) {
            mView.setFieldEmailError(R.string.field_error_email_invalid);
            result = false;
        }

        //  Check passwords not equal
        if(!mView.getFieldPassword().equalsIgnoreCase(mView.getFieldPasswordConfirm())) {
            mView.setFieldPasswordConfirmError(R.string.field_error_password_diferent);
            result = false;
        }

        //  Check empty fields
        if(mView.getFieldEmail().isEmpty()) {
            mView.setFieldEmailError(R.string.field_error_empty);
            result = false;
        }

        if(mView.getFieldPassword().isEmpty()) {
            mView.setFieldPasswordError(R.string.field_error_empty);
            result = false;
        }

        if(mView.getFieldPasswordConfirm().isEmpty()) {
            mView.setFieldPasswordConfirmError(R.string.field_error_empty);
            result = false;
        }

        return result;
    }

    @Override
    public void onSignupSuccess() {
        mView.stopProgressDialog();
        mView.launchActivity(new Intent(mContext, HomeActivity.class));
        mView.finishActivity();
    }

    @Override
    public void onSignupError(int errorMessage) {
        mView.stopProgressDialog();
        mView.showAlertDialog(mContext.getString(R.string.signup_error), getSignUpErrorMessage(errorMessage));
    }

    @Override
    public void onLoginErrorAfterSignup() {
        mView.stopProgressDialog();
        mView.showAlertDialog(mContext.getString(R.string.signup_error), mContext.getString(R.string.signup_error_unable_to_login));
    }


    private String getSignUpErrorMessage(int code) {

        String errorMessage = mContext.getString(R.string.signup_error_unknown);

        switch (code) {
            case FirebaseError.EMAIL_TAKEN:
                errorMessage = mContext.getString(R.string.signup_error_email_taken);
                break;
            case FirebaseError.INVALID_EMAIL:
                errorMessage = mContext.getString(R.string.signup_error_email_invalid);
                break;
            case FirebaseError.UNAVAILABLE:
                errorMessage = mContext.getString(R.string.signup_error_username_taken);
                break;
        }

        return errorMessage;
    }

}
