package com.example.hours.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.example.hours.R;

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        SharedPreferencesUtil.setDefaults(getString(R.string.pref_existing_user), true);
        SharedPreferencesUtil.loadDefaults();
        Utils.setupDarkMode(getApplicationContext());
        LocaleHelper.setLocale(this, SharedPreferencesUtil.getString(getString(R.string.pref_language)));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
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
