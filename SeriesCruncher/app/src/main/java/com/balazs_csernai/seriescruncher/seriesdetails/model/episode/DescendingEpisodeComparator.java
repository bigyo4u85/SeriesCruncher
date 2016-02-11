package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import java.util.Comparator;

/**
 * Created by erik_markus_kramli on 2016-02-08.
 */
public class DescendingEpisodeComparator implements Comparator<EpisodeModel> {
    
    @Override
    public int compare(EpisodeModel lhs, EpisodeModel rhs) {
        int episodeDiff = rhs.getEpisodeNumber() - lhs.getEpisodeNumber();
        int seasonDiff = rhs.getSeasonNumber() - lhs.getSeasonNumber();

        if (seasonDiff == 0) {
            return episodeDiff;
        }
        return seasonDiff;
    }
}
