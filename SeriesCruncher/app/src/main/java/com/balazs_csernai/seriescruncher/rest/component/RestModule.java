package com.balazs_csernai.seriescruncher.rest.component;

import com.balazs_csernai.seriescruncher.image.ImageLoader;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.SeriesLoaderImpl;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.loader.NetworkingService;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoader;
import com.balazs_csernai.seriescruncher.rest.loader.RequestLoaderImpl;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactory;
import com.balazs_csernai.seriescruncher.rest.request.RequestFactoryImpl;
import com.balazs_csernai.seriescruncher.seriesdetails.request.PosterRequest;
import com.balazs_csernai.seriescruncher.seriesdetails.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.serieslist.request.SeriesRequest;
import com.balazs_csernai.seriescruncher.utils.color.ColorProvider;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.utils.converter.SeriesDetails;
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
    SeriesLoader provideSeriesLoader(SeriesLoaderImpl impl) {
        return impl;
    }

    @Provides
    RequestFactory provideRequestFactory(RequestFactoryImpl impl) {
        return impl;
    }

    @Provides
    SeriesRequest provideSeriesRequest(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        return new SeriesRequest(epGuideApi, omdbApi);
    }

    @Provides
    SeriesDetailsRequest provideSeriesDetailsRequest(EPGuideApi epGuideApi, OmdbApi omdbApi, @SeriesDetails ModelConverter converter) {
        return new SeriesDetailsRequest(epGuideApi, omdbApi, converter);
    }

    @Provides
    PosterRequest providePosterRequest(ImageLoader imageLoader, ColorProvider colorProvider) {
        return new PosterRequest(imageLoader, colorProvider);
    }
}
