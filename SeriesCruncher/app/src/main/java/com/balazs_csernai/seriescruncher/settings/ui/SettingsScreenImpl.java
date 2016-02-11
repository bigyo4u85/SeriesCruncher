package com.balazs_csernai.seriescruncher.settings.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.utils.dialog.DialogFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
public class SettingsScreenImpl implements SettingsScreen, DialogFactory.TimePickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.notification_check)
    AppCompatCheckBox notificationsCheck;

    @InjectView(R.id.notification_time)
    TextView notificationTimeText;

    private final AppCompatActivity activity;
    private final DialogFactory dialogFactory;
    private Callbacks callbacks;
    private int hour, minute;

    @Inject
    public SettingsScreenImpl(AppCompatActivity activity, DialogFactory dialogFactory) {
        this.activity = activity;
        this.dialogFactory = dialogFactory;
    }

    @Override
    public void onCreate(Callbacks callbacks) {
        this.callbacks = callbacks;
        ButterKnife.inject(this, activity);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setPreferenceValues(boolean notificationEnabled, int notificationHour, int notificationMinute) {
        notificationsCheck.setChecked(notificationEnabled);
        updateTimeText(notificationHour, notificationMinute);
        hour = notificationHour;
        minute = notificationMinute;
    }

    private void updateTimeText(int hour, int minute) {
        notificationTimeText.setText(String.format("%02d:%02d", hour, minute));
    }

    @Override
    public void showTimePickerDialog() {
        dialogFactory.createTimePickerDialog(hour, minute, this);
    }

    @Override
    public void onTimePicked(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        updateTimeText(hour, minute);
        callbacks.onTimePicked(hour, minute);
    }

    @OnClick(R.id.notification_preference)
    void onNotificationCheckChange() {
        boolean isChecked = !notificationsCheck.isChecked();
        notificationsCheck.setChecked(isChecked);
        callbacks.onNotificationCheckChanged(isChecked);
    }

    @OnClick(R.id.time_preference)
    void onTimePreferenceTap() {
        callbacks.onNotificationTimeTapped();
    }

}
