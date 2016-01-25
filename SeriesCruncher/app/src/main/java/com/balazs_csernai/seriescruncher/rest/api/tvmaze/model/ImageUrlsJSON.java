package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ImageUrlsJSON implements ImageUrls {
    @SerializedName("medium")
    private String medium;

    @SerializedName("original")
    private String original;

    @Override
    public String getMedium() {
        return medium;
    }

    @Override
    public String getOriginal() {
        return original;
    }
}
