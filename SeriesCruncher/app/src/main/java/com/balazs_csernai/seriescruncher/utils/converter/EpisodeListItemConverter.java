package com.balazs_csernai.seriescruncher.utils.converter;


import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListItemEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListItemModel;

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
                .setText(String.format("S%02d-E%02d %s (%s)",
                        episodeModel.getSeasonNumber(),
                        episodeModel.getEpisodeNumber(),
                        episodeModel.getTitle(),
                        episodeModel.getAirDate()));
    }
}
