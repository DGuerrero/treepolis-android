package com.quoders.apps.android.treepolis.ui.checkin;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
public interface CheckinView  {

    void displayErrorCapturingImageDialog();

    void takePicture(String fileName);

    void setTreePictureThumbnail(int viewId, String imagePath);
}
