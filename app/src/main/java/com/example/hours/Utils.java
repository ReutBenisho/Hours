package com.example.hours;

import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

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
}
