package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import java.util.Collections;
import java.util.List;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeListEntity implements EpisodeListModel {

    private List<EpisodeListItemModel> episodes;

    public EpisodeListEntity() {
        episodes = Collections.emptyList();
    }

    @Override
    public List<EpisodeListItemModel> getEpisodes() {
        return episodes;
    }

    public EpisodeListEntity setEpisodes(List<EpisodeListItemModel> episodes) {
        this.episodes = episodes;
        return this;
    }
}

