package com.quoders.apps.android.treepolis.utils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by davidguerrerodiaz on 12/01/16.
 */
@Singleton
public class QSharedPrefs implements IQSharedPrefs {

    Context mContext;

    @Inject
    public QSharedPrefs(Context context) {
        this.mContext = context;
    }

    @Override
    public String getString(String key) {
        SharedPreferences settings =
                this.mContext.getSharedPreferences(SharedPreferencesDefs.TREEPOLIS_PREFS_NAME, 0);
        return settings.getString(key, "");
    }

    @Override
    public void setString(String key, String value) {
        SharedPreferences settings =
                this.mContext.getSharedPreferences(SharedPreferencesDefs.TREEPOLIS_PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
