package com.quoders.apps.android.treepolis.model.data;

import android.support.annotation.NonNull;

/**
 * Created by davidguerrerodiaz on 29/02/16.
 */
public class TreePhotos {
    private String mPhotoTree;
    private String mPhotoLeaf;
    private String mPhotoFruit;

    public TreePhotos(@NonNull String photoTree, @NonNull String photoLeaf, @NonNull String photoFruit) {
        this.mPhotoTree = photoTree;
        this.mPhotoLeaf = photoLeaf;
        this.mPhotoFruit = photoFruit;
    }

    public TreePhotos() {
        this.mPhotoTree = "";
        this.mPhotoLeaf = "";
        this.mPhotoFruit = "";
    }

    public String getPhotoTree() {
        return mPhotoTree;
    }

    public void setPhotoTree(String mPhotoTree) {
        this.mPhotoTree = mPhotoTree;
    }

    public String getPhotoLeaf() {
        return mPhotoLeaf;
    }

    public void setPhotoLeaf(String mPhotoLeaf) {
        this.mPhotoLeaf = mPhotoLeaf;
    }

    public String getPhotoFruit() {
        return mPhotoFruit;
    }

    public void setPhotoFruit(String mPhotoFruit) {
        this.mPhotoFruit = mPhotoFruit;
    }
}
