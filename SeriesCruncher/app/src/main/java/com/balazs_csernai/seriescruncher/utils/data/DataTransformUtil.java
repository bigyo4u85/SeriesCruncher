package com.balazs_csernai.seriescruncher.utils.data;

import com.balazs_csernai.seriescruncher.details.model.SeasonsEntity;
import com.balazs_csernai.seriescruncher.details.model.SeasonsModel;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Balázs on 2016.01.23..
 */
public class DataTransformUtil {

    public static final SeasonsModel getSeasons(Map<Integer, List<EpisodeJson>> seasons) {
        return new SeasonsEntity(sortSeasons(seasons));
    }

    private static final TreeMap<Integer, List<Episode>> sortSeasons(Map<Integer, List<EpisodeJson>> seasons) {
        TreeMap<Integer, List<Episode>> sortedSeasons = new TreeMap<>();
        List<Integer> seasonNumbers = new ArrayList<>(seasons.keySet());
        Collections.sort(seasonNumbers);
        for (Integer seasonNumber : seasonNumbers) {
            sortedSeasons.put(seasonNumber, sortEpisodes(seasons.get(seasonNumber)));
        }
        return sortedSeasons;
    }

    private static final List<Episode> sortEpisodes(List<EpisodeJson> episodes) {
        List<Episode> sortedEpisodes = new ArrayList<Episode>(episodes);
        Collections.sort(sortedEpisodes, new EpisodeNumberComparator());
        return sortedEpisodes;
    }

    private static class EpisodeNumberComparator implements Comparator<Episode> {
        @Override
        public int compare(Episode lhs, Episode rhs) {
            return lhs.getEpisodeNumber() - rhs.getEpisodeNumber();
        }
    }
}
