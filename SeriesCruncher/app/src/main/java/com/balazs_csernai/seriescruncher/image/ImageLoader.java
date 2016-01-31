package com.balazs_csernai.seriescruncher.image;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by ErikKramli on 2016.01.18..
 */
public interface ImageLoader {

    interface ImageTarget {
        ImageView get();
    }

    void load(String url, ImageTarget target);

    Bitmap load(String url);
}
