package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

/**
 * Created by ErikKramli on 2016.01.11..
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Converter provideConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    OmdbApi provideOmdbApi(Converter converter) {
        return new RestAdapter.Builder()
                .setEndpoint(OmdbApi.ENDPOINT_URL)
                .setConverter(converter)
                .build()
                .create(OmdbApi.class);
    }

    @Provides
    @Singleton
    EPGuideApi provideEpisodeGuideApi(Converter converter) {
        return new RestAdapter.Builder()
                .setEndpoint(EPGuideApi.ENDPOINT_URL)
                .setConverter(converter)
                .build()
                .create(EPGuideApi.class);
    }
}
