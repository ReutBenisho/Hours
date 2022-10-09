package com.example.hours.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }

    public static Resources getRes(){
        return mContext.getResources();
    }

    public static String getStr(int id){
        return mContext.getResources().getString(id);
    }
}
