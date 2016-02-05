package com.balazs_csernai.seriescruncher.seriesdetails.model.poster;

import android.graphics.Bitmap;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterEntity implements PosterModel {

    private Bitmap poster;
    private Bitmap posterBackground;
    private int primaryBackgroundColor, primaryTextColor, secondaryBackgroundColor, secondaryTextColor;

    public PosterEntity() {
    }

    @Override
    public Bitmap getPoster() {
        return poster;
    }

    @Override
    public Bitmap getPosterBackground() {
        return posterBackground;
    }

    @Override
    public int getPrimaryBackgroundColor() {
        return primaryBackgroundColor;
    }

    @Override
    public int getPrimaryTextColor() {
        return primaryTextColor;
    }

    @Override
    public int getSecondaryBackgroundColor() {
        return secondaryBackgroundColor;
    }

    @Override
    public int getSecondaryTextColor() {
        return secondaryTextColor;
    }

    public PosterEntity setPoster(Bitmap poster) {
        this.poster = poster;
        return this;
    }

    public PosterEntity setPosterBackground(Bitmap posterBackground) {
        this.posterBackground = posterBackground;
        return this;
    }

    public PosterEntity setPrimaryBackgroundColor(int color) {
        primaryBackgroundColor = color;
        return this;
    }

    public PosterEntity setPrimaryTextColor(int color) {
        primaryTextColor = color;
        return this;
    }

    public PosterEntity setSecondaryBackgroundColor(int color) {
        secondaryBackgroundColor = color;
        return this;
    }

    public PosterEntity setSecondaryTextColor(int color) {
        secondaryTextColor = color;
        return this;
    }
}
