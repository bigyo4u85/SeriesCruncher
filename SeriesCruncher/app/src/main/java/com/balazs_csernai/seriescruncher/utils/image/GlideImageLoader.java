package com.balazs_csernai.seriescruncher.utils.image;

import android.app.Activity;
import android.graphics.Bitmap;

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
    public Bitmap load(String url) throws ExecutionException, InterruptedException {
        FutureTarget<Bitmap> target = Glide.with(activity)
                .load(url)
                .asBitmap()
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        Bitmap bitmap = target.get();
        Glide.clear(target);
        return bitmap;
    }
}
