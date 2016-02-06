package com.balazs_csernai.seriescruncher.seriesdetails.presenter;

import com.balazs_csernai.seriescruncher.rest.SeriesLoader;
import com.balazs_csernai.seriescruncher.rest.loader.Loader.Callback;
import com.balazs_csernai.seriescruncher.seriesdetails.model.SeriesDetailsModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.seriesdetails.ui.SeriesDetailsScreen;
import com.balazs_csernai.seriescruncher.utils.converter.EpisodeList;
import com.balazs_csernai.seriescruncher.utils.converter.ModelConverter;

import javax.inject.Inject;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsPresenterImpl implements SeriesDetailsPresenter {

    private final SeriesLoader seriesLoader;
    private final SeriesDetailsScreen screen;
    private final ModelConverter converter;

    @Inject
    public SeriesDetailsPresenterImpl(SeriesLoader seriesLoader, SeriesDetailsScreen screen, @EpisodeList ModelConverter converter) {
        this.seriesLoader = seriesLoader;
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
        screen.displayProgressIndicator();
        seriesLoader.loadDetails(seriesName, imdbId, seriesCallbacks);
    }

    @Override
    public void onStop() {
        seriesLoader.unbind();
    }

    SeriesDetailsModel detailsModel;

    private final Callback<SeriesDetailsModel> seriesCallbacks = new Callback<SeriesDetailsModel>() {

        @Override
        public void onSuccess(SeriesDetailsModel model) {
            screen.setTitle(model.getTitle());
            seriesLoader.loadPoster(model.getImageUrl(), posterCallbacks);
            detailsModel = model;
        }

        @Override
        public void onFailure() {
        }
    };

    private final Callback<PosterModel> posterCallbacks = new Callback<PosterModel>() {
        @Override
        public void onSuccess(PosterModel result) {
            screen.setColors(result.getPrimaryColor(), result.getSecondaryColor(), result.getAccentColor());
            screen.setPoster(result.getPoster());
            screen.setBackground(result.getPosterBackground());
            screen.displaySeriesDetails((EpisodeListModel) converter.convert(detailsModel));
        }

        @Override
        public void onFailure() {
        }
    };
}
