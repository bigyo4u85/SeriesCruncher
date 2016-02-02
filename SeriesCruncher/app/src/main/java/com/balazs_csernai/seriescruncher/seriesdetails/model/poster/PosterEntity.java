package com.balazs_csernai.seriescruncher.seriesdetails.model.poster;

import android.graphics.Bitmap;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterEntity implements PosterModel {

    private Bitmap poster;
    private int backgroundColor, textColor;

    public PosterEntity() {}

    @Override
    public Bitmap getPoster() {
        return poster;
    }

    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public int getTextColor() {
        return textColor;
    }

    public PosterEntity setPoster(Bitmap poster) {
        this.poster = poster;
        return this;
    }

    public PosterEntity setBackgroundColor(int color) {
        backgroundColor = color;
        return this;
    }

    public PosterEntity setTextColor(int color) {
        textColor = color;
        return this;
    }
}
