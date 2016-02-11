package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeModel;
import com.balazs_csernai.seriescruncher.utils.dialog.DialogFactory;
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
import butterknife.OnClick;

import static com.balazs_csernai.seriescruncher.utils.ui.DrawableUtil.getColorizedDrawable;

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

    @InjectView(R.id.details_container)
    LinearLayout detailsContainer;

    @InjectView(R.id.next_or_last_episode_title)
    TextView nextOrLastEpisodeTitle;

    @InjectView(R.id.next_or_last_episode_air_date)
    TextView nextOrLastEpisodeAirDate;

    @InjectView(R.id.episodes_recyclerview)
    RecyclerView episodesRecyclerView;

    @InjectView(R.id.favor_fab)
    FloatingActionButton favorFab;

    private final AppCompatActivity activity;
    private final EpisodeAdapter adapter;
    private final DialogFactory dialogFactory;
    private final Animation animation;
    private Callbacks callbacks;
    private SmartLayoutManager layoutManager;
    private boolean isFavorite;

    @Inject
    public SeriesDetailsScreenImpl(AppCompatActivity activity, Provider<EpisodeAdapter> adapterProvider, DialogFactory dialogFactory, Animation animation) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
        this.dialogFactory = dialogFactory;
        this.animation = animation;
    }

    @Override
    public void onCreate(Callbacks callbacks) {
        this.callbacks = callbacks;
        ButterKnife.inject(this, activity);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        appbar.setOnAppBarChangeListener(this);

        layoutManager = new SmartLayoutManager(activity);
        episodesRecyclerView.setLayoutManager(layoutManager);
        episodesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources().getColor(R.color.light_gray)));
        episodesRecyclerView.setAdapter(adapter);
        episodesRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void displayProgressIndicator() {
        ViewUtils.gone(appbar, detailsContainer, favorFab);
        ViewUtils.alpha(1f, progressBar);
        ViewUtils.visible(progressBar);
    }

    @Override
    public void displaySeriesDetails(final EpisodeListModel episodes) {
        adapter.setItems(episodes);
        float initialFavorFabRotation = isFavorite ? 180 : 0;
        animation.create()
                .fadeOut(progressBar)
                .fadeIn(appbar, detailsContainer)
                .then()
                .reveal(favorFab)
                .then()
                .rotate(initialFavorFabRotation, favorFab)
                .play();
    }

    @Override
    public void displayNextEpisode(EpisodeModel episode) {
        nextOrLastEpisodeAirDate.setText(getText(R.string.air_date, episode.getAirDate()));
        nextOrLastEpisodeTitle.setText(getText(R.string.next_episode, episode.getSeasonNumber(), episode.getEpisodeNumber(), episode.getTitle()));
    }

    @Override
    public void displayLastEpisode(EpisodeModel episode) {
        nextOrLastEpisodeTitle.setText(getText(R.string.last_episode, episode.getSeasonNumber(), episode.getEpisodeNumber(), episode.getTitle()));
        nextOrLastEpisodeAirDate.setText(getText(R.string.aired_at, episode.getAirDate()));
    }

    private Spanned getText(@StringRes int res, Object... formatArgs) {
        return Html.fromHtml(activity.getResources().getString(res, formatArgs));
    }

    @Override
    public void setTitle(String text) {
        title.setText(text);
    }

    @Override
    public void setColors(ColorModel primaryColor, ColorModel secondaryColor, ColorModel accentColor) {
        collapsingToolbar.setContentScrimColor(primaryColor.getBackgroundColor());

        title.setTextColor(primaryColor.getForegroundColor());
        activity.getSupportActionBar().setHomeAsUpIndicator(getColorizedDrawable(activity.getResources(), R.drawable.abc_ic_ab_back_mtrl_am_alpha, primaryColor.getForegroundColor()));

        detailsContainer.setBackgroundColor(primaryColor.getBackgroundColor());
        nextOrLastEpisodeTitle.setTextColor(primaryColor.getForegroundColor());
        nextOrLastEpisodeAirDate.setTextColor(primaryColor.getForegroundColor());

        adapter.setColors(primaryColor, secondaryColor);

        favorFab.setBackgroundTintList(ColorStateList.valueOf(accentColor.getBackgroundColor()));
        favorFab.setImageDrawable(getColorizedDrawable(activity.getResources(), R.drawable.ic_thumb_up, accentColor.getForegroundColor()));
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
    public void setAsFavorite(boolean favorite) {
        this.isFavorite = favorite;
    }

    @Override
    public void showNetworkErrorDialog() {
        dialogFactory.createNetworkError();
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

    @OnClick(R.id.favor_fab)
    void onFavorFabClicked() {
        animation.create().rotate(180, favorFab).play();
        callbacks.onFavorFabClicked();
    }
}
