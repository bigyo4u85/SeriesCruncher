package com.balazs_csernai.seriescruncher.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.balazs_csernai.seriescruncher.R;
import com.balazs_csernai.seriescruncher.settings.component.SettingsComponent;
import com.balazs_csernai.seriescruncher.settings.presenter.SettingsPresenter;
import com.balazs_csernai.seriescruncher.utils.dialog.TimePickerDialogFragment;

import javax.inject.Inject;

/**
 * Created by erik_markus_kramli on 2016-02-10.
 */
public class SettingsActivity extends AppCompatActivity implements TimePickerDialogFragment.OnTimePickListener {

    @Inject
    SettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SettingsComponent.Injector.inject(this);
        presenter.onCreate();
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

    @Override
    public void onTimePicked(int hour, int minute) {
        presenter.onTimePicked(hour, minute);
    }
}
