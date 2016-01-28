package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsScreenImpl implements SeriesDetailsScreen {

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.episodes_recyclerview)
    RecyclerView episodesRecyclerView;

    private final Activity activity;
    private final EpisodeAdapter adapter;

    @Inject
    public SeriesDetailsScreenImpl(Activity activity, Provider<EpisodeAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCreate() {
        ButterKnife.inject(this, activity);
        episodesRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        episodesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources()));
        episodesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        episodesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void show(String url) {
        title.setText(url);
    }

    @Override
    public void show(EpisodeListModel episodes) {
        adapter.setItems(episodes);
    }
}
