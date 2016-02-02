package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeListItemEntity implements EpisodeListItemModel {

    private boolean episode;
    private boolean expanded;
    private String text;

    public EpisodeListItemEntity() {
        this.episode = false;
        this.expanded = false;
        this.text = "";
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
    public String getText() {
        return text;
    }

    public EpisodeListItemEntity setEpisode(boolean episode) {
        this.episode = episode;
        return this;
    }

    public EpisodeListItemEntity setExpanded(boolean expanded) {
        this.expanded = expanded;
        return this;
    }

    public EpisodeListItemEntity setText(String text) {
        this.text = text;
        return this;
    }
}
