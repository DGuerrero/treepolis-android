package com.quoders.apps.android.treepolis.ui.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by davidguerrero on 05/12/14.
 */
public class QProgressDialog {

    private ProgressDialog mDialog;
    private Context mContext;

    public QProgressDialog(Context context) {
        mContext = context;
    }

    public void show(String title, String message, boolean cancelable) {

        if(mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(cancelable);
            mDialog.setTitle(title);
            mDialog.setMessage(message);
            mDialog.show();
        }
    }

    public void show(String message, boolean cancelable) {

        if(mDialog == null) {
            mDialog = new ProgressDialog(mContext);
            mDialog.setCancelable(cancelable);
            mDialog.setMessage(message);
            mDialog.show();
        }
    }

    public void stop() {
        if(mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
