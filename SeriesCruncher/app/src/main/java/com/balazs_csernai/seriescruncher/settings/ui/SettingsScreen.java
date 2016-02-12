package com.balazs_csernai.seriescruncher.settings.ui;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
public interface SettingsScreen {

    interface Callbacks {

        void onNotificationCheckChanged(boolean isChecked);

        void onNotificationTimeTapped();
    }

    void onCreate(Callbacks callbacks);

    void setNotificationsEnabled(boolean enabled);

    void setNotificationTime(int hour, int minute);

    void setTimePickerEnabled(boolean enabled);

    void showTimePickerDialog();
    
}
