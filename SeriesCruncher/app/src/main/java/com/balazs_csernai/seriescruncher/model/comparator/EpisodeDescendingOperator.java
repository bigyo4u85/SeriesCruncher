package com.balazs_csernai.seriescruncher.model.comparator;

import com.balazs_csernai.seriescruncher.model.EpisodeModel;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeDescendingOperator extends EpisodeComparator {

    @Inject
    public EpisodeDescendingOperator() {
    }

    @Override
    public int compare(EpisodeModel episode, EpisodeModel otherEpisode) {
        int seasonDiff = -getSeasonDiff(episode, otherEpisode);
        int episodeDiff = -getEpisodeDiff(episode, otherEpisode);

        return seasonDiff == 0 ? episodeDiff : seasonDiff;
    }
}
