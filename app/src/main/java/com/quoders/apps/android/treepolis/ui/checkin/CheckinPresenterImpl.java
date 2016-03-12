package com.quoders.apps.android.treepolis.ui.checkin;

import android.app.Application;

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
    public void setView(CheckinView view) {
        this.mView = view;
    }

    @Override
    public void onViewStarted() {
        //  We check if there is any tree pending to be upload. If so we load the pictures
        final Map<Integer, String> photos = mInteractor.getPendingUploadPictures();
        for (Integer key : photos.keySet()) {
            mView.setTreePictureThumbnail(key, photos.get(key));
        }
    }

    @Override
    public void onTakeTreePhotoClick(int viewId) {
        mPhotoButtonId = viewId;
        mPictureFullPath = mInteractor.buildTreePictureFileFullPath(viewId);

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

}
