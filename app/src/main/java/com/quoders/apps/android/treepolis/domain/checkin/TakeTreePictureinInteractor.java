package com.quoders.apps.android.treepolis.domain.checkin;

import java.util.Map;

/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public interface TakeTreePictureinInteractor {

    String buildTreePictureFileFullPath(int viewId);

    void savePictureTaken(int viewId, String path);

    Map<Integer,String> getPendingUploadPictures();
}
