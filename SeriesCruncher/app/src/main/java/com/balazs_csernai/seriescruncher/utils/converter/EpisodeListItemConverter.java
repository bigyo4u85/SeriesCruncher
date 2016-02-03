package com.balazs_csernai.seriescruncher.utils.converter;


import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListItemEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListItemModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
@EpisodeListItem
public class EpisodeListItemConverter implements ModelConverter<EpisodeListItemModel, EpisodeModel> {

    @Inject
    public EpisodeListItemConverter() {
    }

    @Override
    public EpisodeListItemModel convert(EpisodeModel episodeModel) {
        return new EpisodeListItemEntity()
                .setEpisode(true)
                .setExpanded(false)
                .setEpisodeNumber(String.format("Episode %02d", episodeModel.getEpisodeNumber()))
                .setTitle(episodeModel.getTitle())
                .setAirDate(episodeModel.getAirDate());
    }
}
