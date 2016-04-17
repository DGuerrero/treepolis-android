package com.quoders.apps.android.treepolis.model.data;

import android.net.Uri;

/**
 * Created by davidguerrerodiaz on 29/02/16.
 */
public class Tree {

    private int treeId;
    private Uri wikiLink;
    private String mNameCommon;
    private String mNameScientific;
    private User mUserAuthor;
    private TreePhoto mPhotos;
    private double mLatitude;
    private double mLongitude;
    private boolean mVerified;
    private User[] mVerifiers;

    public int getTreeId() {
        return treeId;
    }

    public void setTreeId(int treeId) {
        this.treeId = treeId;
    }

    public Uri getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(Uri wikiLink) {
        this.wikiLink = wikiLink;
    }

    public String getNameCommon() {
        return mNameCommon;
    }

    public void setNameCommon(String mNameCommon) {
        this.mNameCommon = mNameCommon;
    }

    public String getNameScientific() {
        return mNameScientific;
    }

    public void setNameScientific(String mNameScientific) {
        this.mNameScientific = mNameScientific;
    }

    public User getUserAuthor() {
        return mUserAuthor;
    }

    public void setUserAuthor(User mUserAuthor) {
        this.mUserAuthor = mUserAuthor;
    }

    public TreePhoto getPhotos() {
        return mPhotos;
    }

    public void setPhotos(TreePhoto mPhotos) {
        this.mPhotos = mPhotos;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public boolean ismVerified() {
        return mVerified;
    }

    public void setVerified(boolean mVerified) {
        this.mVerified = mVerified;
    }

    public User[] getVerifiers() {
        return mVerifiers;
    }

    public void setVerifiers(User[] mVerifiers) {
        this.mVerifiers = mVerifiers;
    }
}
