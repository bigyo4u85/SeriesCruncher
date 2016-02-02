package com.balazs_csernai.seriescruncher.seriesdetails.model.episode;

import java.util.Comparator;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
public class EpisodeComparator implements Comparator<EpisodeModel> {
    @Override
    public int compare(EpisodeModel lhs, EpisodeModel rhs) {
        int episodeDiff = lhs.getEpisodeNumber() - rhs.getEpisodeNumber();
        int seasonDiff = lhs.getSeasonNumber() - rhs.getSeasonNumber();

        if (seasonDiff == 0) {
            return episodeDiff;
        }
        return seasonDiff;
    }
}
