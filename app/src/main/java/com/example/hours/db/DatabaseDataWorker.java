package com.example.hours.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DatabaseDataWorker {
    private SQLiteDatabase mDb;

    public DatabaseDataWorker(SQLiteDatabase db) {
        mDb = db;
    }

    public void insertDailyReports() {
        insertDailyReport(new Date(2022 - 1900, 10, 30), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 24, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 10, 31), Duration.of(8 * 60 + 12, ChronoUnit.MINUTES), Duration.of(17 * 60 + 30, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 11, 1), Duration.of(8 * 60 + 22, ChronoUnit.MINUTES), Duration.of(19 * 60 + 12, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 11, 2), Duration.of(7 * 60 + 22, ChronoUnit.MINUTES), Duration.of(18 * 60 + 46, ChronoUnit.MINUTES));
    }

    private void insertDailyReport(Date date, Duration arrival, Duration exit) {
        ContentValues values = new ContentValues();
        values.put(HoursDbContract.DailyReportEntry.COLUMN_DATE, date.toString());
        values.put(HoursDbContract.DailyReportEntry.COLUMN_ARRIVAL, arrival.toString());
        values.put(HoursDbContract.DailyReportEntry.COLUMN_EXIT, exit.toString());

        long newRowId = mDb.insert(HoursDbContract.DailyReportEntry.TABLE_NAME, null, values);
    }
}
