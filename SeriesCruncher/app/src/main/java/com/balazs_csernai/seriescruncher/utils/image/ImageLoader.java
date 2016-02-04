package com.balazs_csernai.seriescruncher.utils.image;

import android.graphics.Bitmap;

import java.util.concurrent.ExecutionException;

/**
 * Created by ErikKramli on 2016.01.18..
 */
public interface ImageLoader {

    Bitmap load(String url) throws ExecutionException, InterruptedException;
}
