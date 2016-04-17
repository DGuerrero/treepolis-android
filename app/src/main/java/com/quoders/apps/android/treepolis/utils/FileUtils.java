package com.quoders.apps.android.treepolis.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by davidguerrerodiaz on 13/04/16.
 */
public class FileUtils {

    public static String readJsonFile(Context context, String jsonFile) {

        InputStream is;
        String jsonString;

        try {
            is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}
