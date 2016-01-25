package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class RefJSON implements Ref {
    @SerializedName("href")
    private String url;

    @Override
    public String getUrl() {
        return url;
    }
}
