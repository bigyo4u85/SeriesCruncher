package com.balazs_csernai.seriescruncher.showlist.presenter;

import com.balazs_csernai.seriescruncher.model.ShowModel;
import com.balazs_csernai.seriescruncher.model.ShowsModel;
import com.balazs_csernai.seriescruncher.rest.api.epguides.loader.DataLoader;
import com.balazs_csernai.seriescruncher.rest.loader.Loader;
import com.balazs_csernai.seriescruncher.showlist.ui.ShowListScreen;
import com.balazs_csernai.seriescruncher.utils.navigator.SeriesListNavigator;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class ShowListPresenterImpl implements ShowListPresenter, Loader.Callback<ShowsModel>, ShowListScreen.Callbacks {

    private final DataLoader loader;
    private final ShowListScreen screen;
    private final SeriesListNavigator navigator;

    @Inject
    public ShowListPresenterImpl(DataLoader loader, ShowListScreen screen, SeriesListNavigator seriesListNavigator) {
        this.loader = loader;
        this.screen = screen;
        this.navigator = seriesListNavigator;
    }

    @Override
    public void onStart() {
        screen.onCreate(this);
        loader.bind();

        screen.displayProgressIndicator();
        loader.loadShows(this);
    }

    @Override
    public void onStop() {
        loader.unbind();
    }

    @Override
    public void onSuccess(ShowsModel series) {
        screen.displaySeriesList(series);
    }

    @Override
    public void onFailure() {
        // TODO: Show error message
    }

    @Override
    public void onShowSelected(ShowModel show) {
        navigator.launchSeriesDetails(show.getName(), show.getImdbId());
    }
}
