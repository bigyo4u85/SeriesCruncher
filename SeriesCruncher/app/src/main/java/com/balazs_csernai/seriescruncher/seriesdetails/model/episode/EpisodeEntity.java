package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import com.google.gson.annotations.Expose;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeEntity implements EpisodeModel {

    @Expose
    private String title;
    @Expose
    private int seasonNumber;
    @Expose
    private int episodeNumber;
    @Expose
    private String airDate;

    public EpisodeEntity() {
        title = "";
        seasonNumber = 0;
        episodeNumber = 0;
        airDate = "";
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getSeasonNumber() {
        return seasonNumber;
    }

    @Override
    public int getEpisodeNumber() {
        return episodeNumber;
    }

    @Override
    public String getAirDate() {
        return airDate;
    }

    public EpisodeEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public EpisodeEntity setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
        return this;
    }

    public EpisodeEntity setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
        return this;
    }

    public EpisodeEntity setAirDate(String airDate) {
        this.airDate = airDate;
        return this;
    }
}
