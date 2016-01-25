package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class CountryJSON implements Country {
    @SerializedName("name")
    private String name;

    @SerializedName("code")
    private String code;

    @SerializedName("timezone")
    private String timezone;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }
}
