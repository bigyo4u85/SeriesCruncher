package com.balazs_csernai.seriescruncher.utils.dialog;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;

import com.balazs_csernai.seriescruncher.app.component.ActivityScope;
import com.balazs_csernai.seriescruncher.utils.dialog.TimePickerDialogFragment.OnTimePickListener;

import javax.inject.Inject;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
@ActivityScope
public class DialogFactoryImpl implements DialogFactory {

    private final FragmentManager fragmentManager;

    @Inject
    public DialogFactoryImpl(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public DialogFragment createNetworkError() {
        DialogFragment dialogFragment = new NetworkErrorDialogFragment();
        dialogFragment.show(fragmentManager, "network-error");
        return dialogFragment;
    }

    @Override
    public DialogFragment createTimePickerDialog(int hour, int minute) {
        TimePickerDialogFragment dialogFragment = new TimePickerDialogFragment();
        dialogFragment.setTime(hour, minute);
        dialogFragment.show(fragmentManager, "time-picker");
        return dialogFragment;
    }
}
