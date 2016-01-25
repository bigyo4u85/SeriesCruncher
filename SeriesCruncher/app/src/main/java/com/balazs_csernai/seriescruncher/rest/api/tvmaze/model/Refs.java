package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface Refs {

    Ref getSelf();

    Ref getPreviousEpisode();

    Ref getNextEpisode();
}
