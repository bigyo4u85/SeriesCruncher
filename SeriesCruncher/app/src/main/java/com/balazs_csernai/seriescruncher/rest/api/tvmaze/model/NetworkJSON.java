package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class NetworkJSON implements Network {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private CountryJSON country;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
