package com.balazs_csernai.seriescruncher.rest.epguides;

import com.balazs_csernai.seriescruncher.rest.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowShort;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface EPGuideApi {

    @GET("/show/")
    void loadShows(Callback<ShowShort[]> callback);

    @GET("/show/{showName}/")
    void loadShow(@Path("showName") String showName, Callback<Show> callback);
}
