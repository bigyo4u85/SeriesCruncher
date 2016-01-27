package com.balazs_csernai.seriescruncher.model;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeEntity implements EpisodeModel {

    private int seasonNumber;
    private int episodeNumber;
    private String title;
    private String airDate;

    public EpisodeEntity() {
        seasonNumber = 0;
        episodeNumber = 0;
        title = "";
        airDate = "";
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
    public String getTitle() {
        return title;
    }

    @Override
    public String getAirDate() {
        return airDate;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }
}
