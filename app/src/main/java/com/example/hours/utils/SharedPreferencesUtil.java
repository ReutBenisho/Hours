package com.example.hours.utils;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.hours.R;

public class SharedPreferencesUtil {
    public static void loadDefaults(){
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.header_preferences, true);
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.general_preferences, true);
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.notifiations_preferences, true);
        PreferenceManager.setDefaultValues(App.getContext(), R.xml.times_preferences, true);

        SharedPreferences manager = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        Defaults.useSystem = manager.getBoolean(App.getStr(R.string.pref_default_system_time), true);

        Defaults.User.ARRIVAL_TIME.setTime(manager.getString(App.getStr(R.string.pref_default_arrival_time), Defaults.getArrival().toString()));
        Defaults.User.EXIT_TIME.setTime(manager.getString(App.getStr(R.string.pref_default_exit_time), Defaults.getExit().toString()));
        Defaults.User.LUNCH_BREAK_START.setTime(manager.getString(App.getStr(R.string.pref_default_lunch_break_time), Defaults.getLunchStart().toString()));
        Defaults.User.LUNCH_BREAK_DURATION.setTime(manager.getString(App.getStr(R.string.pref_default_lunch_break_duration), Defaults.getLunchDuration().toString()));
        Defaults.User.EVENING_BREAK_START.setTime(manager.getString(App.getStr(R.string.pref_default_evening_break_time), Defaults.getEveningStart().toString()));
        Defaults.User.EVENING_BREAK_DURATION.setTime(manager.getString(App.getStr(R.string.pref_default_evening_break_duration), Defaults.getEveningDuration().toString()));
        Defaults.User.NIGHT_BREAK_START.setTime(manager.getString(App.getStr(R.string.pref_default_night_break_time), Defaults.getNightStart().toString()));
        Defaults.User.NIGHT_BREAK_DURATION.setTime(manager.getString(App.getStr(R.string.pref_default_night_break_duration), Defaults.getNightDuration().toString()));
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
}
