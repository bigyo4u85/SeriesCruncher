package com.balazs_csernai.seriescruncher.rest.request;

import com.balazs_csernai.seriescruncher.details.request.ShowDetailsRequest;

/**
 * Created by ErikKramli on 2016.01.14..
 */
public interface RequestFactory {

    AllShowRequest createAllShowRequest();

    ShowDetailsRequest createShowDetailsRequest(String showName, String imdbId);
}
