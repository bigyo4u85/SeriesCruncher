package com.balazs_csernai.seriescruncher.utils.navigator;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ErikKramli on 2016.01.17..
 */
@Module
public class NavigatorModule {

    @Provides
    SeriesListNavigator provideNavigator(SeriesListNavigatorImpl impl) {
        return impl;
    }
}
