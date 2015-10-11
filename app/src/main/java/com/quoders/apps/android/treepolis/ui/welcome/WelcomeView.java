package com.quoders.apps.android.treepolis.ui.welcome;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public interface WelcomeView {

    void launchHomeActivity();
    void LaunchSignupActivity();
    void closeActivity();

    void stopProgressDialog();
    void showLoginErrorAlertDialog();
    void showProgressDialogLogin();

    void setUsernameFieldEmptyError();
    void setPasswordFieldEmptyError();

    String getUsernameFieldText();
    String getPasswordFieldText();
}
