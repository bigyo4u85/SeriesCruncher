package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.ShowDetailsActivity;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesModel;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListScreenImpl implements SeriesListScreen {

    @InjectView(R.id.series_recyclerview)
    RecyclerView seriesRecyclerView;

    private final Activity activity;
    private final SeriesAdapter adapter;

    @Inject
    public SeriesListScreenImpl(Activity activity, Provider<SeriesAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCrate() {
        ButterKnife.inject(this, activity);
        seriesRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        seriesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void displaySeries(SeriesModel model) {
        adapter.setItems(model.getSeries());
    }

    @OnClick(R.id.to_details_btn)
    void onToDetailsButtonClicked() {
        activity.startActivity(new Intent(activity, ShowDetailsActivity.class));
    }
}
