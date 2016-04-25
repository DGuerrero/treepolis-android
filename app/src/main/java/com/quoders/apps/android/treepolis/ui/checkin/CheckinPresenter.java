package com.quoders.apps.android.treepolis.ui.checkin;

import android.content.Intent;

import com.quoders.apps.android.treepolis.BasePresenter;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
public interface CheckinPresenter extends BasePresenter {

    void onTakeTreePhotoClick(int viewId);

    void onImageCaptureSuccess();

    void onImageCaptureError();

    void onNotKnownTreeClicked();

    void onFindTreeInWikipediaClicked();

    void onRequestPermissionsWriteStorageGranted();

    void onRequestPermissionsWriteStorageDenied();

    void processImageCaptureResult(int resultCode);

    void processWikiTreeSelection(int resultCode, Intent data);
}
