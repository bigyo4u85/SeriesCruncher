package com.balazs_csernai.seriescruncher.seriesdetails.model.finder;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;

import java.util.List;

/**
 * Created by erik_markus_kramli on 2016-02-10.
 */
public interface EpisodeFinder {

    void setEpisodes(List<EpisodeModel> episodes);

    boolean hasNextEpisode();

    EpisodeModel getNextEpisode();

    EpisodeModel getLastEpisode();
}
