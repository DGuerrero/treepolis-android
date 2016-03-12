package com.quoders.apps.android.treepolis.ui.signup;

import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.quoders.apps.android.treepolis.model.TreepolisConsts;

import java.util.Map;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class SignupInteractorImpl implements SignupInteractor {

    private SignupListener mListener;

    public SignupInteractorImpl(Context context, SignupListener listener) {

        this.mListener = listener;
    }


    @Override
    public void SignUpUser(final String userName, final String email, final String password) {

        Firebase ref = new Firebase(TreepolisConsts.FIREBASE_TREEPOLIS_URL);
        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                performLogin(email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                if(mListener != null) {
                    mListener.onSignupError(firebaseError.getCode());
                }
            }
        });
    }

    private void performLogin(String email, String password) {
        Firebase ref = new Firebase(TreepolisConsts.FIREBASE_TREEPOLIS_URL);

        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                if(mListener != null) {
                    mListener.onSignupSuccess();
                }
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                if(mListener != null) {
                    mListener.onLoginErrorAfterSignup();
                }
            }
        };

        ref.authWithPassword(email, password, authResultHandler);
    }


    public interface SignupListener {
        void onSignupSuccess();
        void onSignupError(int errorCode);
        void onLoginErrorAfterSignup();
    }
}
