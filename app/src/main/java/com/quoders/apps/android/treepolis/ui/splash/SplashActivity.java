package com.quoders.apps.android.treepolis.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.TreepolisConsts;
import com.quoders.apps.android.treepolis.ui.home.HomeActivity;
import com.quoders.apps.android.treepolis.ui.welcome.WelcomeActivity;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkUserSession();
    }

    private void checkUserSession() {

        Firebase ref = new Firebase(TreepolisConsts.FIREBASE_TREEPOLIS_URL);
        AuthData authData = ref.getAuth();
        if (authData != null) {
            startActivity(new Intent(this, HomeActivity.class));
        } else {
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
