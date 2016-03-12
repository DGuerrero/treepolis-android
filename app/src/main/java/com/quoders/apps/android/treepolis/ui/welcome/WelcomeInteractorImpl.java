package com.quoders.apps.android.treepolis.ui.welcome;

import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.quoders.apps.android.treepolis.model.TreepolisConsts;

/**
 * Created by davidguerrerodiaz on 19/04/15.
 */
public class WelcomeInteractorImpl implements WelcomeInteractor {

    private Context                     mContext;
    private WelcomeInteractorListener   mListener;


    public WelcomeInteractorImpl(Context context, WelcomeInteractorListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }


    @Override
    public void loginWithFacebook() {

        //  TODO

    }


    @Override
    public void loginWithUserName(String username, String password) {

        Firebase ref = new Firebase(TreepolisConsts.FIREBASE_TREEPOLIS_URL);

        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                if(mListener != null) {
                    mListener.onLoginSuccessful();
                }
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                if(mListener != null) {
                    mListener.onLoginError(firebaseError);
                }
            }
        };

        ref.authWithPassword(username, password, authResultHandler);
   }


    public interface WelcomeInteractorListener {
        void onLoginSuccessful();
        void onLoginError(FirebaseError firebaseError);
    }
}
