package com.quoders.apps.android.treepolis.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by davidguerrero on 10/04/15.
 */
public class QAlertDialog {

    AlertDialog             mDialog = null;
    Context                 mContext = null;


    public QAlertDialog(Context context) {
        mContext = context;
    }


    public void showDialogNeutral(int title, int message, int neutralButtonText,
                                  DialogInterface.OnClickListener onClickListener) {

        showDialogNeutral(mContext.getString(title), mContext.getString(message),
                mContext.getString(neutralButtonText), onClickListener);
    }

    public void showDialogNeutral(String title, String message, String neutralButtonText,
                                  DialogInterface.OnClickListener onClickListener) {

        dismissDialog();

        mDialog = new AlertDialog.Builder(mContext).setTitle(title)
            .setMessage(message)
            .setNeutralButton(neutralButtonText, onClickListener)
                .show();
    }


    public void showDialogConfirm(String title, String message,
                                  String buttonOkText, String buttonCancelText,
                                  DialogInterface.OnClickListener onClickOk,
                                  DialogInterface.OnClickListener onClickCancel) {

        dismissDialog();

        mDialog = new AlertDialog.Builder(mContext).setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonOkText, onClickOk)
                .setNegativeButton(buttonCancelText, onClickCancel)
                .show();
    }


    public void dismissDialog() {

        if(mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }


}
