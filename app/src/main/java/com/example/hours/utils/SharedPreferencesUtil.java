package com.example.hours.utils;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.example.hours.R;
import com.example.hours.calcUtils.BreakTimes;
import com.example.hours.calcUtils.CustomBreak;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesUtil {
    public static void loadDefaults(){

        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        SharedPreferences.Editor editor= manager.edit();
        boolean firstRun = manager.getBoolean(App.getStr(R.string.pref_first_run), true);
        if(firstRun)
        {
            editor.putBoolean(App.getStr(R.string.pref_first_run),false);
            setDefaultTimeToSharedPreference(editor);
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

    private static void setDefaultTimeToSharedPreference(SharedPreferences.Editor editor) {
        editor.putString(App.getStr(R.string.pref_default_arrival_time), Defaults.getArrival().toString());
        editor.putString(App.getStr(R.string.pref_default_exit_time), Defaults.getExit().toString());
        editor.putString(App.getStr(R.string.pref_default_lunch_break_time), Defaults.getLunchStart().toString());
        editor.putString(App.getStr(R.string.pref_default_lunch_break_duration), Defaults.getLunchDuration().toString());
        editor.putString(App.getStr(R.string.pref_default_evening_break_time), Defaults.getEveningStart().toString());
        editor.putString(App.getStr(R.string.pref_default_evening_break_duration), Defaults.getEveningDuration().toString());
        editor.putString(App.getStr(R.string.pref_default_night_break_time), Defaults.getNightStart().toString());
        editor.putString(App.getStr(R.string.pref_default_night_break_duration), Defaults.getNightDuration().toString());
        ArrayList<CustomBreak> breaks = new ArrayList<>();
        breaks.add(new CustomBreak(true, new BreakTimes(16, 30, 19, 0)));
        breaks.add(new CustomBreak(false, new BreakTimes(16, 45, 19, 15)));
        breaks.add(new CustomBreak(true, new BreakTimes(12, 50, 13, 15)));

        Set<CustomBreak> set = new HashSet<>();
        set.addAll(breaks);
        editor.putString("CustomBreaks", CustomBreak.serialize(breaks));
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
        String orgStr = SharedPreferencesUtil.getString(pref);
        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        SharedPreferences.Editor editor= manager.edit();
        setDefaultTimeToSharedPreference(editor);
        editor.commit();
    }
}
