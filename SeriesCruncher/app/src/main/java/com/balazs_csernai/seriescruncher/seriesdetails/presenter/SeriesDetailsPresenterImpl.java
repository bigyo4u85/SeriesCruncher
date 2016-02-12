package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.preferences.Preferences;
import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
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
    private final Preferences preferences;
    private final EpisodeFinder episodeFinder;
    private String seriesName;
    private String imdbId;
    private SeriesDetailsModel detailsModel;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader,
                                      SeriesDetailsNavigator navigator,
                                      SeriesDetailsScreen screen,
                                      @EpisodeList ModelConverter converter,
                                      Preferences preferences,
                                      EpisodeFinder episodeFinder) {
        this.seriesLoader = seriesLoader;
        this.navigator = navigator;
        this.screen = screen;
        this.converter = converter;
        this.preferences = preferences;
        this.episodeFinder = episodeFinder;
    }

    @Override
    public void onStart() {
        screen.onCreate(this);
        seriesLoader.bind();
    }

    @Override
    public void loadSeriesDetails(String seriesName, String imdbId) {
        this.seriesName = seriesName;
        this.imdbId = imdbId;
        screen.displayProgressIndicator();
        seriesLoader.loadDetails(seriesName, imdbId, seriesCallbacks);
        screen.setAsFavorite(isFavorite());
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
        UserPreferencesModel userPreferences = preferences.getUserPreferences();
        if (isFavorite()) {
            userPreferences.removeSeriesFromFavorites(seriesName);
        } else {
            userPreferences.addSeriesToFavorites(seriesName);
        }
        preferences.updateUserPreferences(userPreferences);
    }

    private boolean isFavorite() {
        return preferences.getUserPreferences().getFavoredSeries().contains(seriesName);
    }

    @Override
    public void onNetworkErrorRetry() {
        loadSeriesDetails(seriesName, imdbId);
    }

    @Override
    public void onNetworkErrorCancel() {
        navigator.closeSeriesDetails();
    }
}
