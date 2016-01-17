package com.balazs_csernai.seriescruncher.rest.api.epguides.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class ShowJson implements Show {

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("title")
    private String title;

    @SerializedName("epguide_name")
    private String epGuideName;

    @Override
    public String getImdbId() {
        return imdbId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getEpGuideName() {
        return epGuideName;
    }
}
