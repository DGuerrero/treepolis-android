package com.quoders.apps.android.treepolis.domain.events;

/**
 * Created by davidguerrerodiaz on 09/02/16.
 */
public class TakeTreePictureMessage {

    public static final short ERROR_SAVING_PICTURE = 0x0001;
    public static final short ERROR_CAMERA_UNAVAILABLE = 0x0002;

    private int message;

    public TakeTreePictureMessage(int message) {
        this.message = message;
    }
}
