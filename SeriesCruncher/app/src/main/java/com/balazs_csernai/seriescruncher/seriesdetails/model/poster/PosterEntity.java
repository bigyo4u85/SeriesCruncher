package com.balazs_csernai.seriescruncher.seriesdetails.model.poster;

import android.graphics.Bitmap;

import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

/**
 * Created by ErikKramli on 2016.01.31..
 */
public class PosterEntity implements PosterModel {

    private Bitmap poster;
    private Bitmap posterBackground;
    private ColorModel primaryColor, secondaryColor, accentColor;

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
    public ColorModel getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public ColorModel getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public ColorModel getAccentColor() {
        return accentColor;
    }

    public PosterEntity setPoster(Bitmap poster) {
        this.poster = poster;
        return this;
    }

    public PosterEntity setPosterBackground(Bitmap posterBackground) {
        this.posterBackground = posterBackground;
        return this;
    }

    public PosterEntity setPrimaryColor(ColorModel color) {
        primaryColor = color;
        return this;
    }

    public PosterEntity setSecondaryColor(ColorModel color) {
        secondaryColor = color;
        return this;
    }

    public PosterEntity setAccentColor(ColorModel color) {
        accentColor = color;
        return this;
    }
}
