package com.quoders.apps.android.treepolis.domain.checkin;

import android.support.annotation.NonNull;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.ImageUtils;
import com.quoders.apps.android.treepolis.model.checkin.CheckinConsts;
import com.quoders.apps.android.treepolis.utils.IQSharedPrefs;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by davidguerrerodiaz on 02/05/16.
 */
public class CheckinTreeStorage {

    private final IQSharedPrefs mSharedPrefs;

    @Inject
    public CheckinTreeStorage(@NonNull IQSharedPrefs sharedPrefs) {
        this.mSharedPrefs = sharedPrefs;
    }

    public String getStoredTreePicturePath() {
        return mSharedPrefs.getString(mapPictureNameFromId(R.id.circleButtonTreePhoto));
    }

    public String getStoredLeafPicturePath() {
        return mSharedPrefs.getString(mapPictureNameFromId(R.id.circleButtonLeafPhoto));
    }

    public String getStoredFruitPicturePath() {
        return mSharedPrefs.getString(mapPictureNameFromId(R.id.circleButtonFruitPhoto));
    }

    public void saveTreePictureTaken(int viewId, String fullPath) {
        mSharedPrefs.setString(mapPictureNameFromId(viewId), fullPath);
    }

    public static String mapPictureNameFromId(int viewId) {
        switch (viewId) {
            case R.id.circleButtonTreePhoto:
                return CheckinConsts.CHEKIN_TREE_PICTURE_FILE_NAME;
            case R.id.circleButtonFruitPhoto:
                return CheckinConsts.CHEKIN_FRUIT_PICTURE_FILE_NAME;
            case R.id.circleButtonLeafPhoto:
                return CheckinConsts.CHEKIN_LEAF_PICTURE_FILE_NAME;
        }
        return CheckinConsts.CHEKIN_UNKNOWN_PICTURE_FILE_NAME;
    }

    public String buildTreePictureFileFullPath(int viewId) {

        String imagePath = "";

        try {
            String picName =  mapPictureNameFromId(viewId);
            if(picName != null) {
                File photoFile = ImageUtils.buildImageFilePath(picName);
                if (photoFile != null) {
                    imagePath = photoFile.getPath();
                }
            }
        } catch (IOException ex) {
            return null;
        }

        return imagePath;
    }


}
