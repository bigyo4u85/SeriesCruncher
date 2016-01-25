package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ExternalRefsJSON implements ExternalRefs {
    @SerializedName("tvrage")
    private String tvRage;

    @SerializedName("thetvdb")
    private String theTVDB;

    @SerializedName("imdb")
    private String imdb;

    @Override
    public String getTVRage() {
        return tvRage;
    }

    @Override
    public String getTheTVDB() {
        return theTVDB;
    }

    @Override
    public String getIMDB() {
        return imdb;
    }
}
