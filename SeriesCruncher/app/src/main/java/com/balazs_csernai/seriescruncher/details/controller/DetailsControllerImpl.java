package com.balazs_csernai.seriescruncher.details.controller;

import com.balazs_csernai.seriescruncher.details.model.ShowDetailsModel;
import com.balazs_csernai.seriescruncher.details.ui.DetailsScreen;
import com.balazs_csernai.seriescruncher.rest.RestService;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class DetailsControllerImpl implements DetailsController, RestService.Callback<ShowDetailsModel> {

    private final RestService restService;
    private final DetailsScreen screen;

    @Inject
    public DetailsControllerImpl(RestService restService, DetailsScreen screen) {
        this.restService = restService;
        this.screen = screen;
    }

    @Override
    public void onStart() {
        screen.onCreate();
        restService.bind();
        restService.loadDetails("bigbangtheory", "tt0898266", this);
    }

    @Override
    public void onStop() {
        restService.unbind();
    }

    @Override
    public void onSuccess(ShowDetailsModel result) {
        String text = result.getSeasonMap().get(1).get(0).getTitle() + "\n" + result.getOmdbDetails().getPosterUrl();

        screen.show("loaded \n " + text);
    }

    @Override
    public void onFailure() {

    }
}
