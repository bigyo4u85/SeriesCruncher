package com.balazs_csernai.seriescruncher.utils.image;

import android.content.Context;
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

    private final Context context;

    @Inject
    public GlideImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public Bitmap load(String url) throws ExecutionException, InterruptedException {
        FutureTarget<Bitmap> target = Glide.with(context)
                .load(url)
                .asBitmap()
                .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        Bitmap bitmap = target.get();
        Glide.clear(target);
        return bitmap;
    }
}
