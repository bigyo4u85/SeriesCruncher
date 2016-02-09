package com.balazs_csernai.seriescruncher.utils.converter;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeEntity;
import com.balazs_csernai.seriescruncher.utils.common.DateUtils;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
@EpisodeDetails
public class EpisodeDetailsConverter implements ModelConverter<EpisodeEntity, Episode> {

    @Inject
    public EpisodeDetailsConverter() {
    }

    @Override
    public EpisodeEntity convert(Episode episode) {
        return new EpisodeEntity()
                .setTitle(episode.getTitle())
                .setSeasonNumber(episode.getSeasonNumber())
                .setEpisodeNumber(episode.getEpisodeNumber())
                .setAirDate(DateUtils.parseDate(episode.getAirDate()));
    }
}
