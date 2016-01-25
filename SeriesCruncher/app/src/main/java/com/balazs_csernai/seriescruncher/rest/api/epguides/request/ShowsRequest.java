package com.balazs_csernai.seriescruncher.rest.api.epguides.request;

import com.balazs_csernai.seriescruncher.model.ShowModel;
import com.balazs_csernai.seriescruncher.model.ShowsEntity;
import com.balazs_csernai.seriescruncher.rest.api.epguides.EPGuideApi;
import com.balazs_csernai.seriescruncher.rest.api.epguides.converter.ShowConverter;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.ShowJSON;
import com.balazs_csernai.seriescruncher.rest.api.omdb.OmdbApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class ShowsRequest extends NetworkRequest<ShowsEntity> {

    private final ShowConverter converter;

    public ShowsRequest(ShowConverter converter, EPGuideApi epGuideApi, OmdbApi omdbApi) {
        super(ShowsEntity.class, epGuideApi, omdbApi);
        this.converter = converter;
    }

    @Override
    public ShowsEntity loadDataFromNetwork() throws Exception {
        List<ShowJSON> epGuideShows = getEpGuideService().loadShows();
        List<ShowModel> shows = new ArrayList<>(epGuideShows.size());

        for (ShowJSON epGuideShow : epGuideShows) {
            shows.add(converter.convert(epGuideShow));
        }

        return new ShowsEntity(shows);
    }
}
