package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class SeasonItemsModelBuilderImpl implements SeasonItemsModelBuilder {

    @Inject
    public SeasonItemsModelBuilderImpl() {
    }

    @Override
    public DetailsScreenModel empty() {
        return new DetailsScreenEntity(Collections.<DetailScreenItemModel>emptyList());
    }

    @Override
    public DetailsScreenModel build(SeasonsModel seasons) {
        List<DetailScreenItemModel> items = new ArrayList<>();
        for (Integer seasonKey : seasons.getSeasonKeys()) {
            items.add(createHeaderItem(seasonKey));
            for (Integer episodeKey : seasons.getEpisodeKeys(seasonKey)) {
                items.add(createSeasonEpisodeItem(seasons.getEpisode(seasonKey, episodeKey)));
            }
        }
        return new DetailsScreenEntity(items);
    }

    private DetailScreenItemModel createHeaderItem(int seasonNumber) {
        return new DetailScreenItemEntity(
                true,
                false,
                String.format("Season %d" + seasonNumber));
    }

    private DetailScreenItemModel createSeasonEpisodeItem(Episode episode) {
        return new DetailScreenItemEntity(
                false,
                false,
                String.format("Episode %d - %s (%s)", episode.getEpisodeNumber(), episode.getTitle(), episode.getAirDate()));
    }
}
