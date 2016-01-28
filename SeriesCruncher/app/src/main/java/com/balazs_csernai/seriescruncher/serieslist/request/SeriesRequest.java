package com.balazs_csernai.seriescruncher.serieslist.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeriesJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListEntity;

import java.util.Collections;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class SeriesRequest extends NetworkRequest<SeriesListEntity> {

    public SeriesRequest(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(SeriesListEntity.class, epGuideApi, omdbApi);
    }

    @Override
    public SeriesListEntity loadDataFromNetwork() throws Exception {
        List<SeriesJson> series = getEpGuideApi().loadSeries();

        Collections.sort(series);

        return new SeriesListEntity(series);
    }
}
