package com.quoders.apps.android.treepolis.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by davidguerrerodiaz on 29/02/16.
 */
public class PermissionsHelper {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0x000001;

    public static boolean doWeHaveLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;
    }

    public static void requestLocationPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    public static boolean hasLocationPermissionBeenGranted(int requestCode, String permissions[], int[] grantResults) {
        boolean granted = false;

        if(requestCode == PermissionsHelper.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                granted = true;
            }
        }

        return granted;
    }
}
