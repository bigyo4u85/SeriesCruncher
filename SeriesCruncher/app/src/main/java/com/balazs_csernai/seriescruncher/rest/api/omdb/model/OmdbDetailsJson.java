package com.balazs_csernai.seriescruncher.rest.api.omdb.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class OmdbDetailsJson implements OmdbDetails {

    @SerializedName("imdbID")
    private String imdbId;

    @SerializedName("Title")
    private String title;

    @SerializedName("Poster")
    private String posterUrl;

    @SerializedName("Language")
    private String language;

    @SerializedName("imdbRating")
    private float imdbRating;

    @Override
    public String getImdbId() {
        return imdbId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getPosterUrl() {
        return posterUrl;
    }
}
