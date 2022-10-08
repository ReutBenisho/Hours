package com.example.hours;

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

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Utils {
    public enum ListenerType{
        INFO_LABELS,
        ACTION_BAR_TITLE
    }
    private static ArrayList<ArrayList<OnUpdateListener>> mListeners;

    public static void addListener(OnUpdateListener listener, ListenerType type) {
        if(mListeners == null)
            mListeners = new ArrayList<>();
        if(mListeners.size() < type.ordinal() + 1){
            mListeners.add(type.ordinal(), new ArrayList<>());
        }
        if(!mListeners.get(type.ordinal()).contains(listener))
            mListeners.get(type.ordinal()).add(listener);
    }

    public static void removeListener(OnUpdateListener listener, ListenerType type) {
        if(mListeners != null
                && mListeners.get(type.ordinal()) != null
                && mListeners.get(type.ordinal()).contains(listener))
            mListeners.remove(listener);
    }

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

                NotifyListeners(ListenerType.INFO_LABELS, null);
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
        NotifyListeners(ListenerType.INFO_LABELS, null);
    }

    public static void NotifyListeners(ListenerType type, Object obj) {
        if(mListeners == null)
            return;
        for(int i = 0; i < mListeners.get(type.ordinal()).size(); i++){
            mListeners.get(type.ordinal()).get(i).onUpdate(mListeners.get(type.ordinal()).get(i), obj);
        }
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
