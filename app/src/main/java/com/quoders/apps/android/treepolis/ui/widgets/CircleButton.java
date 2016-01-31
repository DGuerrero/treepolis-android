package com.quoders.apps.android.treepolis.ui.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.quoders.apps.android.treepolis.R;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by davidguerrerodiaz on 03/01/16.
 */
public class CircleButton extends FrameLayout {

    LayoutInflater mInflater;
    ImageView mIvButtonImage;
    CircleImageView mCircleImageView;

    public CircleButton(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
        View v = mInflater.inflate(R.layout.circle_button_view, this, true);
        mIvButtonImage = (ImageView)v.findViewById(R.id.imageViewCircleImage);
        mCircleImageView = (CircleImageView)v.findViewById(R.id.circleImageViewPicture);
    }

    public void setButtonImage(int resId) {
        mIvButtonImage.setImageResource(resId);
    }

    public void setCircleImageViewPicture(Bitmap bmp) {
        mCircleImageView.setImageBitmap(bmp);
    }

    public void setButtonImageVisibility(int visibility) {
        mIvButtonImage.setVisibility(visibility);
    }

    public void setCircleImagePictureVisibility(int visibility) {
        mCircleImageView.setVisibility(visibility);
    }
}
