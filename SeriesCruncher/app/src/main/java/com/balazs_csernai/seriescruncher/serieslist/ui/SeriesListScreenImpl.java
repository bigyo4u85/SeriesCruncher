package com.balazs_csernai.seriescruncher.serieslist.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.rest.api.epguides.model.Series;
import com.balazs_csernai.seriescruncher.serieslist.model.SeriesListModel;
import com.balazs_csernai.seriescruncher.utils.common.ViewUtils;
import com.balazs_csernai.seriescruncher.utils.ui.DividerDecoration;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by ErikKramli on 2016.01.17..
 */
public class SeriesListScreenImpl implements SeriesListScreen, SeriesListAdapter.OnShowListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.series_progress)
    ProgressBar progressBar;

    @InjectView(R.id.series_recyclerview)
    RecyclerView seriesRecyclerView;

    @InjectView(R.id.filter_container)
    FrameLayout filterContainer;

    @InjectView(R.id.filter_edit)
    EditText seriesFilter;

    private final AppCompatActivity activity;
    private final SeriesListAdapter adapter;
    private Callbacks callbacks;

    @Inject
    public SeriesListScreenImpl(AppCompatActivity activity, Provider<SeriesListAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCreate(final Callbacks callbacks) {
        this.callbacks = callbacks;
        ButterKnife.inject(this, activity);

        activity.setSupportActionBar(toolbar);

        seriesRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        seriesRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources()));
        seriesRecyclerView.setAdapter(adapter);

        seriesFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                callbacks.onFilterTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void displayProgressIndicator() {
        ViewUtils.gone(seriesRecyclerView, filterContainer);
        ViewUtils.visible(progressBar);
    }

    @Override
    public void displaySeriesList(SeriesListModel model) {
        ViewUtils.gone(progressBar);
        ViewUtils.visible(seriesRecyclerView, filterContainer);

        adapter.setOnShowListener(this);
        updateSeriesList(model.getSeriesList());
    }

    @Override
    public void updateSeriesList(List<Series> series) {
        adapter.setItems(series);
    }

    @Override
    public void onSeriesSelected(Series series) {
        callbacks.onSeriesSelected(series);
    }
}
