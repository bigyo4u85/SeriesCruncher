package com.balazs_csernai.seriescruncher.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.component.DetailsComponent;
import com.balazs_csernai.seriescruncher.details.controller.DetailsController;
import com.balazs_csernai.seriescruncher.utils.common.IntentUtils;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class ShowDetailsActivity extends AppCompatActivity {

    private static final String ARG_SHOW_NAME = "show-name";
    private static final String ARG_IMDB_ID = "imdb-id";

    @Inject
    DetailsController controller;

    private String showName, imdbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        DetailsComponent.Injector.inject(this);

        showName = IntentUtils.getStringArgOrThrow(getIntent(), ARG_SHOW_NAME);
        imdbId = IntentUtils.getStringArgOrThrow(getIntent(), ARG_IMDB_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.onStart();
        controller.loadShowDetails(showName, imdbId);
    }

    @Override
    protected void onStop() {
        controller.onStop();
        super.onStop();
    }

    public static Intent createLaunchIntent(Context context, String showName, String imdbId) {
        Intent intent = new Intent(context, ShowDetailsActivity.class);
        intent.putExtra(ARG_SHOW_NAME, showName);
        intent.putExtra(ARG_IMDB_ID, imdbId);
        return intent;
    }
}
