package com.quoders.apps.android.treepolis.domain.checkin;

import com.quoders.apps.android.treepolis.model.data.Tree;

import rx.Single;

/**
 * Created by davidguerrerodiaz on 02/05/16.
 */
public interface TreeUploaderInteractor {
    Single uploadTree(Tree tree);
}
