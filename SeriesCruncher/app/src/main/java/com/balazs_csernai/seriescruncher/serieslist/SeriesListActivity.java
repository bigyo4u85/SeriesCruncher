package com.balazs_csernai.seriescruncher.serieslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.serieslist.controller.SeriesListController;

import javax.inject.Inject;

import static com.balazs_csernai.seriescruncher.serieslist.component.SeriesListComponent.Injector.inject;

public class SeriesListActivity extends AppCompatActivity {

    @Inject
    SeriesListController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.onStart();
    }

    @Override
    protected void onStop() {
        controller.onStop();
        super.onStop();
    }
}
