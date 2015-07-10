package com.quoders.apps.android.treepolis.ui.maps;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by davidguerrero on 10/07/15.
 */
public class LocationMng implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private Context mContext;
    private LocationManagerCallback mCallback;

    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;


    public LocationMng(Context context, LocationManagerCallback callback) {

        this.mContext = context;
        this.mCallback = callback;

        buildGoogleApiClient();
    }


    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onConnected(Bundle bundle) {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            mCallback.onLocationUpdate(mLastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(mCallback != null) {
            mCallback.onLocationFail();
        }
    }

    public Location getLastLocation() {
        return mLastLocation;
    }


    public interface LocationManagerCallback {
        void onLocationUpdate(Location location);
        void onLocationFail();
    }
}
