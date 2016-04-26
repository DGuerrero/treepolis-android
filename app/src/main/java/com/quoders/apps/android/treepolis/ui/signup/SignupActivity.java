package com.quoders.apps.android.treepolis.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.quoders.apps.android.treepolis.BaseActivity;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.ui.dialogs.QAlertDialog;
import com.quoders.apps.android.treepolis.ui.dialogs.QProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements SignupView {

    public static final int REQUEST_ID_SIGNUP = 0x0001;

    @BindView(R.id.editTextCreateUserName)    EditText mEtCreateUserName;
    @BindView(R.id.editTextCreateEmail)       EditText mEtCreateEmail;
    @BindView(R.id.editTextCreatePassword)    EditText mEtCreatePassword;
    @BindView(R.id.editTextPasswordConfirm)   EditText mEtCreatePasswordConfirm;


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
        ButterKnife.bind(this);

        initToolbar();
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
    public void finishActivity(int resultCode) {
        setResult(resultCode);
        finish();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void launchActivity(Intent intent) {
        startActivity(intent);
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
