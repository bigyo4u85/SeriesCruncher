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
    private Swatch primarySwatch;
    private Swatch secondarySwatch;

    @Inject
    public ColorProviderImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void generateColorPalette(Bitmap bitmap) {
        if (bitmap != null) {
            List<Swatch> swatches = Palette.from(bitmap).generate().getSwatches();
            if (swatches.isEmpty()) {
                primarySwatch = null;
                secondarySwatch = null;
            } else {
                primarySwatch = findPrimaryCommonSwatch(swatches);
                secondarySwatch = findSecondarySwatch(swatches);
            }
        }
    }

    private Swatch findPrimaryCommonSwatch(@NonNull List<Swatch> swatches) {
        Iterator<Swatch> iterator = swatches.iterator();
        Swatch swatch, primarySwatch = iterator.next();

        while (iterator.hasNext()) {
            swatch = iterator.next();
            if (swatch.getPopulation() > primarySwatch.getPopulation()) {
                primarySwatch = swatch;
            }
        }
        return primarySwatch;
    }

    private Swatch findSecondarySwatch(@NonNull List<Swatch> swatches) {
        Iterator<Swatch> iterator = swatches.iterator();
        Swatch swatch, secondarySwatch = iterator.next();

        // Make sure that the primary swatch is not selected as secondary swatch
        if (secondarySwatch.getPopulation() == primarySwatch.getPopulation()) {
            secondarySwatch = iterator.next();
        }

        while (iterator.hasNext()) {
            swatch = iterator.next();
            if (swatch.getPopulation() < primarySwatch.getPopulation()
                    && swatch.getPopulation() > secondarySwatch.getPopulation()) {
                secondarySwatch = swatch;
            }
        }
        return secondarySwatch;
    }

    @ColorInt
    @Override
    public int getPrimaryTextColor() {
        if (calculateColorBrightness(getPrimaryBackgroundColor()) >= BRIGHTNESS_THRESHOLD) {
            return getColor(R.color.black);
        } else {
            return getColor(R.color.white);
        }
    }

    @ColorInt
    @Override
    public int getPrimaryBackgroundColor() {
        return primarySwatch == null ? getColor(R.color.colorPrimary) : primarySwatch.getRgb();
    }

    @Override
    public int getSecondaryTextColor() {
        if (calculateColorBrightness(getSecondaryBackgroundColor()) >= BRIGHTNESS_THRESHOLD) {
            return getColor(R.color.black);
        } else {
            return getColor(R.color.white);
        }
    }

    @Override
    public int getSecondaryBackgroundColor() {
        return secondarySwatch == null ? getColor(R.color.colorAccent) : secondarySwatch.getRgb();
    }

    @ColorInt
    private int getColor(@ColorRes int res) {
        return resources.getColor(res);
    }

    private int calculateColorBrightness(@ColorInt int color) {
        int[] rgb = {Color.red(color), Color.green(color), Color.blue(color)};
        return (int) Math.sqrt(rgb[0] * rgb[0] * .241F +
                        rgb[1] * rgb[1] * .691F +
                        rgb[2] * rgb[2] * .068F);
    }
}
