package com.balazs_csernai.seriescruncher.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.details.component.DetailsComponent;
import com.balazs_csernai.seriescruncher.details.controller.DetailsController;

import javax.inject.Inject;

/**
 * Created by ErikKramli on 2016.01.10..
 */
public class ShowDetailsActivity extends AppCompatActivity {

    @Inject
    DetailsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DetailsComponent.Injector.inject(this);
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
