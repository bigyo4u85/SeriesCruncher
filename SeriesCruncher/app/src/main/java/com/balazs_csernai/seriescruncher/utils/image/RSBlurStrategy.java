package com.balazs_csernai.seriescruncher.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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
    public RSBlurStrategy(Context context) {
        this.renderScript = RenderScript.create(context);
    }

    @Override
    public Bitmap blur(Bitmap original) {
        int scaledWidth = Math.round(original.getWidth() * SCALE);
        int scaledHeight = Math.round(original.getHeight() * SCALE);

        Bitmap result = Bitmap.createBitmap(scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.scale(SCALE, SCALE);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(original, 0, 0, null);

        Allocation input = Allocation.createFromBitmap(renderScript, result, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(renderScript, input.getType());
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));

        blur.setInput(input);
        blur.setRadius(BLUR_RADIUS);
        blur.forEach(output);
        output.copyTo(result);

        renderScript.destroy();

        return result;
    }
}
