package com.balazs_csernai.seriescruncher.details.ui;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.model.ShowModel;
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

    @InjectView(R.id.seasons_recyclerview)
    RecyclerView seasonsRecyclerView;

    private final Activity activity;
    private final SeasonsAdapter adapter;

    @Inject
    public SeriesDetailsScreenImpl(Activity activity, Provider<SeasonsAdapter> adapterProvider) {
        this.activity = activity;
        this.adapter = adapterProvider.get();
    }

    @Override
    public void onCreate() {
        ButterKnife.inject(this, activity);
        seasonsRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        seasonsRecyclerView.addItemDecoration(new DividerDecoration(activity.getResources()));
        seasonsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void show(String url) {
        title.setText(url);
    }

    @Override
    public void show(ShowModel model) {
        adapter.setItems(model.getSeasons());
    }
}
