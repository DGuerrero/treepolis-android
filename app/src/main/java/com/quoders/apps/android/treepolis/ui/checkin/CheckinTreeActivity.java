package com.quoders.apps.android.treepolis.ui.checkin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.ImageUtils;
import com.quoders.apps.android.treepolis.ui.dialogs.QAlertDialog;
import com.quoders.apps.android.treepolis.ui.widgets.CircleButton;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CheckinTreeActivity extends AppCompatActivity implements CheckinView {

    @Bind(R.id.circleButtonTreePhoto) CircleButton mCircleButtonTree;
    @Bind(R.id.circleButtonLeafPhoto) CircleButton mCircleButtonLeaf;
    @Bind(R.id.circleButtonFruitPhoto) CircleButton mCircleButtonFruit;

    private static final int REQUEST_IMAGE_CAPTURE = 0x0001;

    private CheckinPresenter mPresenter;
    private QAlertDialog mAlertDialog;
    private int mButtonPhotoId;
    private String mImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin_tree);
        ButterKnife.bind(this);
        initToolbar();

        initialiseTakePhotoButtons();
    }

    private void initialiseTakePhotoButtons() {
        mCircleButtonTree.setButtonImage(R.drawable.ic_tree);
        mCircleButtonLeaf.setButtonImage(R.drawable.ic_leaf);
        mCircleButtonFruit.setButtonImage(R.drawable.ic_fruit);
    }

    private void takeTreePhoto() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = ImageUtils.createImageFile();
            } catch (IOException ex) {
                displayAlertDialog(getString(R.string.dialog_title_error),
                        getString(R.string.dialog_message_error_taking_picture));
            }

            if (photoFile != null) {
                mImagePath = photoFile.getPath();
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    public void onTakePhotoClick(View view) {
        mButtonPhotoId = view.getId();
        takeTreePhoto();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            setTreePictureThumbnail();
        } else {
            displayAlertDialog(getString(R.string.dialog_title_error),
                    getString(R.string.dialog_message_error_taking_picture));
        }
    }

    private void setTreePictureThumbnail() {
        CircleButton circleButton = ButterKnife.findById(this, mButtonPhotoId);
        Bitmap bitmap = ImageUtils.scaleBitmapToFitInView(circleButton, mImagePath);
        if(bitmap != null) {
            circleButton.setCircleImageViewPicture(bitmap);
            circleButton.setCircleImagePictureVisibility(View.VISIBLE);
            circleButton.setButtonImageVisibility(View.GONE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        dismissAlertDialog();
    }

    private void displayAlertDialog(String title, String message) {
        mAlertDialog = new QAlertDialog(this);
        mAlertDialog.showDialogNeutral(title, message, getString(R.string.dialog_button_ok), null);
    }

    private void dismissAlertDialog() {
        if(mAlertDialog != null) {
            mAlertDialog.dismissDialog();
            mAlertDialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
