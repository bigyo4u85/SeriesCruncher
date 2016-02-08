package com.balazs_csernai.seriescruncher.seriesdetails.model.poster;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public interface PosterModel {

    Bitmap getPoster();

    Bitmap getPosterBackground();

    ColorModel getPrimaryColor();

    ColorModel getSecondaryColor();

    ColorModel getAccentColor();

}
