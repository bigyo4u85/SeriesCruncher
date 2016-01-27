package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
import com.balazs_csernai.seriescruncher.utils.common.ViewUtils;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListScreenImpl implements SeriesListScreen, SeriesListAdapter.OnShowListener {

    @InjectView(R.id.series_progress)
    ProgressBar progressBar;

    @InjectView(R.id.series_recyclerview)
    RecyclerView seriesRecyclerView;

    private final Activity activity;
    private final SeriesListAdapter adapter;
    private Callbacks callbacks;

    @Inject
    public SeriesListScreenImpl(Activity activity, Provider<SeriesListAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCreate(Callbacks callbacks) {
        this.callbacks = callbacks;
        ButterKnife.inject(this, activity);
        seriesRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        seriesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources()));
        seriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displayProgressIndicator() {
        ViewUtils.gone(seriesRecyclerView);
        ViewUtils.visible(progressBar);
    }

    @Override
    public void displaySeriesList(SeriesListModel model) {
        ViewUtils.gone(progressBar);
        ViewUtils.visible(seriesRecyclerView);

        adapter.setOnShowListener(this);
        adapter.setItems(model.getSeriesList());
    }

    @Override
    public void onSeriesSelected(Series series) {
        callbacks.onSeriesSelected(series);
    }
}
