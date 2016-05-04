package com.quoders.apps.android.treepolis.domain.checkin;

import android.content.Context;

import com.quoders.apps.android.treepolis.model.data.Tree;

import rx.Single;

/**
 * Created by davidguerrerodiaz on 02/05/16.
 */
public class TreeUploaderInteractorImpl implements TreeUploaderInteractor {

    Context mAppContext;

    public TreeUploaderInteractorImpl(Context appContext) {
        this.mAppContext = appContext;
    }


    @Override
    public Single uploadTree(Tree tree) {


    }
}
