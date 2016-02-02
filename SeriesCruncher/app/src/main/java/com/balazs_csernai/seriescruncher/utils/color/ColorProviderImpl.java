package com.balazs_csernai.seriescruncher.utils.color;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.Swatch;

import com.balazs_csernai.seriescruncher.R;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
public class ColorProviderImpl implements ColorProvider {

    private static final int BRIGHTNESS_THRESHOLD = 200;

    private final Resources resources;
    private Swatch swatch;

    @Inject
    public ColorProviderImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void generateColorPalette(Bitmap bitmap) {
        if (bitmap != null) {
            List<Swatch> swatches = Palette.from(bitmap).generate().getSwatches();
            if (swatches.isEmpty()) {
                swatch = null;
            } else {
                swatch = findMostCommonSwatch(swatches);
            }
        }
    }

    private Swatch findMostCommonSwatch(@NonNull List<Swatch> swatches) {
        Iterator<Swatch> iterator = swatches.iterator();
        Swatch swatch, mostCommonSwatch = iterator.next();
        while (iterator.hasNext()) {
            swatch = iterator.next();
            if (swatch.getPopulation() > mostCommonSwatch.getPopulation()) {
                mostCommonSwatch = swatch;
            }
        }
        return mostCommonSwatch;
    }

    @ColorInt
    @Override
    public int getTextColor() {
        if (calculateColorBrightness(getBackgroundColor()) >= BRIGHTNESS_THRESHOLD) {
            return getColor(R.color.black);

        } else {
            return getColor(R.color.white);
        }
    }

    private int calculateColorBrightness(@ColorInt int color) {
        int[] rgb = {Color.red(color), Color.green(color), Color.blue(color)};
        return  (int) Math.sqrt(
                rgb[0] * rgb[0] * .241F +
                rgb[1] * rgb[1] * .691F +
                rgb[2] * rgb[2] * .068F);
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
