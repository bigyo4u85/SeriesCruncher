package com.balazs_csernai.seriescruncher.rest.epguides;

import com.balazs_csernai.seriescruncher.rest.epguides.model.Show;
import com.balazs_csernai.seriescruncher.rest.epguides.model.ShowShort;

import javax.inject.Inject;

import retrofit.Callback;

/**
 * Created by Balazs_Csernai on 2016.01.08..
 */
public class EPGuideServiceImpl implements EPGuideService {

    private final EPGuideApi api;

    @Inject
    public EPGuideServiceImpl(EPGuideApi api) {
        this.api = api;
    }


    @Override
    public void loadShows(Callback<ShowShort[]> callback) {
        api.loadShows(callback);
    }

    @Override
    public void loadShow(String showName, Callback<Show> callback) {
        api.loadShow(showName, callback);
    }
}
