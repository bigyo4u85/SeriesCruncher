package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.seriesdetails.request.PosterRequest;
import com.balazs_csernai.seriescruncher.seriesdetails.request.SeriesDetailsRequest;
import com.balazs_csernai.seriescruncher.serieslist.request.SeriesRequest;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public interface RequestFactory {

    SeriesRequest createSeriesRequest();

    SeriesDetailsRequest createSeriesDetailsRequest(String showName, String imdbId);

    PosterRequest createPosterRequest(String url);

    <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration );
}
