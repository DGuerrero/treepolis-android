package com.quoders.apps.android.treepolis.ui.signup;

import android.content.Intent;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public interface SignupView {

    String getFieldEmail();
    String getFieldPassword();
    String getFieldPasswordConfirm();
    String getFieldUserName();

    void setFieldEmailError(int errorMessage);
    void setFieldPasswordError(int errorMessage);
    void setFieldPasswordConfirmError(int errorMessage);

    void finishActivity(int resultCode);
    void finishActivity();
    void launchActivity(Intent intent);

    void showAlertDialog(String title, String message);
    void showProgressDialog(String message);
    void stopProgressDialog();
    void stopAlertDialog();
}
