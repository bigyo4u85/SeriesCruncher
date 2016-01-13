package com.balazs_csernai.seriescruncher.rest.api.epguides;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowShortList;

import java.util.List;
import java.util.Map;

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
    Map<Integer, List<EpisodeJson>> loadShow(@Path("showName") String showName);

    @GET("/show/{showName}/next/")
    EpisodeJson getNextEpisode(@Path("showName") String showName);

    @GET("show/{showName}/last/")
    EpisodeJson getLastEpisode(@Path("showName") String showName);

}
