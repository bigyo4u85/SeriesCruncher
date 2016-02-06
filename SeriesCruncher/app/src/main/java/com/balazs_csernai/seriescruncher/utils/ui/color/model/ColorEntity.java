package com.balazs_csernai.seriescruncher.utils.ui.color.model;

import android.support.annotation.ColorInt;

/**
 * Created by ErikKramli on 2016.02.06..
 */
public class ColorEntity implements ColorModel {

    private int backgroundColor, foregroundColor;

    public ColorEntity(int backgroundColor, int foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
    }

    @Override
    @ColorInt
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    @ColorInt
    public int getForegroundColor() {
        return foregroundColor;
    }
}
