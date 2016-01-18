package com.balazs_csernai.seriescruncher.serieslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.serieslist.presenter.SeriesListPresenter;

import javax.inject.Inject;

import static com.balazs_csernai.seriescruncher.serieslist.component.SeriesListComponent.Injector.inject;

public class SeriesListActivity extends AppCompatActivity {

    @Inject
    SeriesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }
}
