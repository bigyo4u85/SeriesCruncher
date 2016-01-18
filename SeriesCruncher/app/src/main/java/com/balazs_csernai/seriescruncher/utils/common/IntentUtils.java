package com.balazs_csernai.seriescruncher.utils.common;

import android.content.Intent;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public final class IntentUtils {

    private IntentUtils() {}

    public static String getStringArgOrThrow(Intent intent, String argName) {
        String arg = intent.getStringExtra(argName);
        if (arg == null) {
            throw new IllegalArgumentException("Missing argument: " + argName);
        }
        return arg;
    }
}
