package com.balazs_csernai.seriescruncher.utils.dialog;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.TimePicker;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
@ActivityScope
public class DialogFactoryImpl implements DialogFactory {

    private final Activity activity;
    private final FragmentManager fragmentManager;

    @Inject
    public DialogFactoryImpl(Activity activity, FragmentManager fragmentManager) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public DialogFragment createNetworkError() {
        DialogFragment dialogFragment = new NetworkErrorDialogFragment();
        dialogFragment.show(fragmentManager, "network-error");
        return dialogFragment;
    }

    @Override
    public TimePickerDialog createTimePickerDialog(int hour, int minute, final TimePickListener listener) {
        final TimePickerDialog dialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                listener.onTimePicked(hourOfDay, minute);
            }
        }, hour, minute, true);
        dialog.show();
        return dialog;
    }
}
