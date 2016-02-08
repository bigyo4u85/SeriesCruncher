package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.loader.Loader.Callback;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;
import com.balazs_csernai.seriescruncher.utils.navigator.SeriesDetailsNavigator;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsNavigator navigator;
    private final SeriesDetailsScreen screen;
    private final ModelConverter converter;
    private String seriesName;
    private String imdbId;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader, SeriesDetailsNavigator navigator, SeriesDetailsScreen screen, @EpisodeList ModelConverter converter) {
        this.seriesLoader = seriesLoader;
        this.navigator = navigator;
        this.screen = screen;
        this.converter = converter;
    }

    @Override
    public void onStart() {
        screen.onCreate();
        seriesLoader.bind();
    }

    @Override
    public void loadSeriesDetails(String seriesName, String imdbId) {
        this.seriesName = seriesName;
        this.imdbId = imdbId;
        seriesLoader.loadDetails(seriesName, imdbId, seriesCallbacks);
    }

    @Override
    public void onStop() {
        seriesLoader.unbind();
    }

    private final Callback<SeriesDetailsModel> seriesCallbacks = new Callback<SeriesDetailsModel>() {

        @Override
        public void onSuccess(SeriesDetailsModel model) {
            screen.setTitle(model.getTitle());
            screen.setEpisodes((EpisodeListModel) converter.convert(model));
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
            screen.setColors(result.getPrimaryBackgroundColor(), result.getSecondaryBackgroundColor(), result.getPrimaryTextColor(), result.getSecondaryTextColor());
            screen.setPoster(result.getPoster());
            screen.setBackground(result.getPosterBackground());
        }

        @Override
        public void onFailure() {
            screen.showNetworkErrorDialog();
        }
    };

    @Override
    public void onNetworkErrorRetry() {
        loadSeriesDetails(seriesName, imdbId);
    }

    @Override
    public void onNetworkErrorCancel() {
        navigator.closeSeriesDetails();
    }
}
