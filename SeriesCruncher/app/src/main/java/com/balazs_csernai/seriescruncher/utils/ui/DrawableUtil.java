package com.balazs_csernai.seriescruncher.utils.ui;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * Created by ErikKramli on 2016.02.07..
 */
public class DrawableUtil {

    private DrawableUtil() {}

    public static Drawable getColorizedDrawable(Resources res, @DrawableRes int resId, @ColorInt int color) {
        Drawable drawable = res.getDrawable(resId);
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        return drawable;
    }
}
