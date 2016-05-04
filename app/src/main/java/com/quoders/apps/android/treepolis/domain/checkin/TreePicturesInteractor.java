package com.quoders.apps.android.treepolis.domain.checkin;

import com.quoders.apps.android.treepolis.model.data.TreePhotos;

import java.util.Map;

import rx.Observable;

/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public interface TreePicturesInteractor {

    void savePictureTaken(int viewId, String fullPath);

    Map<Integer,String> getPendingUploadPictures();

    Observable<TreePhotos> buildTreePhotosPayload();

    String getFullPathForTreePicture(int mPhotoButtonId);
}
