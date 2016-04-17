package com.quoders.apps.android.treepolis;

/**
 * Created by davidguerrerodiaz on 21/03/16.
 */
public interface BasePresenter {

    void onViewAttached(BaseView view);

    void onViewDetached();
}
