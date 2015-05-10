package com.quoders.apps.android.treepolis.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseFacebookUtils;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.dialogs.QAlertDialog;
import com.quoders.apps.android.treepolis.dialogs.QProgressDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SignupActivity extends ActionBarActivity implements SignupView {

    @InjectView(R.id.editTextCreateUserName)    EditText mEtCreateUserName;
    @InjectView(R.id.editTextCreateEmail)       EditText mEtCreateEmail;
    @InjectView(R.id.editTextCreatePassword)    EditText mEtCreatePassword;
    @InjectView(R.id.editTextPasswordConfirm)   EditText mEtCreatePasswordConfirm;


    @OnClick(R.id.buttonSignUp)
    public void createAccountClick(View view) {
        mPresenter.onCreateAccountClick();
    }


    SignupPresenter mPresenter;
    QProgressDialog mProgressDialog;
    QAlertDialog mAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        mPresenter = new SignupPresenterImpl(this);
        mProgressDialog = new QProgressDialog(this);
        mAlertDialog = new QAlertDialog(this);
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
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getFieldUserName() {
        return mEtCreateUserName.getText().toString();
    }

    @Override
    public String getFieldEmail() {
        return mEtCreateEmail.getText().toString();
    }

    @Override
    public String getFieldPassword() {
        return mEtCreatePassword.getText().toString();
    }

    @Override
    public String getFieldPasswordConfirm() {
        return mEtCreatePasswordConfirm.getText().toString();
    }

    @Override
    public void setFieldEmailError(int errorMessage) {
        mEtCreateEmail.setError(getString(errorMessage));
    }

    @Override
    public void setFieldPasswordError(int errorMessage) {
        mEtCreatePassword.setError(getString(errorMessage));
    }

    @Override
    public void setFieldPasswordConfirmError(int errorMessage) {
        mEtCreatePasswordConfirm.setError(getString(errorMessage));
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showAlertDialog(String title, String message) {
        if(mAlertDialog != null) {
            mAlertDialog.showDialogNeutral(title, message, getString(R.string.dialog_button_ok), null);
        }
    }

    @Override
    public void showProgressDialog(String message) {
        if(mProgressDialog != null) {
            mProgressDialog.show(message, false);
        }
    }

    @Override
    public void stopProgressDialog() {
        if(mProgressDialog != null) {
            mProgressDialog.stop();
        }
    }

    @Override
    public void stopAlertDialog() {
        if(mAlertDialog != null) {
            mAlertDialog.dismissDialog();
        }
    }


}
