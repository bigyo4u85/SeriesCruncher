package com.balazs_csernai.seriescruncher.rest.api.omdb;

import com.balazs_csernai.seriescruncher.rest.api.omdb.model.ImdbDetailJson;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public interface OmdbApi {

    String ENDPOINT_URL = "http://www.omdbapi.com";

    @GET("/?i={imdbId}/")
    ImdbDetailJson getShowDetails(@Path("imdbId") String imdbId);

}
