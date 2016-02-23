package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.SeriesLoaderImpl;
import com.balazs_csernai.seriescruncher.rest.interactor.InteractorFactory;
import com.balazs_csernai.seriescruncher.rest.interactor.InteractorFactoryImpl;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoaderImpl;
import com.balazs_csernai.seriescruncher.rest.loader.RoboSpiceService;
import com.octo.android.robospice.SpiceManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Module
public class RestModule {

    @Provides
    SpiceManager provideSpiceManager() {
        return new SpiceManager(RoboSpiceService.class);
    }

    @Provides
    RequestLoader provideRequestLoader(RequestLoaderImpl impl) {
        return impl;
    }

    @Provides
    SeriesLoader provideSeriesLoader(SeriesLoaderImpl impl) {
        return impl;
    }

    @Provides
    InteractorFactory provideRequestFactory(InteractorFactoryImpl impl) {
        return impl;
    }
}
