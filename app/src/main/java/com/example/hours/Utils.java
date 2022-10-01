package com.example.hours;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<OnUpdateListener> mListeners;

    public static void addListener(OnUpdateListener listener) {
        if(mListeners == null)
            mListeners = new ArrayList<>();
        if(!mListeners.contains(listener))
            mListeners.add(listener);
    }

    public static void removeListener(OnUpdateListener listener) {
        if(mListeners == null)
            mListeners = new ArrayList<>();
        if(mListeners.contains(listener))
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
        Button btnMiddayExit = viewMiddayRow.findViewById(R.id.btn_midday_exit);
        btnMiddayExit.setOnClickListener(listener);
        Button btnMiddayArrival = viewMiddayRow.findViewById(R.id.btn_midday_arrival);
        btnMiddayArrival.setOnClickListener(listener);

        ImageView imgRemoveMiddayRow = viewMiddayRow.findViewById(R.id.img_remove_midday);
        imgRemoveMiddayRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.removeMiddayRowFromLayout(layout, viewMiddayRow);
            }
        });
        layout.addView(viewMiddayRow);
    }

    public static void popTimePicker(View btnView, Context context) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Timestamp viewTimestamp = new Timestamp(selectedHour, selectedMinute);
                ((Button)btnView).setText(viewTimestamp.toString());
                if(btnView.getId() == R.id.btn_midday_exit){
                    ((Button)((ConstraintLayout)btnView.getParent()).findViewById(R.id.btn_midday_arrival)).setText(viewTimestamp.toString());
                }

                NotifyListeners();
            }
        };
        Timestamp timestamp = new Timestamp();
        timestamp.setTime(((Button)btnView).getText().toString());
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(context, AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                        timestamp.getHour(), timestamp.getMinute(),
                        true);
        timePickerDialog.setTitle("הזן שעה");
        timePickerDialog.show();
    }

    public static void GetTimestampsFromViewIndex(LinearLayout layout, int i, Timestamp exit, Timestamp arrival){
        View middayView = layout.getChildAt(i);
        Button middayExit = middayView.findViewById(R.id.btn_midday_exit);
        Button middayArrival = middayView.findViewById(R.id.btn_midday_arrival);
        exit.setTime(middayExit.getText().toString());
        arrival.setTime(middayArrival.getText().toString());
    }

    public static void removeMiddayRowFromLayout(LinearLayout layout, View view) {
        layout.removeView(view);
        NotifyListeners();
    }

    private static void NotifyListeners() {
        for(int i = 0; i < mListeners.size(); i++){
            mListeners.get(i).onUpdate(mListeners.get(i));
        }
    }

}
