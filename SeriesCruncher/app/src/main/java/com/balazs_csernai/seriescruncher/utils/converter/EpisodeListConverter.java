package com.balazs_csernai.seriescruncher.utils.converter;

import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListItemEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListItemModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
@EpisodeList
public class EpisodeListConverter implements ModelConverter<EpisodeListModel, SeriesDetailsModel> {

    private final ModelConverter listItemConverter;

    @Inject
    public EpisodeListConverter(@EpisodeListItem ModelConverter listItemConverter) {
        this.listItemConverter = listItemConverter;
    }

    @Override
    public EpisodeListModel convert(SeriesDetailsModel seriesDetailsModel) {
        List<EpisodeListItemModel> items = new ArrayList<>();
        int currentSeason = -1;

        for (EpisodeModel episodeModel : seriesDetailsModel.getEpisodes()) {

            if (currentSeason != episodeModel.getSeasonNumber()) {

                items.add(new EpisodeListItemEntity()
                        .setEpisode(false)
                        .setExpanded(false)
                        .setText(String.format("S%02d",
                                episodeModel.getSeasonNumber())));

                currentSeason = episodeModel.getSeasonNumber();

            }

            items.add((EpisodeListItemModel) listItemConverter.convert(episodeModel));
        }

        return new EpisodeListEntity().setItems(items);
    }
}
