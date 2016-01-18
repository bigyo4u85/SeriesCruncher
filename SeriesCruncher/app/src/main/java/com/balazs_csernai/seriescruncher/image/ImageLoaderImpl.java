package com.balazs_csernai.seriescruncher.image;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.18..
 */
public class ImageLoaderImpl implements ImageLoader {

    private final Activity activity;

    @Inject
    public ImageLoaderImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void load(String url, ImageView target) {
        Glide.with(activity)
                .load(url)
                .into(target);
    }
}
