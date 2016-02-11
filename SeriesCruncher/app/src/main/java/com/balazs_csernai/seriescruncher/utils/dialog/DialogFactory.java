package com.balazs_csernai.seriescruncher.utils.dialog;

import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;

/**
 * Created by Balazs_Csernai on 2016.02.08..
 */
public interface DialogFactory {

    DialogFragment createNetworkError();

    interface TimePickListener {
        void onTimePicked(int hour, int minute);
    }

    TimePickerDialog createTimePickerDialog(int hour, int minute, TimePickListener listener);
}
