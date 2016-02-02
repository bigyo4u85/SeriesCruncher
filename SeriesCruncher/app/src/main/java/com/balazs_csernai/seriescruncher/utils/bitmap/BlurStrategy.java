package com.balazs_csernai.seriescruncher.utils.bitmap;

import android.graphics.Bitmap;

/**
 * Created by Balazs_Csernai on 2016.02.02..
 */
public interface BlurStrategy {

    Bitmap blur(Bitmap original);
}
