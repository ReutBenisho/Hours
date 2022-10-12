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
