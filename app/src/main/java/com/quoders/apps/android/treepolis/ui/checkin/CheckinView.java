package com.quoders.apps.android.treepolis.ui.checkin;

import android.location.Location;

import com.quoders.apps.android.treepolis.BaseView;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;

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

    void displayErrorTakingTreeInfo();

    void displayTreeInfo(WikiTreeLink wikiTreeLink);

    void displayErrorNeedToAddPictures();

    String getTreeName();

    void displayErrorTreeNameEmpty();

    void displayErrorLocationNotAccurated();

    boolean getLocationAccuracy();

    Location getCurrentLocation();

    void displayConfirmCheckinDialog();
}
