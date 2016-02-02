package com.balazs_csernai.seriescruncher.utils.color;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v7.graphics.Palette;

import com.balazs_csernai.seriescruncher.R;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
public class ColorProviderImpl implements ColorProvider {

    private final Resources resources;
    private Palette.Swatch swatch;

    @Inject
    public ColorProviderImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void generateColorPalette(Bitmap bitmap) {
        if (bitmap != null) {
            swatch = Palette.from(bitmap).generate().getSwatches().get(1);
        }
    }

    @ColorInt
    @Override
    public int getTextColor() {
        return swatch == null ? getColor(R.color.white) : swatch.getTitleTextColor();
    }

    @ColorInt
    @Override
    public int getBackgroundColor() {
        return swatch == null ? getColor(R.color.colorPrimary) : swatch.getRgb();
    }

    @ColorInt
    private int getColor(@ColorRes int res) {
        return resources.getColor(res);
    }
}
