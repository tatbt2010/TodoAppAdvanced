package com.example.davidtran.todoappadvanced;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by davidtran on 6/7/17.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.SECOND);

        return new TimePickerDialog(getActivity(),this, hour, minute,false);

    }
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        String AM_PM="";
        //int hour = 0;
        if(hourOfDay>=12) {
            AM_PM = "PM";
            hourOfDay -= 12;      // convert hour24format to 12format
        }
        else AM_PM = "AM";

        Calendar datetime = Calendar.getInstance();

        TextView tv = (TextView) getActivity().findViewById(R.id.tv_timeset);
        tv.setText(hourOfDay + ":" + minute + " " + AM_PM);
    }
}

