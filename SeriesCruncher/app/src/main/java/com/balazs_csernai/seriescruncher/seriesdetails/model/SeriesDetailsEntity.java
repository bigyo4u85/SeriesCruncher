package com.balazs_csernai.seriescruncher.seriesdetails.model;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class SeriesDetailsEntity implements SeriesDetailsModel {

    @Expose
    private String title;

    @Expose
    private String name;

    @Expose
    private String imdbId;

    @Expose
    private String imageUrl;

    @Expose
    private List<EpisodeEntity> episodes;

    public SeriesDetailsEntity() {
        episodes = Collections.emptyList();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getName() {
        return name;
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
        return new ArrayList<EpisodeModel>(episodes);
    }

    public SeriesDetailsEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public SeriesDetailsEntity setName(String name) {
        this.name = name;
        return this;
    }

    public SeriesDetailsEntity setImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public SeriesDetailsEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public SeriesDetailsEntity setEpisodes(List<EpisodeEntity> episodes) {
        this.episodes = episodes;
        return this;
    }
}
