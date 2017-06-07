package com.example.davidtran.todoappadvanced;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.widget.TextView;
import android.widget.DatePicker;
import android.app.Dialog;
import java.util.Calendar;
/**
 * Created by davidtran on 6/7/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_DayNight_Dialog_Alert,this, year, month, day);

    }


   public void onDateSet(DatePicker view, int year, int month, int day) {

        TextView tv = (TextView) getActivity().findViewById(R.id.tv_dateset);
        tv.setText(year + "/" + month + "/" + day);

    }
}