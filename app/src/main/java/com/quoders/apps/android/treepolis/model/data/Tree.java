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
}
