package com.balazs_csernai.seriescruncher.model.comparator;

import com.balazs_csernai.seriescruncher.model.EpisodeModel;

import java.util.Comparator;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public abstract class EpisodeComparator implements Comparator<EpisodeModel> {

    protected int getSeasonDiff(EpisodeModel episode, EpisodeModel otherEpisode) {
        return episode.getSeasonNumber() - otherEpisode.getSeasonNumber();
    }

    protected int getEpisodeDiff(EpisodeModel episode, EpisodeModel otherEpisode) {
        return episode.getEpisodeNumber() - otherEpisode.getEpisodeNumber();
    }
}
