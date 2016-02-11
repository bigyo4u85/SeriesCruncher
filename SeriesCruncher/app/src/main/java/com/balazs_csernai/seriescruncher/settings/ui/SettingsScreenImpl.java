package com.balazs_csernai.seriescruncher.settings.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.balazs_csernai.seriescruncher.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
public class SettingsScreenImpl implements SettingsScreen {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    private final AppCompatActivity activity;
    private Callbacks callbacks;

    @Inject
    public SettingsScreenImpl(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Callbacks callbacks) {
        this.callbacks = callbacks;
        ButterKnife.inject(this, activity);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showTimePickerDialog() {
    }

    @OnClick(R.id.notification_preference)
    void onNotificationPreferenceClick() {
    }

    @OnClick(R.id.time_preference)
    void onTimePreferenceClick() {
    }
}
