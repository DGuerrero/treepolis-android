package com.quoders.apps.android.treepolis;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.quoders.apps.android.treepolis.di.NetComponent;
import com.quoders.apps.android.treepolis.ui.dialogs.QAlertDialog;

/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected QAlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

      /**
       * Adds a {@link Fragment} to this activity's layout.
       *
       * @param containerViewId The container view to where add the fragment.
       * @param fragment The fragment to be added.
       */
      protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
      }

      /**
       * Get the Main Application component for dependency injection.
       */
      protected NetComponent getNetComponent() {
        return ((TreepolisApplication)getApplication()).getNetComponent();
      }

    protected void displayAlertDialog(String title, String message) {
        dismissAlertDialog();
        mAlertDialog = new QAlertDialog(this);
        mAlertDialog.showDialogNeutral(title, message, getString(R.string.dialog_button_ok), null);
    }

    protected void displayAlertDialogConfirm(String title, String message, DialogInterface.OnClickListener onClickListener) {
        dismissAlertDialog();
        mAlertDialog = new QAlertDialog(this);
        mAlertDialog.showDialogConfirm(title, message, getString(R.string.dialog_button_ok),
                getString(R.string.dialog_button_cancel), onClickListener, null);
    }

    protected void dismissAlertDialog() {
        if (mAlertDialog != null) {
            mAlertDialog.dismissDialog();
            mAlertDialog = null;
        }
    }

    protected void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}
