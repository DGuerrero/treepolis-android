package com.quoders.apps.android.treepolis.signup;

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

    void showAlertDialog(String title, String message);
    void showProgressDialog(String message);
    void stopProgressDialog();
    void stopAlertDialog();
}
