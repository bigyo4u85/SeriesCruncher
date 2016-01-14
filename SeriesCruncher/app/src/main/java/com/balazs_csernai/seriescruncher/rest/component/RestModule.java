package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.rest.RestService;
import com.balazs_csernai.seriescruncher.rest.RestServiceImpl;
import com.balazs_csernai.seriescruncher.rest.NetworkingService;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactoryImpl;
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
    RestService provideEPGuideService(RestServiceImpl impl) {
        return impl;
    }

    @Provides
    RequestFactory provideRequestFactory(RequestFactoryImpl impl) {
        return impl;
    }
}
