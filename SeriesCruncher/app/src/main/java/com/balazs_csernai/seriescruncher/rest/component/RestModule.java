package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideService;
import com.balazs_csernai.seriescruncher.rest.epguides.EPGuideServiceImpl;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
@Module
public class RestModule {
    @Provides
    EPGuideApi provideEPGuideApi() {
        return new RestAdapter.Builder().setEndpoint("https://epguides.frecar.no").build().create(EPGuideApi.class);
    }

    @Provides
    EPGuideService provideEPGuideService(EPGuideApi api) {
        return new EPGuideServiceImpl(api);
    }
}
