package com.balazs_csernai.seriescruncher.utils.color;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Erik_Markus_Kramli on 2016-02-02.
 */
@Module
public class ColorModule {

    @Provides
    ColorProvider provideColorProvider(ColorProviderImpl impl) {
        return impl;
    }
}
