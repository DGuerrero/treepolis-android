package com.quoders.apps.android.treepolis.signup;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public interface SignupView {

    String getFieldEmail();
    String getFieldPassword();
    String getFieldPasswordConfirm();

    void setFieldEmailError(int errorMessage);
    void setFieldPasswordError(int errorMessage);
    void setFieldPasswordConfirmError(int errorMessage);
}
