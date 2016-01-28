package com.balazs_csernai.seriescruncher.image;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.01.18..
 */
@Module
public class ImageLoaderModule {

    @Provides
    ImageLoader provideImageLoader(GlideImageLoader impl) {
        return impl;
    }
}
