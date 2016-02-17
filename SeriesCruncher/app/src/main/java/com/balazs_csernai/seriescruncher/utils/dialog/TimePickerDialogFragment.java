package com.balazs_csernai.seriescruncher.utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

/**
 * Created by Erik_Markus_Kramli on 2016-02-12.
 */
public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public interface OnTimePickListener {
        void onTimePicked(int hour, int minute);
    }

    private int hour, minute;
    private OnTimePickListener listener;

    public void setTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnTimePickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.getClass().getSimpleName() + " must implement " + OnTimePickListener.class.getSimpleName());
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (view.isShown()) {
            listener.onTimePicked(hourOfDay, minute);
        }
    }
}
