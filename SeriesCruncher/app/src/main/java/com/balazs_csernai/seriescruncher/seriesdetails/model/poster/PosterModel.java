package com.balazs_csernai.seriescruncher.seriesdetails.model.poster;

import android.graphics.Bitmap;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public interface PosterModel {

    Bitmap getPoster();

    int getBackgroundColor();

    int getTextColor();
}
