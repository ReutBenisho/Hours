package com.example.hours.utils;

import android.content.SharedPreferences;
import android.widget.EditText;

import androidx.preference.PreferenceManager;

import com.example.hours.R;
import com.example.hours.calcUtils.BreakTimes;
import com.example.hours.calcUtils.CustomBreak;

import java.util.ArrayList;

public class SharedPreferencesUtil {
    public static void loadDefaults(){

        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        SharedPreferences.Editor editor= manager.edit();
        boolean firstRun = manager.getBoolean(App.getStr(R.string.pref_first_run), true);
        if(firstRun)
        {
            editor.putBoolean(App.getStr(R.string.pref_first_run),false);
            setDefaultTimeToSharedPreference(editor, null);
            editor.commit();
        }

        PreferenceManager.setDefaultValues(App.getContext(), R.xml.header_preferences, true);
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.general_preferences, true);
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.notifiations_preferences, true);
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.times_preferences, true);

        Defaults.User.ARRIVAL_TIME.setTime(manager.getString(App.getStr(R.string.pref_default_arrival_time), Defaults.getArrival().toString()));
        Defaults.User.EXIT_TIME.setTime(manager.getString(App.getStr(R.string.pref_default_exit_time), Defaults.getExit().toString()));
        Defaults.useSystem = manager.getBoolean(App.getStr(R.string.pref_default_system_time), true);
        Defaults.User.LUNCH_BREAK_START.setTime(manager.getString(App.getStr(R.string.pref_default_lunch_break_time), Defaults.getLunchStart().toString()));
        Defaults.User.LUNCH_BREAK_DURATION.setTime(manager.getString(App.getStr(R.string.pref_default_lunch_break_duration), Defaults.getLunchDuration().toString()));
        Defaults.User.EVENING_BREAK_START.setTime(manager.getString(App.getStr(R.string.pref_default_evening_break_time), Defaults.getEveningStart().toString()));
        Defaults.User.EVENING_BREAK_DURATION.setTime(manager.getString(App.getStr(R.string.pref_default_evening_break_duration), Defaults.getEveningDuration().toString()));
        Defaults.User.NIGHT_BREAK_START.setTime(manager.getString(App.getStr(R.string.pref_default_night_break_time), Defaults.getNightStart().toString()));
        Defaults.User.NIGHT_BREAK_DURATION.setTime(manager.getString(App.getStr(R.string.pref_default_night_break_duration), Defaults.getNightDuration().toString()));


    }

    private static void setDefaultTimeToSharedPreference(SharedPreferences.Editor editor, String pref) {
        if(pref == null || pref == App.getStr(R.string.pref_default_arrival_time))
            editor.putString(App.getStr(R.string.pref_default_arrival_time), Defaults.getArrival().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_exit_time))
            editor.putString(App.getStr(R.string.pref_default_exit_time), Defaults.getExit().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_lunch_break_time))
            editor.putString(App.getStr(R.string.pref_default_lunch_break_time), Defaults.getLunchStart().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_lunch_break_duration))
            editor.putString(App.getStr(R.string.pref_default_lunch_break_duration), Defaults.getLunchDuration().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_evening_break_time))
            editor.putString(App.getStr(R.string.pref_default_evening_break_time), Defaults.getEveningStart().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_evening_break_duration))
            editor.putString(App.getStr(R.string.pref_default_evening_break_duration), Defaults.getEveningDuration().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_night_break_time))
            editor.putString(App.getStr(R.string.pref_default_night_break_time), Defaults.getNightStart().toString());
        if(pref == null || pref == App.getStr(R.string.pref_default_night_break_duration))
            editor.putString(App.getStr(R.string.pref_default_night_break_duration), Defaults.getNightDuration().toString());
//        if(pref == null || pref == App.getStr(R.string.pref_custom_breaks)) {
//            ArrayList<CustomBreak> breaks = new ArrayList<>();
//            boolean[] days = new boolean[6];
//            breaks.add(new CustomBreak(true, new BreakTimes(16, 30, 19, 0), days));
//            days[0] = true;
//            days[3] = true;
//            breaks.add(new CustomBreak(false, new BreakTimes(16, 45, 19, 15), days));
//            days[1] = true;
//            breaks.add(new CustomBreak(true, new BreakTimes(12, 50, 13, 15), days));
//            editor.putString(App.getStr(R.string.pref_custom_breaks), CustomBreak.serialize(breaks));
//        }
    }

    public static void setDefaults(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setDefaults(String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        return preferences.getString(key, null);
    }
    public static boolean getBoolean(String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        return preferences.getBoolean(key, false);
    }

    public static boolean isTimePref(String pref) {
        if(pref == App.getStr(R.string.pref_default_arrival_time)
        || pref == App.getStr(R.string.pref_default_exit_time)
        || pref == App.getStr(R.string.pref_default_lunch_break_time)
        || pref == App.getStr(R.string.pref_default_lunch_break_duration)
        || pref == App.getStr(R.string.pref_default_evening_break_time)
        || pref == App.getStr(R.string.pref_default_evening_break_duration)
        || pref == App.getStr(R.string.pref_default_night_break_time)
        || pref == App.getStr(R.string.pref_default_night_break_duration))
                return true;
        return false;
    }

    public static void setPreviousTime(String pref) {
        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        SharedPreferences.Editor editor= manager.edit();
        setDefaultTimeToSharedPreference(editor, pref);
        editor.commit();
    }
}
