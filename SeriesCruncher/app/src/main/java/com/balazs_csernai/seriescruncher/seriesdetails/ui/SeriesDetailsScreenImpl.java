package com.balazs_csernai.seriescruncher.seriesdetails.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.model.episode.EpisodeListModel;
import com.balazs_csernai.seriescruncher.utils.ui.ViewUtils;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;
import com.balazs_csernai.seriescruncher.utils.ui.SmartAppBarLayout;
import com.balazs_csernai.seriescruncher.utils.ui.SmartLayoutManager;

import java.util.ArrayList;
import java.util.List;

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
        episodesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources().getDrawable(R.drawable.line_divider)));
        episodesRecyclerView.setAdapter(adapter);
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

        ViewUtils.alpha(0f, appbar, episodesRecyclerView, favorFab);
        ViewUtils.visible(appbar, episodesRecyclerView, favorFab);


        AnimatorSet contentAnimatorSet = new AnimatorSet();
        contentAnimatorSet.setDuration(1000);
        contentAnimatorSet.playTogether(
                createDetailsShowAnimators(appbar, episodesRecyclerView)
        );
        contentAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ViewUtils.gone(progressBar);
            }
        });


        AnimatorSet fabAnimatorSet = new AnimatorSet();
        fabAnimatorSet.setInterpolator(new OvershootInterpolator());
        fabAnimatorSet.setDuration(300);
        fabAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(favorFab, View.ALPHA, 0f, 1f),
                ObjectAnimator.ofFloat(favorFab, View.SCALE_X, 0f, 1f),
                ObjectAnimator.ofFloat(favorFab, View.SCALE_Y, 0f, 1f)
        );

        AnimatorSet set = new AnimatorSet();
        set.playSequentially(contentAnimatorSet, fabAnimatorSet);
        set.start();
    }

    private List<Animator> createDetailsShowAnimators(View... views) {
        List<Animator> animators = new ArrayList<>(views.length);
        for (View v : views) {
            animators.add( ObjectAnimator.ofFloat(v, View.ALPHA, 0f, 1f) );
        }
        animators.add( ObjectAnimator.ofFloat(progressBar, View.ALPHA, 1f, 0f) );
        return animators;
    }


    @Override
    public void setTitle(String text) {
        title.setText(text);
    }

    @Override
    public void setColors(int primaryBackgroundColor, int secondaryBackgroundColor, int primaryTextColor, int secondaryTextColor) {
        coordinatorLayout.setBackgroundColor(primaryBackgroundColor);
        collapsingToolbar.setContentScrimColor(primaryBackgroundColor);
        title.setTextColor(primaryTextColor);

        Drawable backArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        backArrow.setColorFilter(primaryTextColor, PorterDuff.Mode.SRC_IN);
        activity.getSupportActionBar().setHomeAsUpIndicator(backArrow);

        adapter.setColors(primaryBackgroundColor, secondaryBackgroundColor, primaryTextColor, secondaryTextColor);
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
