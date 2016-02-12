package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.notification.FavoriteSeriesHandler;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader.LoadType;
import com.balazs_csernai.seriescruncher.rest.loader.Loader.Callback;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.finder.EpisodeFinder;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.utils.navigator.SeriesDetailsNavigator;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter, SeriesDetailsScreen.Callbacks {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsNavigator navigator;
    private final SeriesDetailsScreen screen;
    private final ModelConverter converter;
    private final FavoriteSeriesHandler favoriteHandler;
    private final EpisodeFinder episodeFinder;

    private String seriesName;
    private SeriesDetailsModel detailsModel;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader,
                                      SeriesDetailsNavigator navigator,
                                      SeriesDetailsScreen screen,
                                      @EpisodeList ModelConverter converter,
                                      FavoriteSeriesHandler favoriteHandler,
                                      EpisodeFinder episodeFinder) {
        this.seriesLoader = seriesLoader;
        this.navigator = navigator;
        this.screen = screen;
        this.converter = converter;
        this.favoriteHandler = favoriteHandler;
        this.episodeFinder = episodeFinder;
    }

    @Override
    public void onStart() {
        screen.onCreate(this);
        seriesLoader.bind();
    }

    @Override
    public void loadSeriesDetails(String seriesName) {
        this.seriesName = seriesName;
        screen.displayProgressIndicator();
        seriesLoader.loadDetails(seriesName, LoadType.DEFAULT, seriesCallbacks);
        screen.setAsFavorite(favoriteHandler.isFavorite(seriesName));
    }

    @Override
    public void onStop() {
        seriesLoader.unbind();
    }

    private final Callback<SeriesDetailsModel> seriesCallbacks = new Callback<SeriesDetailsModel>() {

        @Override
        public void onSuccess(SeriesDetailsModel model) {
            detailsModel = model;
            episodeFinder.setEpisodes(model.getEpisodes());
            seriesLoader.loadPoster(model.getImageUrl(), posterCallbacks);
        }

        @Override
        public void onFailure() {
            screen.showNetworkErrorDialog();
        }
    };

    private final Callback<PosterModel> posterCallbacks = new Callback<PosterModel>() {
        @Override
        public void onSuccess(PosterModel result) {
            screen.setTitle(detailsModel.getTitle());
            screen.setColors(result.getPrimaryColor(), result.getSecondaryColor(), result.getAccentColor());
            screen.setPoster(result.getPoster());
            screen.setBackground(result.getPosterBackground());
            screen.displaySeriesDetails((EpisodeListModel) converter.convert(detailsModel));
            displayNextOrLastEpisode();
        }

        @Override
        public void onFailure() {
            screen.showNetworkErrorDialog();
        }
    };

    private void displayNextOrLastEpisode() {
        if (episodeFinder.hasNextEpisode()) {
            screen.displayNextEpisode(episodeFinder.getNextEpisode());
        } else {
            screen.displayLastEpisode(episodeFinder.getLastEpisode());
        }
    }

    @Override
    public void onFavorFabClicked() {
        if (favoriteHandler.isFavorite(seriesName)) {
            favoriteHandler.removeFromFavorites(seriesName);
        } else {
            favoriteHandler.addToFavorites(seriesName);
        }
    }

    @Override
    public void onNetworkErrorRetry() {
        loadSeriesDetails(seriesName);
    }

    @Override
    public void onNetworkErrorCancel() {
        navigator.closeSeriesDetails();
    }
}
