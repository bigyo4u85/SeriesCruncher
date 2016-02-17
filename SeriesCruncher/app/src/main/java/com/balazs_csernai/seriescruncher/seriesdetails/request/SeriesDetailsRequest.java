package com.balazs_csernai.seriescruncher.seriesdetails.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Episode;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.model.CombinedEntity;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsEntity;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.utils.converter.SeriesDetails;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class SeriesDetailsRequest extends NetworkRequest<SeriesDetailsEntity> {

    private final ModelConverter converter;
    private String showName;

    @Inject
    public SeriesDetailsRequest(EPGuideApi epGuideApi, OmdbApi omdbApi, @SeriesDetails ModelConverter converter) {
        super(SeriesDetailsEntity.class, epGuideApi, omdbApi);
        this.converter = converter;
    }

    public void setRequestParams(String showName) {
        this.showName = showName;
    }

    @Override
    public SeriesDetailsEntity loadDataFromNetwork() throws Exception {
        Map<Integer, List<EpisodeJson>> seasonMap = getEpGuideApi().loadSeriesDetails(showName);

        String imdbId = findImdbId(seasonMap);

        OmdbDetailsJson omdbDetails = null;
        if (imdbId != null) {
            omdbDetails = getOmdbApi().getSeriesDetails(imdbId);
        }

        CombinedEntity combined = new CombinedEntity()
                .setSeasonMap(seasonMap)
                .setOmdbDetails(omdbDetails);

        return (SeriesDetailsEntity) converter.convert(combined);
    }

    private String findImdbId(final Map<Integer, List<EpisodeJson>> seasonMap) {
        for (Integer seasonKey : seasonMap.keySet()) {
            for (Episode episode : seasonMap.get(seasonKey)) {
                String imdbId = episode.getSeries().getImdbId();
                if (imdbId != null && imdbId.length() > 0) {
                    return imdbId;
                }
            }
        }
        return null;
    }
}
