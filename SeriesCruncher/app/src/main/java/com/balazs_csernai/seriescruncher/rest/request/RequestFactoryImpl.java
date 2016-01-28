package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.seriesdetails.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.utils.converter.SeriesDetails;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.serieslist.request.SeriesRequest;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public class RequestFactoryImpl implements RequestFactory {

    private final EPGuideApi epGuideApi;
    private final OmdbApi omdbApi;
    private final ModelConverter converter;

    @Inject
    public RequestFactoryImpl(EPGuideApi epGuideApi, OmdbApi omdbApi, @SeriesDetails ModelConverter converter) {
        this.epGuideApi = epGuideApi;
        this.omdbApi = omdbApi;
        this.converter = converter;
    }

    @Override
    public SeriesRequest createSeriesRequest() {
        return new SeriesRequest(epGuideApi, omdbApi);
    }

    @Override
    public SeriesDetailsRequest createSeriesDetailsRequest(String seriesName, String imdbId) {
        return new SeriesDetailsRequest(seriesName, imdbId, epGuideApi, omdbApi, converter);
    }

    @Override
    public <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration) {
        return new CachedSpiceRequest<>(request, cacheKey, cacheDuration);
    }
}
