package com.quoders.apps.android.treepolis.ui.checkin;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
public interface CheckinPresenter  {

    void setView(CheckinView view);

    void onTakeTreePhotoClick(int viewId);

    void onImageCaptureSuccess();

    void onImageCaptureError();
}
