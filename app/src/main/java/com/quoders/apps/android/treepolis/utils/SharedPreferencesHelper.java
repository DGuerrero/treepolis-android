package com.quoders.apps.android.treepolis.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by davidguerrerodiaz on 12/01/16.
 */
public class SharedPreferencesHelper {

    public static String getString(final Context context, final String key) {
        SharedPreferences settings = context.getSharedPreferences(SharedPreferencesDefs.TREEPOLIS_PREFS_NAME, 0);
        return settings.getString(key, "");
    }

    public static void setString(final Context context, final String key, final String value) {
        SharedPreferences settings = context.getSharedPreferences(SharedPreferencesDefs.TREEPOLIS_PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
