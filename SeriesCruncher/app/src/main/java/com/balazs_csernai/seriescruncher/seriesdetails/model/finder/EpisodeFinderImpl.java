package com.balazs_csernai.seriescruncher.seriesdetails.model.finder;

import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.DescendingEpisodeComparator;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;
import com.balazs_csernai.seriescruncher.utils.common.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by erik_markus_kramli on 2016-02-10.
 */
public class EpisodeFinderImpl implements EpisodeFinder {

    private static final int INVALID_INDEX = -1;

    private final Date today;
    private List<EpisodeModel> episodes;
    private int lastAiredEpisodeIndex, nextEpisodeIndex;

    @Inject
    public EpisodeFinderImpl() {
        today = DateUtils.getTodayDate();
        episodes = Collections.emptyList();
        lastAiredEpisodeIndex = nextEpisodeIndex = INVALID_INDEX;
    }

    @Override
    public void setEpisodes(final List<EpisodeModel> episodes) {
        /**
         * Create a defensive copy of the list of episodes,
         * which is then sorted in descending chronological order.
         */
        this.episodes = new ArrayList<>(episodes);
        Collections.sort(this.episodes, new DescendingEpisodeComparator());

        lastAiredEpisodeIndex = findLastAiredEpisodeIndex(episodes);
        nextEpisodeIndex = lastAiredEpisodeIndex - 1;
    }

    private int findLastAiredEpisodeIndex(final List<EpisodeModel> episodes) {
        int index = -1;
        for (int i = 0, n = episodes.size(); i < n && index == INVALID_INDEX; i++) {
            if (isLastAiredEpisode(episodes.get(i))) {
                index = i;
            }
        }
        return index;
    }

    private boolean isLastAiredEpisode(EpisodeModel episode) {
        Date episodeAirDate = episode.getAirDate();
        return episodeAirDate != null && today.after(episodeAirDate);
    }

    @Override
    public boolean hasNextEpisode() {
        return !episodes.isEmpty() && 0 <= nextEpisodeIndex;
    }

    @Override
    public boolean hasNewEpisodeToday() {
        return hasNextEpisode() && DateUtils.isToday(getNextEpisode().getAirDate());
    }

    @Override
    public EpisodeModel getNextEpisode() {
        return episodes.get(nextEpisodeIndex);
    }

    @Override
    public EpisodeModel getLastEpisode() {
        return episodes.get(lastAiredEpisodeIndex);
    }
}
