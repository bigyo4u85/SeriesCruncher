package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeListItemEntity implements EpisodeListItemModel {

    private boolean episode;
    private boolean expanded;
    private String episodeNumber;
    private String title;
    private String airDate;

    public EpisodeListItemEntity() {
        episode = false;
        expanded = false;
        episodeNumber = "";
        title = "";
        airDate = "";
    }

    @Override
    public boolean isEpisode() {
        return episode;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void toggle() {
        expanded = !expanded;
    }

    @Override
    public String getEpisodeNumber() {
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

    public EpisodeListItemEntity setEpisode(boolean episode) {
        this.episode = episode;
        return this;
    }

    public EpisodeListItemEntity setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    public EpisodeListItemEntity setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
        return this;
    }

    public EpisodeListItemEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public EpisodeListItemEntity setAirDate(String airDate) {
        this.airDate = airDate;
        return this;
    }
}
