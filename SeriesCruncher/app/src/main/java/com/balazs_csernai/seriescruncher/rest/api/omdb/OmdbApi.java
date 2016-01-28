package com.balazs_csernai.seriescruncher.rest.api.omdb;

import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public interface OmdbApi {

    String ENDPOINT_URL = "http://www.omdbapi.com";

    @GET("/")
    OmdbDetailsJson getSeriesDetails(@Query("i") String imdbId);

}
