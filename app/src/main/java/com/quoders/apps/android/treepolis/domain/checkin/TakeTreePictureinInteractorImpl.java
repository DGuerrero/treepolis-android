package com.quoders.apps.android.treepolis.domain.checkin;

import android.content.Context;
import android.support.annotation.NonNull;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.ImageUtils;
import com.quoders.apps.android.treepolis.model.checkin.CheckinConsts;
import com.quoders.apps.android.treepolis.utils.IQSharedPrefs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public class TakeTreePictureinInteractorImpl implements TakeTreePictureinInteractor {

    private final Context mContext;
    private final IQSharedPrefs mSharedPrefs;

    public TakeTreePictureinInteractorImpl(@NonNull Context context, @NonNull IQSharedPrefs sharedPrefs) {
        this.mContext = context;
        this.mSharedPrefs = sharedPrefs;
    }


    @Override
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

    @Override
    public void savePictureTaken(int viewId, String path) {
        mSharedPrefs.setString(String.valueOf(viewId), path);
    }

    @Override
    public Map<Integer, String> getPendingUploadPictures() {

        Map<Integer, String> pendingPhotos = new HashMap<>(3);

        String fruitPic = mSharedPrefs.getString(mapPictureNameFromId(R.id.circleButtonFruitPhoto));
        if(fruitPic != null && !fruitPic.isEmpty()) {
            pendingPhotos.put(R.id.circleButtonFruitPhoto, fruitPic);
        }

        String treePic = mSharedPrefs.getString(mapPictureNameFromId(R.id.circleButtonTreePhoto));
        if(treePic != null && !treePic.isEmpty()) {
            pendingPhotos.put(R.id.circleButtonTreePhoto, treePic);
        }

        String leafPic = mSharedPrefs.getString(mapPictureNameFromId(R.id.circleButtonLeafPhoto));
        if(leafPic != null && !leafPic.isEmpty()) {
            pendingPhotos.put(R.id.circleButtonLeafPhoto, leafPic);
        }

        return pendingPhotos;
    }

    private String mapPictureNameFromId(int viewId) {
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


}
