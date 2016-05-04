package com.quoders.apps.android.treepolis.ui.checkin;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import com.quoders.apps.android.treepolis.BaseView;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.di.PerActivity;
import com.quoders.apps.android.treepolis.domain.checkin.CheckinTreeStorage;
import com.quoders.apps.android.treepolis.domain.checkin.TreePicturesInteractor;
import com.quoders.apps.android.treepolis.domain.checkin.TreePicturesInteractorImpl;
import com.quoders.apps.android.treepolis.domain.checkin.TreeUploaderInteractor;
import com.quoders.apps.android.treepolis.domain.checkin.TreeUploaderInteractorImpl;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;
import com.quoders.apps.android.treepolis.model.data.Tree;
import com.quoders.apps.android.treepolis.model.data.TreePhotos;
import com.quoders.apps.android.treepolis.ui.wikiSelection.WikiTreeSelectionActivity;

import java.util.Map;

import javax.inject.Inject;

import rx.Observer;

/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
@PerActivity
public class CheckinPresenterImpl implements CheckinPresenter {

    private static final String TAG = CheckinPresenterImpl.class.getSimpleName();

    private CheckinView mView;
    private TreePicturesInteractor mInteractor;
    private TreeUploaderInteractor mTreeUploader;

    private int mPhotoButtonId;
    private String mPictureFullPath;
    private WikiTreeLink mWikiTreeLink;


    @Inject
    public CheckinPresenterImpl(Application application, CheckinTreeStorage treeStorage) {
        mInteractor = new TreePicturesInteractorImpl(application, treeStorage);
        mTreeUploader = new TreeUploaderInteractorImpl(application);
    }

    @Override
    public void onViewAttached(BaseView view) {
        this.mView = (CheckinView)view;
        initView();
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
        mPictureFullPath = mInteractor.getFullPathForTreePicture(mPhotoButtonId);

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
    public void onFindTreeInWikipediaClicked() {
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
        if(resultCode == Activity.RESULT_OK && data != null) {
            mWikiTreeLink = (WikiTreeLink)data.getSerializableExtra(WikiTreeSelectionActivity.WIKI_TREE_DATA_RESULT);
            mView.displayTreeInfo(mWikiTreeLink);
        }
    }

    @Override
    public void onSubmitTreeClicked() {
        //  Check if we have at least pictures of the tree and leaf
        final Map<Integer, String> pics = mInteractor.getPendingUploadPictures();
        if(!pics.containsKey(R.id.circleButtonTreePhoto) || !pics.containsKey(R.id.circleButtonLeafPhoto)) {
            mView.displayErrorNeedToAddPictures();
            return;
        }

        //  Check we have valid name
        String treeName = mView.getTreeName();
        if(treeName.isEmpty()) {
            mView.displayErrorTreeNameEmpty();
            return;
        }

        //  Check location accuracy
        if(!mView.getLocationAccuracy()) {
            mView.displayErrorLocationNotAccurated();
            return;
        }

        mView.displayConfirmCheckinDialog();
    }

    @Override
    public void onSubmitTreeConfirmed() {

         Tree tree =  buildTreePayload();

        mTreeUploader.uploadTree(tree);
    }


    private Tree buildTreePayload() {
        Tree tree = new Tree();

        Location location = mView.getCurrentLocation();
        tree.setLatitude(location.getLatitude());
        tree.setLongitude(location.getLongitude());
        tree.setNameCommon(mView.getTreeName());


        mInteractor.buildTreePhotosPayload().subscribe(new Observer<TreePhotos>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TreePhotos treePhotos) {

            }
        });

        return tree;
    }
}
