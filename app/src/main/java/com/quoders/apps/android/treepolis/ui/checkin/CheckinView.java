package com.quoders.apps.android.treepolis.ui.checkin;

import com.quoders.apps.android.treepolis.BaseView;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
public interface CheckinView extends BaseView {

    void displayErrorCapturingImageDialog();

    void takePicture(String fileName);

    void setTreePictureThumbnail(int viewId, String imagePath);

    void clearTreeInfoView();

    void navigateToWikipediaTreeInfoSelector();

    boolean doWeHaveWriteStoragePermission();

    void requestWriteStoragePermission();
}
