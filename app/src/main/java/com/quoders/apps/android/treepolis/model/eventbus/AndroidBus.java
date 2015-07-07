package com.quoders.apps.android.treepolis.model.eventbus;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by davidguerrero on 14/05/15.
 */
public class AndroidBus extends Bus {

    private final Handler mainThread = new Handler(Looper.getMainLooper());

    @Override public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
           });
        }
    }
}
