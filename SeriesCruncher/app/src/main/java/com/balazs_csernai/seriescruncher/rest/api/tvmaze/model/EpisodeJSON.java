package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.balazs_csernai.seriescruncher.rest.api.tvmaze.model.Episode;
import com.balazs_csernai.seriescruncher.rest.api.tvmaze.model.ImageUrls;
import com.balazs_csernai.seriescruncher.rest.api.tvmaze.model.ImageUrlsJSON;
import com.balazs_csernai.seriescruncher.rest.api.tvmaze.model.Refs;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class EpisodeJSON implements Episode {

    @SerializedName("id")
    private int id;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("season")
    private int season;

    @SerializedName("episode")
    private int episode;

    @SerializedName("airdate")
    private String airDate;

    @SerializedName("airtime")
    private String airTime;

    @SerializedName("aitstamp")
    private String airTimeStamp;

    @SerializedName("runtime")
    private int runTime;

    @SerializedName("image")
    private ImageUrlsJSON imageUrls;

    @SerializedName("summary")
    private String summary;

    @SerializedName("_links")
    private Refs refs;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSeasonNumber() {
        return season;
    }

    @Override
    public int getEpisodeNumber() {
        return episode;
    }

    @Override
    public String getAirDate() {
        return airDate;
    }

    @Override
    public String getAirTime() {
        return airTime;
    }

    @Override
    public int getRuntime() {
        return runTime;
    }

    @Override
    public ImageUrls getImageUrls() {
        return imageUrls;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public Refs getRefs() {
        return refs;
    }
}
