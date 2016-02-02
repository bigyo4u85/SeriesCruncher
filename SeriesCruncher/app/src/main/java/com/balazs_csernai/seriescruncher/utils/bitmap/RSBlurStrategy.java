package com.balazs_csernai.seriescruncher.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.02.02..
 */
public class RSBlurStrategy implements BlurStrategy {

    private final RenderScript renderScript;
    private static final float SCALE = 1.0f;
    private static final float BLUR_RADIUS = 8.0f;

    @Inject
    public RSBlurStrategy(RenderScript renderScript) {
        this.renderScript = renderScript;
    }

    @Override
    public Bitmap blur(Bitmap original) {
        int scaledWidth = Math.round(original.getWidth() * SCALE);
        int scaledHeight = Math.round(original.getHeight() * SCALE);

        //Bitmap result = Bitmap.createScaledBitmap(original, scaledWidth, scaledHeight, false);
        Bitmap result = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        //canvas.translate(-scaledWidth, -scaledHeight);
        canvas.drawBitmap(original, 0, 0, null);

        Allocation alloc = Allocation.createFromBitmap(renderScript, result);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, alloc.getElement());
        blur.setInput(alloc);
        blur.setRadius(BLUR_RADIUS);
        blur.forEach(alloc);
        alloc.copyTo(result);

        return result;
    }
}
