package com.balazs_csernai.seriescruncher.utils.converter;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.rest.api.model.CombinedModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeComparator;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.01.27..
 */
@SeriesDetails
public class SeriesDetailsConverter implements ModelConverter<SeriesDetailsModel, CombinedModel> {

    private final ModelConverter episodeDetailsConverter;

    @Inject
    public SeriesDetailsConverter(@EpisodeDetails ModelConverter episodeDetailsConverter) {
        this.episodeDetailsConverter = episodeDetailsConverter;
    }

    @Override
    public SeriesDetailsModel convert(CombinedModel model) {
        Series series = null;
        List<EpisodeModel> episodes = new ArrayList<>();

        for (Integer seasonKey : model.getEpisodeMap().keySet()) {
            for (Episode episode : model.getEpisodeMap().get(seasonKey)) {
                if (series == null) {
                    series = episode.getSeries();
                }
                episodes.add((EpisodeModel) episodeDetailsConverter.convert(episode));
            }
        }

        Collections.sort(episodes, new EpisodeComparator());

        return new SeriesDetailsEntity()
                .setTitle(series.getTitle())
                .setName(series.getName())
                .setImdbId(series.getImdbId())
                .setImageUrl(model.getOmdbDetails().getPosterUrl())
                .setEpisodes(episodes);
    }
}
