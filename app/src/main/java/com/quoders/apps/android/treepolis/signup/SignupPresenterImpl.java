package com.quoders.apps.android.treepolis.signup;

import android.content.Context;

import com.quoders.apps.android.treepolis.R;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupPresenterImpl implements SignupPresenter, SignupInteractorImpl.SignupListener {

    private static final String EMAIL_REGEX = "[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}";

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
        if(!mView.getFieldEmail().matches(EMAIL_REGEX)) {
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
        mView.finishActivity();
    }

    @Override
    public void onSignupError(String errorCode) {
        mView.stopProgressDialog();
        mView.showAlertDialog(mContext.getString(R.string.signup_error), errorCode);
    }
}
