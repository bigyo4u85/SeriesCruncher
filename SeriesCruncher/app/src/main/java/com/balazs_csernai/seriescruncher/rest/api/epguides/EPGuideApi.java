package com.balazs_csernai.seriescruncher.rest.api.epguides;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeasonJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface EPGuideApi {

    String ENDPOINT_URL = "https://epguides.frecar.no";

    @GET("/show/")
    ShowShortList loadShows();

    @GET("/show/{showName}/")
    SeasonJson loadShow(@Path("showName") String showName);

    @GET("/show/{showName}/next/")
    EpisodeJson getNextEpisode(@Path("showName") String showName);

    @GET("show/{showName}/last/")
    EpisodeJson getLastEpisode(@Path("showName") String showName);

}
