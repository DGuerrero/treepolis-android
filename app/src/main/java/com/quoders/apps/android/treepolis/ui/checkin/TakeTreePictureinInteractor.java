package com.quoders.apps.android.treepolis.ui.checkin;

/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public interface TakeTreePictureinInteractor {

    void takeTreePicture(String filename);

    String buildTreePictureFileFullPath(int viewId);
}
