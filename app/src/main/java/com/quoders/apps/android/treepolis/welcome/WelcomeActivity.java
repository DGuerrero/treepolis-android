package com.quoders.apps.android.treepolis.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.parse.ParseFacebookUtils;
import com.quoders.apps.android.treepolis.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends ActionBarActivity implements WelcomeView {

    WelcomePresenter mPresenter;


    @OnClick(R.id.textViewCreateAccount)
    public void createAccountClick(View view) {
        mPresenter.onCreateAccountClick();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.inject(this);

        mPresenter = new WelcomePresenterImpl(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);

        mPresenter.onActivityResultCalled(requestCode, resultCode);
    }

    @Override
    public void launchActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
    }

}
