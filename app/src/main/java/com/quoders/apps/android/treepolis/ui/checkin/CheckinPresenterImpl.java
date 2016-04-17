package com.quoders.apps.android.treepolis.ui.checkin;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.quoders.apps.android.treepolis.BaseView;
import com.quoders.apps.android.treepolis.di.PerActivity;
import com.quoders.apps.android.treepolis.domain.checkin.TakeTreePictureinInteractor;
import com.quoders.apps.android.treepolis.domain.checkin.TakeTreePictureinInteractorImpl;
import com.quoders.apps.android.treepolis.utils.IQSharedPrefs;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
@PerActivity
public class CheckinPresenterImpl implements CheckinPresenter {

    private CheckinView mView;
    private TakeTreePictureinInteractor mInteractor;

    private final IQSharedPrefs mPreferences;
    private int mPhotoButtonId;
    private String mPictureFullPath;


    @Inject
    public CheckinPresenterImpl(IQSharedPrefs prefs, Application application) {
        this.mPreferences = prefs;
        mInteractor = new TakeTreePictureinInteractorImpl(application, prefs);
    }

    @Override
    public void onViewAttached(BaseView view) {
        this.mView = (CheckinView)view;
        this.initView();
    }

    private void initView() {
        final Map<Integer, String> photos = mInteractor.getPendingUploadPictures();
        for (Integer key : photos.keySet()) {
            mView.setTreePictureThumbnail(key, photos.get(key));
        }
    }

    @Override
    public void onViewDetached() {
    }

    @Override
    public void onTakeTreePhotoClick(int viewId) {
        mPhotoButtonId = viewId;

        if(mView.doWeHaveWriteStoragePermission()) {
            this.takeTreePicture();
        } else {
            mView.requestWriteStoragePermission();
        }
    }

    private void takeTreePicture() {
        mPictureFullPath = mInteractor.buildTreePictureFileFullPath(mPhotoButtonId);

        if(mPictureFullPath != null) {
            mView.takePicture(mPictureFullPath);
        } else {
            mView.displayErrorCapturingImageDialog();
        }
    }

    @Override
    public void onImageCaptureSuccess() {
        mView.setTreePictureThumbnail(mPhotoButtonId, mPictureFullPath);
        mInteractor.savePictureTaken(mPhotoButtonId, mPictureFullPath);
    }

    @Override
    public void onImageCaptureError() {
        mView.displayErrorCapturingImageDialog();
    }

    @Override
    public void onNotKnownTreeClicked() {
        mView.clearTreeInfoView();
    }

    @Override
    public void onKnownTreeClicked() {
        mView.navigateToWikipediaTreeInfoSelector();
    }

    @Override
    public void onRequestPermissionsWriteStorageGranted() {
        this.takeTreePicture();
    }

    @Override
    public void onRequestPermissionsWriteStorageDenied() {
        mView.displayErrorCapturingImageDialog();
    }

    @Override
    public void processImageCaptureResult(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            onImageCaptureSuccess();
        } else {
            onImageCaptureError();
        }
    }

    @Override
    public void processWikiTreeSelection(int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
        } else {
        }
    }
}
