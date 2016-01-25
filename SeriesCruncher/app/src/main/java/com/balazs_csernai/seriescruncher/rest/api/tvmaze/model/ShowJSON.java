package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ShowJSON implements Show {
    @SerializedName("id")
    private int id;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("language")
    private String language;

    @SerializedName("genres")
    private List<String> genres;

    @SerializedName("status")
    private String status;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("premiered")
    private String premierDate;

    @SerializedName("schedule")
    private ScheduleJSON schedule;

    @SerializedName("rating")
    private RatingJSON rating;

    @SerializedName("weight")
    private int weight;

    @SerializedName("network")
    private NetworkJSON network;

    @SerializedName("externals")
    private ExternalRefsJSON externalRefs;

    @SerializedName("image")
    private ImageUrlsJSON imageUrls;

    @SerializedName("summary")
    private String summary;

    @SerializedName("updated")
    private long lastUpdate;

    @SerializedName("_links")
    private RefsJSON refs;

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
    public String getType() {
        return type;
    }

    @Override
    public String language() {
        return language;
    }

    @Override
    public List<String> getGenres() {
        return genres;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public int getRuntime() {
        return runtime;
    }

    @Override
    public String getPremierDate() {
        return premierDate;
    }

    @Override
    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public Network getNetwork() {
        return network;
    }

    @Override
    public ExternalRefs getExternalRefs() {
        return externalRefs;
    }

    @Override
    public ImageUrls getImageUrls() {
        return imageUrls;
    }

    @Override
    public long getLastUpdate() {
        return lastUpdate;
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
