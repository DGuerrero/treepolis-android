package com.quoders.apps.android.treepolis.utils;

/**
 * Created by davidguerrerodiaz on 21/02/16.
 */
public interface IQSharedPrefs {

    String getString(final String key);

    void setString(final String key, final String value);

}
