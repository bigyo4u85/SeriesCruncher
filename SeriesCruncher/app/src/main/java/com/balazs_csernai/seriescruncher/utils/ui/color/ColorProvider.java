package com.balazs_csernai.seriescruncher.utils.ui.color;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
public interface ColorProvider {

    void generateColorPalette(Bitmap bitmap);

    ColorModel getPrimaryColor();

    ColorModel getSecondaryColor();

    ColorModel getAccentColor();

}
