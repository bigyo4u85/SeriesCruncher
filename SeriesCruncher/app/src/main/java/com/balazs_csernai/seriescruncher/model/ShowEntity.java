package com.balazs_csernai.seriescruncher.model;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ShowEntity implements ShowModel {

    private String name;
    private String title;
    private String imdbId;

    public ShowEntity(String name, String title, String imdbId) {
        this.name = name;
        this.title = title;
        this.imdbId = imdbId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getImdbId() {
        return imdbId;
    }
}
