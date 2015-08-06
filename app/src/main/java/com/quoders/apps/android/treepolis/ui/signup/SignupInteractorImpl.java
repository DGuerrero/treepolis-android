package com.quoders.apps.android.treepolis.ui.signup;

import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupInteractorImpl implements SignupInteractor {

    private SignupListener mListener;

    public SignupInteractorImpl(Context context, SignupListener listener) {

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
                        mListener.onSignupError(e.getCode());
                    }
                }
            }
        });
    }



    public interface SignupListener {

        void  onSignupSuccess();
        void onSignupError(int errorCode);

    }
}
