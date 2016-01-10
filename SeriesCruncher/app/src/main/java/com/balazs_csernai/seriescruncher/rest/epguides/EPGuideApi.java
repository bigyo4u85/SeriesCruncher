package com.balazs_csernai.seriescruncher.rest.epguides;

import com.balazs_csernai.seriescruncher.rest.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowList;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public interface EPGuideApi {

    @GET("/show/")
    ShowList loadShows();

    @GET("/show/{showName}/")
    Show loadShow(@Path("showName") String showName);
}
