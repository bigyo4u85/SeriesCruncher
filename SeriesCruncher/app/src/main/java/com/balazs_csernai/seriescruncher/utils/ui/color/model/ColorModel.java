package com.balazs_csernai.seriescruncher.utils.ui.color.model;

import android.support.annotation.ColorInt;

/**
 * Created by ErikKramli on 2016.02.06..
 */
public interface ColorModel {

    @ColorInt
    int getBackgroundColor();

    @ColorInt
    int getForegroundColor();
}
