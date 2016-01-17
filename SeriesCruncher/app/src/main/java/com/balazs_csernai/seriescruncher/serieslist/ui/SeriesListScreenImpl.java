package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.ShowDetailsActivity;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Show;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListScreenImpl implements SeriesListScreen, SeriesAdapter.OnShowListener {

    @InjectView(R.id.series_recyclerview)
    RecyclerView seriesRecyclerView;

    private final Activity activity;
    private final SeriesAdapter adapter;
    private Callbacks callbacks;

    @Inject
    public SeriesListScreenImpl(Activity activity, Provider<SeriesAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCrate(Callbacks callbacks) {
        this.callbacks = callbacks;
        ButterKnife.inject(this, activity);
        seriesRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        seriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displaySeries(SeriesModel model) {
        adapter.setOnShowListener(this);
        adapter.setItems(model.getSeries());
    }

    @OnClick(R.id.to_details_btn)
    void onToDetailsButtonClicked() {
        activity.startActivity(new Intent(activity, ShowDetailsActivity.class));
    }

    @Override
    public void onShowTapped(Show show) {
        callbacks.onShowTapped(show);

        //todo: remove
        activity.startActivity(ShowDetailsActivity.createLaunchIntent(activity, show.getEpGuideName(), show.getImdbId()));
    }
}
