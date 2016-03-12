package com.quoders.apps.android.treepolis.ui.checkin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.SupportMapFragment;
import com.quoders.apps.android.treepolis.BaseActivity;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.TreepolisApplication;
import com.quoders.apps.android.treepolis.helpers.PermissionsHelper;
import com.quoders.apps.android.treepolis.model.ImageUtils;
import com.quoders.apps.android.treepolis.ui.maps.GoogleMapsMng;
import com.quoders.apps.android.treepolis.ui.maps.LocationMng;
import com.quoders.apps.android.treepolis.ui.widgets.CircleButton;

import java.io.File;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckinActivity extends BaseActivity implements CheckinView {

    private static final int REQUEST_IMAGE_CAPTURE = 0x0001;

    @Bind(R.id.circleButtonTreePhoto) CircleButton mCircleButtonTree;
    @Bind(R.id.circleButtonLeafPhoto) CircleButton mCircleButtonLeaf;
    @Bind(R.id.circleButtonFruitPhoto) CircleButton mCircleButtonFruit;

    @Inject
    CheckinPresenter mPresenter;

    private GoogleMapsMng mMapMng;
    private LocationMng mLocationMng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_tree);
        ButterKnife.bind(this);
        initToolbar();
        ((TreepolisApplication) getApplication()).getCheckinComponent().inject(this);

        mPresenter.setView(this);

        initTakePhotoButtons();
        initMapFragment();
        initLocationManager();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            mPresenter.onImageCaptureSuccess();
        } else {
            mPresenter.onImageCaptureError();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewStarted();

        if(mLocationMng != null) {
            if(PermissionsHelper.doWeHaveLocationPermission(this)) {
                mLocationMng.startLocationService();
            } else {
                PermissionsHelper.requestLocationPermission(this);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissAlertDialog();
    }

    private void initTakePhotoButtons() {
        mCircleButtonTree.setButtonImage(R.drawable.ic_tree);
        mCircleButtonLeaf.setButtonImage(R.drawable.ic_leaf);
        mCircleButtonFruit.setButtonImage(R.drawable.ic_fruit);
    }

    private void initMapFragment() {
        mMapMng = new GoogleMapsMng();

        // Obtain the MapFragment and set the async listener to be notified when the map is ready.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(mMapMng);
    }

    private void initLocationManager() {
        mLocationMng = new LocationMng(this, true, mMapMng);
    }

    public void onTakePhotoClick(View view) {
        mPresenter.onTakeTreePhotoClick(view.getId());
    }

    @Override
    public void displayErrorCapturingImageDialog() {
        displayAlertDialog(getString(R.string.dialog_title_error),
                getString(R.string.dialog_message_error_taking_picture));
    }

    @Override
    public void takePicture(String filePath) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File file = new File(filePath);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void setTreePictureThumbnail(int viewId, String imagePath) {
        CircleButton circleButton = ButterKnife.findById(this, viewId);
        Bitmap bitmap = ImageUtils.scaleBitmapToFitInView(circleButton, imagePath);
        if (bitmap != null) {
            circleButton.setCircleImageViewPicture(bitmap);
            circleButton.setCircleImagePictureVisibility(View.VISIBLE);
            circleButton.setButtonImageVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

