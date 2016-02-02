package com.balazs_csernai.seriescruncher.utils.color;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.Swatch;

import com.balazs_csernai.seriescruncher.R;

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
            Palette palette = Palette.from(bitmap).generate();
            swatch = getSwatch(palette.getDarkVibrantSwatch(),
                    palette.getMutedSwatch(),
                    palette.getVibrantSwatch(),
                    palette.getSwatches().get(0));
        }
    }


    private Swatch getSwatch(Swatch... swatches) {
        Swatch desiredSwatch = null;
        for (Swatch swatch : swatches) {
            if (swatch != null) {
                desiredSwatch = swatch;
                break;
            }
        }
        return desiredSwatch;
    }

    @ColorInt
    @Override
    public int getTextColor() {
        int color = getBackgroundColor();
        int[] rgb = {Color.red(color), Color.green(color), Color.blue(color)};
        int brightness = (int)Math.sqrt(
                rgb[0] * rgb[0] * .241 +
                rgb[1] * rgb[1] * .691 +
                rgb[2] * rgb[2] * .068);

        if (brightness >= BRIGHTNESS_THRESHOLD) {
            return getColor(R.color.black);
        } else {
            return getColor(R.color.white);
        }
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
