package com.balazs_csernai.seriescruncher.image;

import android.app.Activity;
import android.util.Log;

import com.bumptech.glide.Glide;

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
        Log.d("imageloader", "url: " + url);
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .into(target.get());
    }
}
