package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.poster.PosterModel;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;
import com.balazs_csernai.seriescruncher.utils.ui.SmartAppBarLayout;
import com.balazs_csernai.seriescruncher.utils.ui.SmartLayoutManager;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsScreenImpl implements SeriesDetailsScreen, SmartAppBarLayout.AppBarChangeListener {

    @InjectView(R.id.appbar)
    SmartAppBarLayout appbar;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.poster_img)
    ImageView poster;

    @InjectView(R.id.episodes_recyclerview)
    RecyclerView episodesRecyclerView;

    private final AppCompatActivity activity;
    private final EpisodeAdapter adapter;
    private SmartLayoutManager layoutManager;

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
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        appbar.setOnAppBarChangeListener(this);

        layoutManager = new SmartLayoutManager(activity);
        episodesRecyclerView.setLayoutManager(layoutManager);
        episodesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources()));
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
    public void show(PosterModel posterModel) {
        poster.setImageBitmap(posterModel.getPoster());
        collapsingToolbar.setContentScrimColor(posterModel.getBackgroundColor());
        title.setTextColor(posterModel.getTextColor());

        Drawable backArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        backArrow.setColorFilter(posterModel.getTextColor(), PorterDuff.Mode.SRC_IN);
        activity.getSupportActionBar().setHomeAsUpIndicator(backArrow);
    }

    @Override
    public void onAppBarCollapsed() {
        layoutManager.setVerticalScrollEnabled(true);
        animateTitle(1);
    }

    @Override
    public void onAppBarExpanded() {
        layoutManager.setVerticalScrollEnabled(false);
        animateTitle(0);
    }

    private void animateTitle(float toAlpha) {
        title.animate().alpha(toAlpha).start();
    }
}
