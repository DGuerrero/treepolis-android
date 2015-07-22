package com.quoders.apps.android.treepolis.ui.maps;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by davidguerrero on 10/07/15.
 */
public class LocationMng implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private LocationManagerCallback mCallback;

    LocationRequest mLocationRequest;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mCurrentLocation;
    private boolean mRequestingLocationUpdates;



    public LocationMng(Context context, boolean requestUpdates,  LocationManagerCallback callback) {

        this.mCallback = callback;
        this.mRequestingLocationUpdates = requestUpdates;

        buildGoogleApiClient(context);
    }

    public void startLocationService() {
        mGoogleApiClient.connect();
    }

    public void stopLocationService() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Builds a GoogleApiClient. Uses the addApi() method to request the LocationServices API.
     */
    protected synchronized void buildGoogleApiClient(Context context) {

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnected(Bundle bundle) {

        //  Get the last location
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mCurrentLocation != null) {
            mCallback.onLocationUpdate(mCurrentLocation);
        }

        //  Request location updates
        if (mRequestingLocationUpdates) {
            createLocationRequest();
            startLocationUpdates();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(mCallback != null) {
            mCallback.onLocationFail();
        }
    }

    public Location getLastLocation() {
        return mCurrentLocation;
    }

    @Override
    public void onLocationChanged(Location location) {

        mCurrentLocation = location;

        //  Report the change to listeners
        if(mCallback != null) {
            mCallback.onLocationUpdate(location);
        }
    }

    public void stopLocationUpdates() {
        mRequestingLocationUpdates = false;
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    protected void startLocationUpdates() {
        mRequestingLocationUpdates = true;
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    public interface LocationManagerCallback {
        void onLocationUpdate(Location location);
        void onLocationFail();
    }
}
