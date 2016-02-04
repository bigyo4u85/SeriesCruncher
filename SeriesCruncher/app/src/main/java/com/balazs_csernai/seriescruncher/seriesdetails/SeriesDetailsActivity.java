package com.balazs_csernai.seriescruncher.seriesdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.seriesdetails.component.SeriesDetailsComponent;
import com.balazs_csernai.seriescruncher.seriesdetails.presenter.SeriesDetailsPresenter;
import com.balazs_csernai.seriescruncher.utils.common.IntentUtils;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class SeriesDetailsActivity extends AppCompatActivity {

    private static final String ARG_SERIES_NAME = "series-name";
    private static final String ARG_IMDB_ID = "imdb-id";

    @Inject
    SeriesDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_details);
        SeriesDetailsComponent.Injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
        String seriesName = IntentUtils.getStringArgOrThrow(getIntent(), ARG_SERIES_NAME);
        String imdbId = IntentUtils.getStringArgOrThrow(getIntent(), ARG_IMDB_ID);
        presenter.loadSeriesDetails(seriesName, imdbId);
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public static Intent createLaunchIntent(Context context, String showName, String imdbId) {
        Intent intent = new Intent(context, SeriesDetailsActivity.class);
        intent.putExtra(ARG_SERIES_NAME, showName);
        intent.putExtra(ARG_IMDB_ID, imdbId);
        return intent;
    }
}
