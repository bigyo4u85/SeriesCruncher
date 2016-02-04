package com.balazs_csernai.seriescruncher.utils.image;

import com.balazs_csernai.seriescruncher.utils.color.ColorProvider;
import com.balazs_csernai.seriescruncher.utils.color.ColorProviderImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs_Csernai on 2016.02.04..
 */
@Module
public class ImageModule {

    @Provides
    ColorProvider provideColorProvider(ColorProviderImpl impl) {
        return impl;
    }

    @Provides
    ImageLoader provideImageLoader(GlideImageLoader impl) {
        return impl;
    }

    @Provides
    BlurStrategy provideBlurStrategy(RSBlurStrategy impl) {
        return impl;
    }
}
