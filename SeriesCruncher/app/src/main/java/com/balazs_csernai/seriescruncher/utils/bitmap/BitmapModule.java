package com.balazs_csernai.seriescruncher.utils.bitmap;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs_Csernai on 2016.02.02..
 */
@Module
public class BitmapModule {

    @Provides
    BlurStrategy provideBlurStrategy(RSBlurStrategy impl) {
        return impl;
    }
}
