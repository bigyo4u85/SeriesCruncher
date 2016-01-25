package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.rest.api.epguides.loader.DataLoader;
import com.balazs_csernai.seriescruncher.rest.api.epguides.loader.DataLoaderImpl;
import com.balazs_csernai.seriescruncher.rest.api.epguides.request.RequestFactory;
import com.balazs_csernai.seriescruncher.rest.api.epguides.request.RequestFactoryImpl;
import com.balazs_csernai.seriescruncher.rest.loader.NetworkingService;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoaderImpl;
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
        return new SpiceManager(NetworkingService.class);
    }

    @Provides
    RequestLoader provideRequestLoader(RequestLoaderImpl impl) {
        return impl;
    }

    @Provides
    DataLoader provideSeriesLoader(DataLoaderImpl impl) {
        return impl;
    }

    @Provides
    RequestFactory provideRequestFactory(RequestFactoryImpl impl) {
        return impl;
    }
}
