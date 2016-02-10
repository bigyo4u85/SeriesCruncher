package com.balazs_csernai.seriescruncher.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.balazs_csernai.seriescruncher.R;

/**
 * Created by erik_markus_kramli on 2016-02-10.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}
