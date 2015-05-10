package com.quoders.apps.android.treepolis.welcome;

import android.content.Intent;

/**
 * Created by davidguerrerodiaz on 03/05/15.
 */
public interface WelcomeView {

    void launchActivityForResult(Intent intent, int requestCode);
    void launchActivity(Intent intent);
}
