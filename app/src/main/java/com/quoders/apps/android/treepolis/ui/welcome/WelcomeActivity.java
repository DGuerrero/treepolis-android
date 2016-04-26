package com.quoders.apps.android.treepolis.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.ui.dialogs.QAlertDialog;
import com.quoders.apps.android.treepolis.ui.dialogs.QProgressDialog;
import com.quoders.apps.android.treepolis.ui.home.HomeActivity;
import com.quoders.apps.android.treepolis.ui.signup.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity implements WelcomeView {

    @BindView(R.id.editTextUserNameLogin)     EditText mEtUserName;
    @BindView(R.id.editTextPasswordLogin)     EditText mEtUserPassword;

    @OnClick(R.id.loginButton)
    public void loginButtonClick(View view) {
        mPresenter.onLoginClick();
    }

    @OnClick(R.id.textViewCreateAccount)
    public void createAccountClick(View view) {
        mPresenter.onCreateAccountClick();
    }


    ActionBar mActionBar;
    WelcomePresenter mPresenter;
    QProgressDialog mProgressDialog;
    QAlertDialog mAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);

        mPresenter = new WelcomePresenterImpl(this);

        mProgressDialog = new QProgressDialog(this);
        mAlertDialog = new QAlertDialog(this);

        initToolbar();
    }


    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
    }


    @Override
    protected void onStop() {
        super.onStop();

        if(mProgressDialog != null) {
            mProgressDialog.stop();
        }

        if(mAlertDialog != null) {
            mAlertDialog.dismissDialog();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.onActivityResultCalled(requestCode, resultCode);
    }

    @Override
    public void launchHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void LaunchSignupActivity() {
        startActivity(new Intent(this, SignupActivity.class));
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public String getUsernameFieldText() {
        return mEtUserName.getText().toString();
    }

    @Override
    public String getPasswordFieldText() {
        return mEtUserPassword.getText().toString();
    }

    @Override
    public void showProgressDialogLogin() {
        if(mProgressDialog != null) {
            mProgressDialog.show(getString(R.string.login_progress), false);
        }
    }

    @Override
    public void stopProgressDialog() {
        if(mProgressDialog != null) {
            mProgressDialog.stop();
        }
    }

    @Override
    public void showLoginErrorAlertDialog() {
        if(mAlertDialog != null) {
            mAlertDialog.showDialogNeutral(getString(R.string.login_error_title),
                    getString(R.string.login_error_user_not_found),
                    getString(R.string.dialog_button_ok), null);
        }
    }

    @Override
    public void setUsernameFieldEmptyError() {
        mEtUserName.setError(getString(R.string.field_error_empty));
    }

    @Override
    public void setPasswordFieldEmptyError() {
        mEtUserPassword.setError(getString(R.string.field_error_empty));
    }
}
