package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideService;
import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideServiceImpl;
import com.balazs_csernai.seriescruncher.rest.epguides.NetworkingSpiceService;
import com.octo.android.robospice.SpiceManager;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Module
public class RestModule {

    @Provides
    SpiceManager provideSpiceManager() {
        return new SpiceManager(NetworkingSpiceService.class);
    }

    @Provides
    EPGuideService provideEPGuideService(EPGuideServiceImpl impl) {
        return impl;
    }
}
