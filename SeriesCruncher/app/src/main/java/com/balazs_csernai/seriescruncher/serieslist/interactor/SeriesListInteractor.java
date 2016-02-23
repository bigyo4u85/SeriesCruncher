package com.balazs_csernai.seriescruncher.serieslist.interactor;

import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.SeriesJson;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;
import com.balazs_csernai.seriescruncher.rest.interactor.NetworkInteractor;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListEntity;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-02-22.
 */
public class SeriesListInteractor extends NetworkInteractor<SeriesListEntity> {

    @Inject
    public SeriesListInteractor(EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(epGuideApi, omdbApi);
    }

    @Override
    public Class<SeriesListEntity> getResultType() {
        return SeriesListEntity.class;
    }

    @Override
    public SeriesListEntity execute() {
        List<SeriesJson> series = getEpGuideApi().loadSeries();

        Collections.sort(series);

        return new SeriesListEntity(series);
    }
}
