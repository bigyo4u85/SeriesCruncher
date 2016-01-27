package com.balazs_csernai.seriescruncher.seriesdetails.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.EpisodeJson;
import com.balazs_csernai.seriescruncher.utils.converter.SeriesDetails;
import com.balazs_csernai.seriescruncher.rest.api.model.CombinedEntity;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.omdb.model.OmdbDetailsJson;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;

import java.util.List;
import java.util.Map;

/**
 * Created by ErikKramli on 2016.01.11..
 */
public class SeriesDetailsRequest extends NetworkRequest<SeriesDetailsModel> {

    private final String showName, imdbId;
    private final ModelConverter converter;

    public SeriesDetailsRequest(String showName, String imdbId, EPGuideApi epGuideApi, OmdbApi omdbApi, @SeriesDetails ModelConverter converter) {
        super(SeriesDetailsModel.class, epGuideApi, omdbApi);
        this.showName = showName;
        this.imdbId = imdbId;
        this.converter = converter;
    }

    @Override
    public SeriesDetailsModel loadDataFromNetwork() throws Exception {
        Map<Integer, List<EpisodeJson>> episodeMap = getEpGuideApi().loadSeriesDetails(showName);
        OmdbDetailsJson omdbDetails = getOmdbApi().getSeriesDetails(imdbId);

        CombinedEntity combined = new CombinedEntity()
                .setEpisodeMap(episodeMap)
                .setOmdbDetails(omdbDetails);

        return (SeriesDetailsModel) converter.convert(combined);
    }
}
