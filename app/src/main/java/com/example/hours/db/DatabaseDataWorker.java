package com.example.hours.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import com.example.hours.calcUtils.Timestamp;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.utils.App;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DatabaseDataWorker {
    private final Context m_Context;
    private SQLiteDatabase mDb;

    public DatabaseDataWorker(SQLiteDatabase db, Context context) {
        mDb = db;
        m_Context = context;
    }

    public void insertDailyReports() {
        insertDailyReport(new Date(2022 - 1900, 10, 30), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 24, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 10, 31), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 25, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 11, 1), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(17 * 60 + 30, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 11, 2), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 0, ChronoUnit.MINUTES));
    }

    private void insertDailyReport(Date date, Duration arrival, Duration exit) {
        ContentValues values = new ContentValues();
        values.put(HoursProviderContract.DailyReports.COLUMN_DATE, new SimpleDateFormat("yyyyMMdd").format(date));
        values.put(HoursProviderContract.DailyReports.COLUMN_ARRIVAL, (new Timestamp(arrival)).toString());
        values.put(HoursProviderContract.DailyReports.COLUMN_EXIT, (new Timestamp(exit)).toString());
        m_Context.getContentResolver().insert(HoursProviderContract.DailyReports.CONTENT_URI, values);
    }
}
