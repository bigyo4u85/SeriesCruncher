package com.balazs_csernai.seriescruncher.settings.ui;

/**
 * Created by erik_markus_kramli on 2016-02-11.
 */
public interface SettingsScreen {

    interface Callbacks {

        void onNotificationsTapped(boolean enabled);

        void onTimePicked();
    }

    void onCreate(Callbacks callbacks);

    void showTimePickerDialog();
    
}
