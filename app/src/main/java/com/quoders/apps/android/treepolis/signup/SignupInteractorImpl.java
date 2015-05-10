package com.quoders.apps.android.treepolis.signup;

import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.quoders.apps.android.treepolis.R;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupInteractorImpl implements SignupInteractor {

    private Context mContext;
    private SignupListener mListener;

    public SignupInteractorImpl(Context context, SignupListener listener) {

        this.mContext = context;
        this.mListener = listener;
    }


    @Override
    public void SignUpUser(String userName, String email, String password) {

        ParseUser user = new ParseUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);

        user.signUpInBackground(new SignUpCallback() {

            public void done(ParseException e) {

                if (e == null && mListener != null) {
                    mListener.onSignupSuccess();
                } else {

                    if(mListener != null) {
                        mListener.onSignupError(getSignUpErrorMessage(e.getCode()));
                    }
                }
            }
        });
    }


    private String getSignUpErrorMessage(int code) {

        String errorMessage = mContext.getString(R.string.signup_error_unknown);

        switch (code) {
            case ParseException.EMAIL_TAKEN:
                errorMessage = mContext.getString(R.string.signup_error_email_taken);
                break;
            case ParseException.INVALID_EMAIL_ADDRESS:
                errorMessage = mContext.getString(R.string.signup_error_email_invalid);
                break;
            case ParseException.USERNAME_TAKEN:
                errorMessage = mContext.getString(R.string.signup_error_username_taken);
                break;
        }

        return errorMessage;
    }



    public interface SignupListener {

        void  onSignupSuccess();
        void onSignupError(String errorCode);

    }
}
