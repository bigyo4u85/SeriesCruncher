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
    public EpisodeListModel adapterModel;

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
            adapterModel = (EpisodeListModel) converter.convert(model);
            seriesLoader.loadPoster(model.getImageUrl(), posterCallbacks);
        }

        @Override
        public void onFailure() {
        }
    };

    private final Callback<PosterModel> posterCallbacks = new Callback<PosterModel>() {
        @Override
        public void onSuccess(PosterModel result) {
            screen.setPrimaryBackgroundColor(result.getPrimaryBackgroundColor());
            screen.setPrimaryTextColor(result.getPrimaryTextColor());
            screen.setSecondaryBackgroundColor(result.getSecondaryBackgroundColor());
            screen.setSecondaryTextColor(result.getSecondaryTextColor());
            screen.setEpisodes(adapterModel);
            screen.setPoster(result.getPoster());
            screen.setBackground(result.getPosterBackground());
        }

        @Override
        public void onFailure() {
        }
    };
}
