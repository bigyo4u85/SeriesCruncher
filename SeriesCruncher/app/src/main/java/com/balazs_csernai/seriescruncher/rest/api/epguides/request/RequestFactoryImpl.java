package com.balazs_csernai.seriescruncher.rest.api.epguides.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.converter.ShowConverter;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public class RequestFactoryImpl implements RequestFactory {

    private final ShowConverter converter;
    private final EPGuideApi epGuideApi;
    private final OmdbApi omdbApi;

    @Inject
    public RequestFactoryImpl(Provider<ShowConverter> converterProvider, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        this.converter = converterProvider.get();
        this.epGuideApi = epGuideApi;
        this.omdbApi = omdbApi;
    }

    @Override
    public ShowsRequest createShowsRequest() {
        return new ShowsRequest(converter, epGuideApi, omdbApi);
    }

    @Override
    public ShowRequest createShowRequest(String seriesName, String imdbId) {
        return new ShowRequest(seriesName, imdbId, epGuideApi, omdbApi);
    }

    @Override
    public <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration) {
        return new CachedSpiceRequest<>(request, cacheKey, cacheDuration);
    }
}
