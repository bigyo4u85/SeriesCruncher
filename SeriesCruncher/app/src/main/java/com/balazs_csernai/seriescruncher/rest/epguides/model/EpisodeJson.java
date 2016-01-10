package com.balazs_csernai.seriescruncher.rest.epguides.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class EpisodeJson {

    @SerializedName("season")
    private int season;

    @SerializedName("number")
    private int number;

    @SerializedName("title")
    private String title;

    @SerializedName("release_date")
    private Date releaseDate;

    @SerializedName("show")
    private Show show;




}
