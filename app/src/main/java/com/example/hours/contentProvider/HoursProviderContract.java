package com.example.hours.contentProvider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class HoursProviderContract {
    private HoursProviderContract() {}

    public static final String AUTHORITY = "com.example.hours.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    protected interface DailyReportsColumns{
        public static final String COLUMN_DATE = "daily_report_column_date";
        public static final String COLUMN_ARRIVAL = "daily_report_column_arrival";
        public static final String COLUMN_EXIT = "daily_report_column_exit";
    }

    public static final class DailyReports implements BaseColumns, DailyReportsColumns{
        public static final String PATH = "dailyReports";
        // content://com.example.hours.provider/dailyReports
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
    }
}
