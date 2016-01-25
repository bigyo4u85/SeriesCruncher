package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Balázs on 2016.01.23..
 */
public class SeasonsEntity implements SeasonsModel {

    private final TreeMap<Integer, List<Episode>> seasons;

    public SeasonsEntity(TreeMap<Integer, List<Episode>> seasons) {
        this.seasons = seasons;
    }

    @Override
    public List<Integer> getSeasonKeys() {
        return new ArrayList<>(seasons.keySet());
    }

    @Override
    public List<Integer> getEpisodeKeys(Integer seasonKey) {
        List<Episode> episodes = seasons.get(seasonKey);
        if (episodes != null) {
            List<Integer> episodeKeys = new ArrayList<>(episodes.size());
            for (Episode episode : episodes) {
                episodeKeys.add(Integer.valueOf(episode.getEpisodeNumber()));
            }
        }
        return null;
    }

    @Override
    public Episode getEpisode(Integer seasonKey, Integer episodeKey) {
        List<Episode> episodes = seasons.get(seasonKey);
        if (episodeKey != null) {
            for (Episode episode : episodes) {
                if (Integer.valueOf(episode.getEpisodeNumber()).equals(episodeKey)) {
                    return episode;
                }
            }
        }
        return null;
    }
}
