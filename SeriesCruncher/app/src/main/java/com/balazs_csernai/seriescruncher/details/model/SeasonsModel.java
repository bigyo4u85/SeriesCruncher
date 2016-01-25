package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.List;

/**
 * Created by Balázs on 2016.01.23..
 */
public interface SeasonsModel {

    List<Integer> getSeasonKeys();

    List<Integer> getEpisodeKeys(Integer seasonKey);

    Episode getEpisode(Integer seasonKey, Integer episodeKey);
}
