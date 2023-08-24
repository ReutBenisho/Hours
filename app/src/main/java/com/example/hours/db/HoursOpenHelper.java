package com.example.hours.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HoursOpenHelper extends SQLiteOpenHelper {
    public static final String  DATABASE_NAME = "Hours.db";
    public static final int DATABASE_VERSION = 2;
    public HoursOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HoursDbContract.DailyReportEntry.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(HoursDbContract.DailyReportEntry.SQL_CREATE_INDEX1);

        //DatabaseDataWorker worker = new DatabaseDataWorker(sqLiteDatabase, m_Context);
        //worker.insertDailyReports();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion < 2){
            sqLiteDatabase.execSQL(HoursDbContract.DailyReportEntry.SQL_CREATE_INDEX1);
        }
    }
}
