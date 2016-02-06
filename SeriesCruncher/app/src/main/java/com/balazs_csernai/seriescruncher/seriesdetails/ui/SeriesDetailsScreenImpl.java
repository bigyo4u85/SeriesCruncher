package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;
import com.balazs_csernai.seriescruncher.utils.ui.SmartAppBarLayout;
import com.balazs_csernai.seriescruncher.utils.ui.SmartLayoutManager;
import com.balazs_csernai.seriescruncher.utils.ui.ViewUtils;
import com.balazs_csernai.seriescruncher.utils.ui.animation.Animation;
import com.balazs_csernai.seriescruncher.utils.ui.color.model.ColorModel;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsScreenImpl implements SeriesDetailsScreen, SmartAppBarLayout.AppBarChangeListener {

    @InjectView(R.id.details_progress)
    View progressBar;

    @InjectView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @InjectView(R.id.appbar)
    SmartAppBarLayout appbar;

    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.title)
    TextView title;

    @InjectView(R.id.poster)
    ImageView poster;

    @InjectView(R.id.background)
    ImageView background;

    @InjectView(R.id.episodes_recyclerview)
    RecyclerView episodesRecyclerView;

    @InjectView(R.id.favor_fab)
    FloatingActionButton favorFab;

    private final AppCompatActivity activity;
    private final EpisodeAdapter adapter;
    private final Animation animation;
    private SmartLayoutManager layoutManager;

    @Inject
    public SeriesDetailsScreenImpl(AppCompatActivity activity, Provider<EpisodeAdapter> adapterProvider, Animation animation) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
        this.animation = animation;
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
        episodesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources().getDrawable(R.drawable.line_divider)));
        episodesRecyclerView.setAdapter(adapter);
        episodesRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void displayProgressIndicator() {
        ViewUtils.gone(appbar, episodesRecyclerView, favorFab);
        ViewUtils.alpha(1f, progressBar);
        ViewUtils.visible(progressBar);
    }

    @Override
    public void displaySeriesDetails(final EpisodeListModel episodes) {
        adapter.setItems(episodes);
        animation.create()
                .fadeOut(progressBar)
                .fadeIn(appbar, episodesRecyclerView)
                .then()
                .reveal(favorFab)
                .play();
    }

    @Override
    public void setTitle(String text) {
        title.setText(text);
    }

    @Override
    public void setColors(ColorModel primaryColor, ColorModel secondaryColor, ColorModel accentColor) {
        coordinatorLayout.setBackgroundColor(primaryColor.getBackgroundColor());
        collapsingToolbar.setContentScrimColor(primaryColor.getBackgroundColor());
        title.setTextColor(primaryColor.getForegroundColor());

        Drawable backArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        backArrow.setColorFilter(primaryColor.getForegroundColor(), PorterDuff.Mode.SRC_IN);
        activity.getSupportActionBar().setHomeAsUpIndicator(backArrow);

        adapter.setColors(primaryColor, secondaryColor);
        favorFab.setBackgroundTintList(ColorStateList.valueOf(accentColor.getBackgroundColor()));
    }

    @Override
    public void setPoster(Bitmap bitmap) {
        poster.setImageBitmap(bitmap);
    }

    @Override
    public void setBackground(Bitmap bitmap) {
        background.setImageBitmap(bitmap);
    }

    @Override
    public void onAppBarCollapsed() {
        layoutManager.setVerticalScrollEnabled(true);
        animation.create().fadeIn(title).play();
    }

    @Override
    public void onAppBarExpanded() {
        layoutManager.setVerticalScrollEnabled(false);
        animation.create().fadeOut(title).play();
    }
}
