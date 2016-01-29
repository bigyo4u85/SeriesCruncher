package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.EpisodeListModel;
import com.balazs_csernai.seriescruncher.image.ImageLoader.ImageTarget;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsScreenImpl implements SeriesDetailsScreen {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.poster_img)
    ImageView poster;

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.episodes_recyclerview)
    RecyclerView episodesRecyclerView;

    private final AppCompatActivity activity;
    private final EpisodeAdapter adapter;

    @Inject
    public SeriesDetailsScreenImpl(AppCompatActivity activity, Provider<EpisodeAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCreate() {
        ButterKnife.inject(this, activity);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        episodesRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        episodesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources()));
        episodesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        episodesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void show(String text) {
        title.setText(text);
    }

    @Override
    public void show(EpisodeListModel episodes) {
        adapter.setItems(episodes);
    }

    @Override
    public ImageTarget getPosterImageTarget() {
        return new ImageTarget() {
            @Override
            public ImageView get() {
                return poster;
            }
        };
    }
}
