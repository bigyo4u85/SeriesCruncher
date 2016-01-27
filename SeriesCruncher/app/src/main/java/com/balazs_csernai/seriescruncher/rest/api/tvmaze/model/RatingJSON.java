package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class RatingJSON implements Rating {

    @SerializedName("average")
    private float average;

    @Override
    public float getAverage() {
        return average;
    }
}
