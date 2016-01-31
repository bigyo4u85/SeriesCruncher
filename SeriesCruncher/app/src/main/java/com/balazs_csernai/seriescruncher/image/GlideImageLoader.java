package com.balazs_csernai.seriescruncher.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.18..
 */
public class GlideImageLoader implements ImageLoader {

    private final Activity activity;

    @Inject
    public GlideImageLoader(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void load(String url, ImageTarget target) {
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .into(target.get());
    }

    @Override
    public Bitmap load(String url) {
        Bitmap bitmap;
        try {
            bitmap = Glide.with(activity)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("GlideImageLoader", e.getMessage());
            bitmap = null;
        }
        return bitmap;
    }
}
