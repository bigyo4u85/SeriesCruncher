package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balázs on 2016.01.23..
 */
public class RefsJSON implements Refs {
    @SerializedName("self")
    private RefJSON self;

    @SerializedName("previousepisode")
    private RefJSON previous;

    @SerializedName("nextepisode")
    private RefJSON next;

    @Override
    public Ref getSelf() {
        return self;
    }

    @Override
    public Ref getPreviousEpisode() {
        return previous;
    }

    @Override
    public Ref getNextEpisode() {
        return next;
    }
}
