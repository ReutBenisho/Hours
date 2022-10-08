package com.example.hours.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.hours.R;

public class SharedPreferencesUtil {
    public static void loadDefaults(Context context){
        PreferenceManager.setDefaultValues(context, R.xml.header_preferences, false);
        PreferenceManager.setDefaultValues(context, R.xml.general_preferences, false);
        PreferenceManager.setDefaultValues(context, R.xml.notifiations_preferences, false);
        PreferenceManager.setDefaultValues(context, R.xml.times_preferences, false);
    }
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
    public static boolean getBoolean(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }
}
