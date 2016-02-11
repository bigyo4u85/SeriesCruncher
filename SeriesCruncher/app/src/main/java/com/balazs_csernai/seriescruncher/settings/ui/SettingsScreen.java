package com.balazs_csernai.seriescruncher.settings.ui;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
public interface SettingsScreen {

    interface Callbacks {

        void onNotificationCheckChanged(boolean isChecked);

        void onNotificationTimeTapped();

        void onTimePicked(int hour, int minute);
    }

    void onCreate(Callbacks callbacks);

    void setPreferenceValues(boolean notificationEnabled, int notificationHour, int notificationMinute);

    void showTimePickerDialog();
    
}
