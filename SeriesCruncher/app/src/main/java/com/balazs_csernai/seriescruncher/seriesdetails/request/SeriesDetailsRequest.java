package com.balazs_csernai.seriescruncher.seriesdetails.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.rest.api.model.CombinedEntity;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsEntity;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
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
    private String showName, imdbId;

    @Inject
    public SeriesDetailsRequest(EPGuideApi epGuideApi, OmdbApi omdbApi, @SeriesDetails ModelConverter converter) {
        super(SeriesDetailsEntity.class, epGuideApi, omdbApi);
        this.converter = converter;
    }

    public void setRequestParams(String showName, String imdbId) {
        this.showName = showName;
        this.imdbId = imdbId;
    }

    @Override
    public SeriesDetailsEntity loadDataFromNetwork() throws Exception {
        Map<Integer, List<EpisodeJson>> episodeMap = getEpGuideApi().loadSeriesDetails(showName);
        OmdbDetailsJson omdbDetails = getOmdbApi().getSeriesDetails(imdbId);

        CombinedEntity combined = new CombinedEntity()
                .setEpisodeMap(episodeMap)
                .setOmdbDetails(omdbDetails);



        return (SeriesDetailsEntity) converter.convert(combined);
    }
}
