package com.balazs_csernai.seriescruncher.details.ui;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.image.ImageLoader;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Erik_Markus_Kramli on 2016-01-13.
 */
public class SeriesDetailsScreenImpl implements SeriesDetailsScreen {

    @InjectView(R.id.header_img)
    ImageView headerImg;

    @InjectView(R.id.title)
    TextView title;

    private final Activity activity;
    private final ImageLoader imageLoader;

    @Inject
    public SeriesDetailsScreenImpl(Activity activity, ImageLoader imageLoader) {
        this.activity = activity;
        this.imageLoader = imageLoader;
    }

    @Override
    public void onCreate() {
        ButterKnife.inject(this, activity);
    }

    @Override
    public void show(String url) {
        imageLoader.load(url, headerImg);
        title.setText(url);
    }
}
