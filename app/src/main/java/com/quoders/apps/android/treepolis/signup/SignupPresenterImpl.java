package com.quoders.apps.android.treepolis.signup;

import android.content.Context;

import com.quoders.apps.android.treepolis.R;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupPresenterImpl implements SignupPresenter {

    private SignupView          mView;
    private SignupInteractor    mInteractor;
    private Context             mContext;


    public SignupPresenterImpl(SignupView view) {

        mView = view;
        mContext = (Context)view;
        mInteractor = new SignupInteractorImpl(mContext);
    }

    @Override
    public void onCreateAccountClick() {

        if(ValidateCreateAccountFields()) {

        }
    }

    private boolean ValidateCreateAccountFields() {

        //  Check passwords not equal
        if(!mView.getFieldPassword().equalsIgnoreCase(mView.getFieldPasswordConfirm())) {
            mView.setFieldPasswordConfirmError(R.string.field_error_password_diferent);
        }

        //  Check empty fields
        if(mView.getFieldEmail().isEmpty()) {
            mView.setFieldEmailError(R.string.field_error_empty);
        }

        if(mView.getFieldPassword().isEmpty()) {
            mView.setFieldPasswordError(R.string.field_error_empty);
        }

        if(mView.getFieldPasswordConfirm().isEmpty()) {
            mView.setFieldPasswordConfirmError(R.string.field_error_empty);
        }

        return false;
    }
}
