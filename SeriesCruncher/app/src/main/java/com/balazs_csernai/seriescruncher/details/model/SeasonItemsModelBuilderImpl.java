package com.balazs_csernai.seriescruncher.details.model;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemState.COLLAPSED;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemType.EPISODE;
import static com.balazs_csernai.seriescruncher.details.model.SeasonItemModel.ItemType.HEADER;

/**
 * Created by Balazs_Csernai on 2016.01.18..
 */
public class SeasonItemsModelBuilderImpl implements SeasonItemsModelBuilder {

    @Inject
    public SeasonItemsModelBuilderImpl() {
    }

    @Override
    public SeasonItemsModel empty() {
        return new SeasonItemsEntity(Collections.<SeasonItemModel>emptyList());
    }

    @Override
    public SeasonItemsModel build(Map<Integer, List<Episode>> seasonMap) {
        List<SeasonItemModel> items = new ArrayList<>(seasonMap.size() + seasonMap.keySet().size());
        for (Entry<Integer, List<Episode>> season : seasonMap.entrySet()) {
            items.add(createSeasonHeaderItem(items.size(), season.getKey()));
            for (Episode episode : season.getValue()) {
                items.add(createSeasonEpisodeItem(items.size(), season.getKey(), episode));
            }
        }
        return new SeasonItemsEntity(items);
    }

    private SeasonItemModel createSeasonHeaderItem(int position, int seasonNumber) {
        return new SeasonItemEntity(
                position,
                HEADER,
                COLLAPSED,
                "Season " + seasonNumber);
    }

    private SeasonItemModel createSeasonEpisodeItem(int position, int seasonNumber, Episode episode) {
        return new SeasonItemEntity(position,
                EPISODE,
                COLLAPSED,
                "Episode (" + (position - seasonNumber) + ") " + episode.getTitle() + " (" + episode.getAirDate() + ")");
    }
}
