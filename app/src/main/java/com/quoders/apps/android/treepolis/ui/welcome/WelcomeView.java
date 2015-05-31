package com.quoders.apps.android.treepolis.ui.welcome;

import android.content.Intent;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public interface WelcomeView {

    void launchActivityForResult(Intent intent, int requestCode);
    void launchActivity(Intent intent);
    void closeActivity();

    String getUsernameFieldText();
    String getPasswordFieldText();
    void setFieldUsernameError(int errorMessage);
    void setFieldPasswordError(int errorMessage);

    void showAlertDialog(int title, int message);
    void showProgressDialog(int message);
    void stopProgressDialog();
    void stopAlertDialog();
}
