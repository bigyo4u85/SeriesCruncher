package com.balazs_csernai.seriescruncher.utils.common;

import android.view.View;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public final class ViewUtils {

    private ViewUtils() {}

    public static void visible(View... views) {
        setVisibility(View.VISIBLE, views);
    }

    public static void gone(View... views) {
        setVisibility(View.GONE, views);
    }

    private static void setVisibility(int visibilityFlag, View... views) {
        for (View v : views) {
            v.setVisibility(visibilityFlag);
        }
    }
}
