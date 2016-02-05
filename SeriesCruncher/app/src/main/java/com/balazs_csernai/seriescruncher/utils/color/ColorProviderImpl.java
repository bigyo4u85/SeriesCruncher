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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
public class ColorProviderImpl implements ColorProvider {

    private static final int BRIGHTNESS_THRESHOLD = 200;
    private static final int COLOR_THRESHOLD = 150;
    private static final Comparator<Swatch> populationComparator = new PopulationComparator();

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
            List<Swatch> swatches = new ArrayList<>(Palette.from(bitmap).generate().getSwatches());
            if (swatches.isEmpty()) {
                primarySwatch = null;
                secondarySwatch = null;
            } else {
                Collections.sort(swatches, populationComparator);
                primarySwatch = findPrimaryCommonSwatch(swatches);
                secondarySwatch = findSecondarySwatch(swatches);
            }
        }
    }

    private Swatch findPrimaryCommonSwatch(@NonNull List<Swatch> swatches) {
        return swatches.get(0);
    }

    private Swatch findSecondarySwatch(@NonNull List<Swatch> swatches) {
        if (swatches.size() <= 1) {
            return null;
        } else if (swatches.size() == 2) {
            return swatches.get(1);
        }

        int position = 2;
        Swatch swatch = swatches.get(position);

        while (!areColorsDifferent(primarySwatch, swatch)) {
            swatch = swatches.get(++position);
        }

        return swatch;
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

    private boolean areColorsDifferent(Swatch swatchOne, Swatch swatchTwo) {
        return COLOR_THRESHOLD < calculateColorDifference(swatchOne.getRgb(), swatchTwo.getRgb());
    }

    private int calculateColorDifference(@ColorInt int colorOne, @ColorInt int colorTwo) {
        return Math.abs(Color.red(colorOne) - Color.red(colorTwo))
                + Math.abs(Color.green(colorOne) - Color.green(colorTwo))
                + Math.abs(Color.blue(colorOne) - Color.blue(colorTwo));
    }

    private static class PopulationComparator implements Comparator<Swatch> {

        @Override
        public int compare(Swatch swatchOne, Swatch swatchTwo) {
            return swatchTwo.getPopulation()- swatchOne.getPopulation();
        }
    }
}
