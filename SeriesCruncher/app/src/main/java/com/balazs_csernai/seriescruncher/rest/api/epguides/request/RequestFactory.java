package com.balazs_csernai.seriescruncher.rest.api.epguides.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.request.ShowRequest;
import com.balazs_csernai.seriescruncher.rest.api.epguides.request.ShowsRequest;
import com.octo.android.robospice.request.CachedSpiceRequest;
import com.octo.android.robospice.request.SpiceRequest;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public interface RequestFactory {

    ShowsRequest createShowsRequest();

    ShowRequest createShowRequest(String showName, String imdbId);

    <JSON> CachedSpiceRequest<JSON> createCachedRequest(SpiceRequest<JSON> request, String cacheKey, long cacheDuration );
}
