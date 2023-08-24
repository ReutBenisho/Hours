package com.example.hours.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.hours.R;

import java.util.Locale;

public class App extends Application {
    private static Context mContext;
    private Locale mLocale = null;
    private static Locale sLocale = null;

    public static Locale getLocale() {
        return sLocale;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        SharedPreferencesUtil.setDefaults(getString(R.string.pref_existing_user), true);
        SharedPreferencesUtil.loadDefaults();
        Utils.setupDarkMode(getApplicationContext());
        Configuration config = getBaseContext().getResources().getConfiguration();

        String language = SharedPreferencesUtil.getString(getString(R.string.pref_language));
        LocaleHelper.setLocale(this, language);
        sLocale = new Locale(language);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        //TODO: check if needs this function
        super.onConfigurationChanged(newConfig);
        if (mLocale != null)
        {
            sLocale = mLocale;
            newConfig.locale = mLocale;
            Locale.setDefault(mLocale);
            getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getContext(){
        return mContext;
    }

    public static Resources getRes(){
        return mContext.getResources();
    }

    public static final String getStr(int id){
        return mContext.getResources().getString(id);
    }
}
