package com.balazs_csernai.seriescruncher.seriesdetails.model;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeEntity implements EpisodeModel {

    private String title;
    private int seasonNumber;
    private int episodeNumber;
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
