package com.quoders.apps.android.treepolis.ui.checkin;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
public class CheckinPresenterImpl implements CheckinPresenter {

    private CheckinView mView;
    private int mPhotoButtonId;

    public CheckinPresenterImpl(CheckinView checkinView) {
        this.mView = checkinView;
    }

    @Override
    public void onTakeTreePhotoClick(int viewId) {
    }


}
