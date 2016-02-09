package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.preferences.PreferenceHandler;
import com.balazs_csernai.seriescruncher.preferences.user.UserPreferencesModel;
import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.loader.Loader.Callback;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.utils.common.DateUtils;
import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.utils.navigator.SeriesDetailsNavigator;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter, SeriesDetailsScreen.Callbacks {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsNavigator navigator;
    private final SeriesDetailsScreen screen;
    private final ModelConverter converter;
    private final PreferenceHandler preference;
    private String seriesName;
    private String imdbId;
    private SeriesDetailsModel detailsModel;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader, SeriesDetailsNavigator navigator, SeriesDetailsScreen screen, @EpisodeList ModelConverter converter, PreferenceHandler preference) {
        this.seriesLoader = seriesLoader;
        this.navigator = navigator;
        this.screen = screen;
        this.converter = converter;
        this.preference = preference;
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
        int lastAiredEpisodeIndex = findLastAiredEpisodeIndex(detailsModel.getEpisodes());
        EpisodeModel nextEpisode = getEpisode(lastAiredEpisodeIndex - 1);
        if (nextEpisode != null) {
            screen.displayNextEpisode(nextEpisode);

        } else {
            EpisodeModel lastEpisode = getEpisode(lastAiredEpisodeIndex);
            screen.displayLastEpisode(lastEpisode);
        }
    }

    private EpisodeModel getEpisode(int episodeIndex) {
        try {
            return detailsModel.getEpisodes().get(episodeIndex);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    private int findLastAiredEpisodeIndex(List<EpisodeModel> episodes) {
        int index = -1;
        for (int i = 0, n = episodes.size(); i < n && index == -1; i++) {
            if (isLastEpisode(episodes.get(i))) {
                index = i;
            }
        }
        return index;
    }

    private boolean isLastEpisode(EpisodeModel episode) {
        Date airDate = episode.getAirDate();
        return airDate != null && DateUtils.getTodayDate().after(airDate);
    }

    @Override
    public void onFavorFabClicked() {
        UserPreferencesModel userPreferences = preference.getUserPreferences();
        if (isFavorite()) {
            userPreferences.removeSeriesFromFavorites(seriesName);
        } else {
            userPreferences.addSeriesToFavorites(seriesName);
        }
        preference.updateUserPreferences(userPreferences);
    }

    private boolean isFavorite() {
        return preference.getUserPreferences().getFavoredSeries().contains(seriesName);
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
