package com.balazs_csernai.seriescruncher.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public class ShowEntity implements ShowModel {

    private String name;
    private String title;
    private String imdbId;
    private String imageUrl;
    private List<EpisodeModel> episodes;

    public ShowEntity() {
        this.name = "";
        this.title = "";
        this.imdbId = "";
        this.imageUrl = "";
        this.episodes = Collections.emptyList();
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

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public List<EpisodeModel> getEpisodes() {
        return episodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setEpisodes(List<EpisodeModel> episodes) {
        this.episodes = episodes;
    }

}
