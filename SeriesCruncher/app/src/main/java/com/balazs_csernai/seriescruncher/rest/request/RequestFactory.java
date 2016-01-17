package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.details.request.ShowDetailsRequest;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public interface RequestFactory {

    AllShowRequest createAllShowRequest();

    ShowDetailsRequest createShowDetailsRequest(String showName, String imdbId);

    <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration );
}
