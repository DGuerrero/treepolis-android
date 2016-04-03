package com.quoders.apps.android.treepolis;

import com.quoders.apps.android.treepolis.ui.signup.SignupPresenterImpl;

/**
 * Created by davidguerrerodiaz on 03/04/16.
 */
public class EmailValidator {

    String EMAIL_REGEX = SignupPresenterImpl.REGEX_EMAIL;

    public boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public void releaseResources() {
        //  We would release here any resources
    }
}
