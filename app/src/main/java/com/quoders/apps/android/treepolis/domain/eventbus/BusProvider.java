package com.quoders.apps.android.treepolis.domain.eventbus;

/**
 * Created by davidguerrero on 14/05/15.
 */
public final class BusProvider {

    private static final AndroidBus BUS = new AndroidBus();

    public static AndroidBus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}
