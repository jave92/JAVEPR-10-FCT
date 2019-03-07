package com.example.er_ja.jave_pr10_fct.base;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class TimePickerDialogFragment extends DialogFragment {
    private TimePickerDialog.OnTimeSetListener listener;

    public static TimePickerDialogFragment newInstance(TimePickerDialog.OnTimeSetListener listener) {
        TimePickerDialogFragment fragment = new TimePickerDialogFragment();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(TimePickerDialog.OnTimeSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), listener, hour, minute, true);
    }
}
