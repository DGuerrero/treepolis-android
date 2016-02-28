package com.quoders.apps.android.treepolis.ui.checkin;

import android.app.Application;

import com.quoders.apps.android.treepolis.di.PerActivity;
import com.quoders.apps.android.treepolis.domain.events.TakeTreePictureMessage;
import com.quoders.apps.android.treepolis.utils.IQSharedPrefs;
import com.squareup.otto.Subscribe;

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


    @Inject
    public CheckinPresenterImpl(IQSharedPrefs prefs, Application application) {
        this.mPreferences = prefs;
        mInteractor = new TakeTreePictureinInteractorImpl(application);
    }


    @Override
    public void setView(CheckinView view) {
        this.mView = view;
    }

    @Override
    public void onTakeTreePhotoClick(int viewId) {
        mPhotoButtonId = viewId;
        String pictureFullPath = mInteractor.buildTreePictureFileFullPath(viewId);

        if(pictureFullPath != null) {
            mView.takePicture(pictureFullPath);
        } else {
            mView.displayErrorCapturingImageDialog();
        }
    }


    @Override
    public void onImageCaptureSuccess() {
        mView.displayErrorCapturingImageDialog();
    }

    @Override
    public void onImageCaptureError() {
        mView.displayErrorCapturingImageDialog();
    }

    @Subscribe
    public void onTakeTreePictureResult(TakeTreePictureMessage message){
        if(mView!=null){

        }
    }


}
