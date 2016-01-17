package com.balazs_csernai.seriescruncher.serieslist.request;

import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.request.NetworkRequest;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesEntity;

import java.util.Collections;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class SeriesRequest extends NetworkRequest<SeriesEntity> {

    public SeriesRequest(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(SeriesEntity.class, epGuideApi, omdbApi);
    }

    @Override
    public SeriesEntity loadDataFromNetwork() throws Exception {
        List<ShowJson> series = getEpGuideService().loadSeries();

        Collections.sort(series);

        return new SeriesEntity(series);
    }
}
