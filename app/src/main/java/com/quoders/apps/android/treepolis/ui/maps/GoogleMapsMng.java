package com.quoders.apps.android.treepolis.ui.maps;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by davidguerrero on 07/07/15.
 */
public class GoogleMapsMng implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, LocationMng.LocationManagerCallback {

    private static final int MAP_ZOOM_LEVEL_NORMAL = 14;

    private Context mContext;
    private boolean mFirstLocationUpdated = false;


    /**
     * The map. It is initialized when the map has been fully loaded and is ready to be used.
     *
     * @see #onMapReady(com.google.android.gms.maps.GoogleMap)
     */
    private GoogleMap mMap;


    public GoogleMapsMng(Context context) {
        this.mContext = context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Map is ready to be used.
        mMap = googleMap;

        //  Enable user location layer
        mMap.setMyLocationEnabled(true);

        // Set the long click listener as a way to exit the map.
        mMap.setOnMapLongClickListener(this);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onLocationUpdate(Location location) {

        if(!mFirstLocationUpdated) {
            mFirstLocationUpdated = true;
            
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            // Add a marker with a title that is shown in its info window.
            mMap.addMarker(new MarkerOptions().position(latLng));

            // Move the camera to show the marker.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAP_ZOOM_LEVEL_NORMAL));
        }
    }

    @Override
    public void onLocationFail() {

    }
}
