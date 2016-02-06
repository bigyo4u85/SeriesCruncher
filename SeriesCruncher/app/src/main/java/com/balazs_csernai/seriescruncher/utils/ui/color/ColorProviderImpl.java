package com.balazs_csernai.seriescruncher.utils.ui.color;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.Swatch;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorEntity;
import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
public class ColorProviderImpl implements ColorProvider {

    private static final Comparator<Swatch> POPULATION_COMPARATOR = new PopulationComparator();
    private static final Comparator<Swatch> BRIGHTNESS_COMPARATOR = new BrightnessComparator();
    private static final int BRIGHTNESS_THRESHOLD = 200;
    private static final int COLOR_THRESHOLD = 150;

    private final Resources resources;
    private Swatch primarySwatch, secondarySwatch, accentSwatch;

    @Inject
    public ColorProviderImpl(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void generateColorPalette(Bitmap bitmap) {
        if (bitmap != null) {
            List<Swatch> swatches = new ArrayList<>(Palette.from(bitmap).generate().getSwatches());
            if (swatches.isEmpty()) {
                primarySwatch = secondarySwatch = accentSwatch = null;

            } else {
                Collections.sort(swatches, POPULATION_COMPARATOR);
                primarySwatch = findPrimarySwatch(swatches);
                secondarySwatch = findSecondarySwatch(swatches);

                Collections.sort(swatches, BRIGHTNESS_COMPARATOR);
                accentSwatch = findAccentSwatch(swatches);
            }
        }
    }

    private Swatch findPrimarySwatch(@NonNull List<Swatch> swatches) {
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

    private boolean areColorsDifferent(Swatch swatchOne, Swatch swatchTwo) {
        return COLOR_THRESHOLD < calculateColorDifference(swatchOne.getRgb(), swatchTwo.getRgb());
    }

    private int calculateColorDifference(@ColorInt int colorOne, @ColorInt int colorTwo) {
        return Math.abs(Color.red(colorOne) - Color.red(colorTwo))
                + Math.abs(Color.green(colorOne) - Color.green(colorTwo))
                + Math.abs(Color.blue(colorOne) - Color.blue(colorTwo));
    }

    private Swatch findAccentSwatch(@NonNull List<Swatch> swatches) {
        swatches.remove(primarySwatch);
        swatches.remove(secondarySwatch);
        if (swatches.isEmpty()) {
            return null;
        } else {
            return swatches.get(0);
        }
    }

    @Override
    public ColorModel getPrimaryColor() {
        return createColorModel(primarySwatch, R.color.colorPrimary);
    }

    @Override
    public ColorModel getSecondaryColor() {
        return createColorModel(secondarySwatch, R.color.colorSecondary);
    }

    @Override
    public ColorModel getAccentColor() {
        return createColorModel(accentSwatch, R.color.colorAccent);
    }

    private ColorModel createColorModel(Swatch swatch, @ColorRes int defaultColorRes) {
        @ColorInt int bg = getColor(swatch, defaultColorRes);
        return new ColorEntity(bg, calculateForegroundColor(bg));
    }

    @ColorInt
    private int getColor(Swatch swatch, @ColorRes int defaultColorRes) {
        return swatch == null ? getColor(defaultColorRes) : swatch.getRgb();
    }

    @ColorInt
    private int getColor(@ColorRes int res) {
        return resources.getColor(res);
    }

    @ColorInt
    private int calculateForegroundColor(@ColorInt int backgroundColor) {
        if (calculateColorBrightness(backgroundColor) >= BRIGHTNESS_THRESHOLD) {
            return getColor(R.color.black);
        } else {
            return getColor(R.color.white);
        }
    }

    private static int calculateColorBrightness(@ColorInt int color) {
        int[] rgb = {Color.red(color), Color.green(color), Color.blue(color)};
        return (int) Math.sqrt(
                rgb[0] * rgb[0] * .241F +
                rgb[1] * rgb[1] * .691F +
                rgb[2] * rgb[2] * .068F);
    }

    private static final class PopulationComparator implements Comparator<Swatch> {

        @Override
        public int compare(Swatch swatchOne, Swatch swatchTwo) {
            return swatchTwo.getPopulation() - swatchOne.getPopulation();
        }
    }

    private static final class BrightnessComparator implements Comparator<Swatch> {

        @Override
        public int compare(Swatch lhs, Swatch rhs) {
            return calculateColorBrightness(rhs.getRgb()) - calculateColorBrightness(lhs.getRgb());
        }
    }
}
