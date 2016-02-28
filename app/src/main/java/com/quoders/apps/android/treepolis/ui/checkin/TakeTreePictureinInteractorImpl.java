package com.quoders.apps.android.treepolis.ui.checkin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.domain.eventbus.BusProvider;
import com.quoders.apps.android.treepolis.domain.events.TakeTreePictureMessage;
import com.quoders.apps.android.treepolis.model.ImageUtils;
import com.quoders.apps.android.treepolis.model.checkin.CheckinConsts;

import java.io.File;
import java.io.IOException;


/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public class TakeTreePictureinInteractorImpl implements TakeTreePictureinInteractor {

    private Context mContext;

    public TakeTreePictureinInteractorImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void takeTreePicture(String fileName) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = ImageUtils.buildImageFilePath(fileName);
            } catch (IOException ex) {
                 BusProvider.getInstance().post(new TakeTreePictureMessage(TakeTreePictureMessage.ERROR_SAVING_PICTURE));
                return;
            }

            if (photoFile != null) {
                //mImagePath = photoFile.getPath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
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
