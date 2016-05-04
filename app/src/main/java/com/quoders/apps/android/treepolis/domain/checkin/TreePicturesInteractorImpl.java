package com.quoders.apps.android.treepolis.domain.checkin;

import android.content.Context;
import android.support.annotation.NonNull;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.ImageUtils;
import com.quoders.apps.android.treepolis.model.TreepolisConsts;
import com.quoders.apps.android.treepolis.model.data.TreePhotos;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public class TreePicturesInteractorImpl implements TreePicturesInteractor {

    private final Context mContext;
    private final CheckinTreeStorage mTreeStore;

    @Inject
    public TreePicturesInteractorImpl(@NonNull Context context, @NonNull CheckinTreeStorage treeStorage) {
        this.mContext = context;
        this.mTreeStore = treeStorage;
    }

    @Override
    public void savePictureTaken(int viewId, String fullPath) {
        mTreeStore.saveTreePictureTaken(viewId, fullPath);
    }

    @Override
    public Map<Integer, String> getPendingUploadPictures() {

        Map<Integer, String> pendingPhotos = new HashMap<>(3);

        String fruitPic = mTreeStore.getStoredFruitPicturePath();
        if(fruitPic != null && !fruitPic.isEmpty()) {
            pendingPhotos.put(R.id.circleButtonFruitPhoto, fruitPic);
        }

        String treePic = mTreeStore.getStoredTreePicturePath();
        if(treePic != null && !treePic.isEmpty()) {
            pendingPhotos.put(R.id.circleButtonTreePhoto, treePic);
        }

        String leafPic = mTreeStore.getStoredLeafPicturePath();
        if(leafPic != null && !leafPic.isEmpty()) {
            pendingPhotos.put(R.id.circleButtonLeafPhoto, leafPic);
        }

        return pendingPhotos;
    }

    @Override
    public Observable<TreePhotos> buildTreePhotosPayload() {

        return Observable.create(new Observable.OnSubscribe<TreePhotos>() {
            @Override
            public void call(Subscriber<? super TreePhotos> subscriber) {
                TreePhotos treePhotos = new TreePhotos();

                String treePic = mTreeStore.getStoredTreePicturePath();
                treePhotos.setPhotoTree(ImageUtils.bitmapFileToBase64(treePic, TreepolisConsts.TREE_IMAGE_QUALITY));

                String fruitPic = mTreeStore.getStoredLeafPicturePath();
                treePhotos.setPhotoFruit(ImageUtils.bitmapFileToBase64(fruitPic, TreepolisConsts.TREE_IMAGE_QUALITY));

                String leafPic = mTreeStore.getStoredLeafPicturePath();
                treePhotos.setPhotoFruit(ImageUtils.bitmapFileToBase64(leafPic, TreepolisConsts.TREE_IMAGE_QUALITY));

                subscriber.onNext(treePhotos);
                subscriber.onCompleted();
            }
        })
         .subscribeOn(Schedulers.newThread())
         .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public String getFullPathForTreePicture(int photoButtonId) {
        return mTreeStore.buildTreePictureFileFullPath(photoButtonId);
    }
}
