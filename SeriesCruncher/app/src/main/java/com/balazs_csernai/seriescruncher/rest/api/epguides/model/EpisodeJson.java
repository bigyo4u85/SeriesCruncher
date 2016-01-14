package com.balazs_csernai.seriescruncher.rest.api.epguides.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class EpisodeJson implements Episode {

    @SerializedName("season")
    private int season;

    @SerializedName("number")
    private int number;

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("show")
    private ShowJson showJson;


    @Override
    public int getSeasonNumber() {
        return season;
    }

    @Override
    public int getEpisodeNumber() {
        return number;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Show getShow() {
        return showJson;
    }

    @Override
    public String getAirDate() {
        return releaseDate;
    }
}
