package com.quoders.apps.android.treepolis.ui.mapsmng;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by davidguerrero on 07/07/15.
 */
public class GoogleMapsMng implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private static final LatLng SYDNEY = new LatLng(-33.85704, 151.21522);
    private static final int MAP_ZOOM_LEVEL_NORMAL = 14;

    private Context mContext;

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

        // Add a marker with a title that is shown in its info window.
        mMap.addMarker(new MarkerOptions().position(SYDNEY).title("Sydney Opera House"));

        // Move the camera to show the marker.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, MAP_ZOOM_LEVEL_NORMAL));
        //mMap.moveCamera(CameraUpdateFactory.zoomTo(MAP_ZOOM_LEVEL_NORMAL));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}
