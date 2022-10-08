package com.example.hours.utils;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.hours.R;
import com.google.android.material.textfield.TextInputEditText;

public class Utils {
    public static void setupDarkMode(Context context) {
        if(SharedPreferencesUtil.getBoolean(context.getString(R.string.pref_system_mode), context)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }else{
            if(SharedPreferencesUtil.getBoolean(context.getString(R.string.pref_dark_mode), context)){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        }
    }

    public static void addMiddayRowToLayout(LayoutInflater layoutInflater, LinearLayout layout, Context context) {
        View viewMiddayRow = layoutInflater.inflate(R.layout.row_midday_exit_and_arrival_times, null, false);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker(view, context);
            }
        };
        TextInputEditText txtMiddayExit = viewMiddayRow.findViewById(R.id.txt_midday_exit_time);
        txtMiddayExit.setOnClickListener(listener);
        TextInputEditText txtMiddayArrival = viewMiddayRow.findViewById(R.id.txt_midday_arrival_time);
        txtMiddayArrival.setOnClickListener(listener);

        ImageView imgRemoveMiddayRow = viewMiddayRow.findViewById(R.id.img_remove_midday);
        imgRemoveMiddayRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.removeMiddayRowFromLayout(layout, viewMiddayRow);
            }
        });
        layout.addView(viewMiddayRow);
    }

    public static void popTimePicker(View view, Context context) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Timestamp viewTimestamp = new Timestamp(selectedHour, selectedMinute);
                ((EditText)view).setText(viewTimestamp.toString());
                if(view.getId() == R.id.txt_midday_exit_time){
                    ((EditText)(view.getRootView().findViewById(R.id.txt_midday_arrival_time))).setText(viewTimestamp.toString());
                }

                ListenerManager.NotifyListeners(ListenerManager.ListenerType.INFO_LABELS);
            }
        };
        Timestamp timestamp = new Timestamp();
        timestamp.setTime(((EditText)view).getText().toString());
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(context, AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                        timestamp.getHour(), timestamp.getMinute(),
                        true);
        timePickerDialog.setTitle("הזן שעה");
        timePickerDialog.show();
    }

    public static void GetTimestampsFromViewIndex(LinearLayout layout, int i, Timestamp exit, Timestamp arrival){
        View middayView = layout.getChildAt(i);
        EditText middayExit = middayView.findViewById(R.id.txt_midday_exit_time);
        EditText middayArrival = middayView.findViewById(R.id.txt_midday_arrival_time);
        exit.setTime(middayExit.getText().toString());
        arrival.setTime(middayArrival.getText().toString());
    }

    public static void removeMiddayRowFromLayout(LinearLayout layout, View view) {
        layout.removeView(view);
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.INFO_LABELS);
    }


    public static void addExitTimeLayout(LayoutInflater layoutInflater, LinearLayout layout, Context context) {
        View viewExitRow = layoutInflater.inflate(R.layout.row_add_exit_time, null, false);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker(view, context);
            }
        };
        EditText txtExitTime = viewExitRow.findViewById(R.id.txt_exit_time);
        txtExitTime.setOnClickListener(listener);
        txtExitTime.setText(Defaults.ARRIVAL_TIME.add(Defaults.FULL_DAY).add(Defaults.LAUNCH_BREAK_DURATION).toString());

        layout.addView(viewExitRow);
    }
    public static void removeExitTime(LinearLayout layout) {
        layout.removeAllViews();
    }
}