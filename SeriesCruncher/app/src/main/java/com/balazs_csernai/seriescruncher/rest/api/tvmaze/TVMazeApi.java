package com.balazs_csernai.seriescruncher.rest.api.tvmaze;

import com.balazs_csernai.seriescruncher.rest.api.tvmaze.model.EpisodeJSON;
import com.balazs_csernai.seriescruncher.rest.api.tvmaze.model.ShowJSON;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface TVMazeApi {

    String ENDPOINT_URL = "http://api.tvmaze.com";

    @GET("/shows")
    List<ShowJSON> loadShows(@Query("page") int page);

    @GET("/shows/{id}")
    ShowJSON loadShow(@Path("id") int showId);

    @GET("/shows/{id}/episodes")
    List<EpisodeJSON> loadEpisodes(@Path("id") int showId);
}
