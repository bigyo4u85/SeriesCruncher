package com.balazs_csernai.seriescruncher.utils.color;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
public interface ColorProvider {

    void generateColorPalette(Bitmap bitmap);

    @ColorInt
    int getPrimaryTextColor();

    @ColorInt
    int getPrimaryBackgroundColor();

    @ColorInt
    int getSecondaryTextColor();

    @ColorInt
    int getSecondaryBackgroundColor();
}
