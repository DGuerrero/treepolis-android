package com.quoders.apps.android.treepolis.ui.home;

import android.content.Context;
import android.content.Intent;

import com.quoders.apps.android.treepolis.ui.checkin.CheckinTreeActivity;

/**
 * Created by davidguerrero on 11/08/15.
 */
public class HomePresenterImpl implements HomePresenter {

    Context mContext;

    public HomePresenterImpl(Context context) {
        mContext = context;
    }

    @Override
    public void onCheckTree() {

        mContext.startActivity(new Intent(mContext, CheckinTreeActivity.class));
    }
}
