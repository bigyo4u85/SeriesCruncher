package com.balazs_csernai.seriescruncher.rest.api.tvmaze.model;

import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface Show {

    int getId();

    String getUrl();

    String getName();

    String getType();

    String language();

    List<String> getGenres();

    String getStatus();

    int getRuntime();

    String getPremierDate();

    Schedule getSchedule();

    Rating getRating();

    int getWeight();

    Network getNetwork();

    ExternalRefs getExternalRefs();

    ImageUrls getImageUrls();

    long getLastUpdate();

    String getSummary();

    Refs getRefs();
}
