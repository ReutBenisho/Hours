package com.example.hours.db;

import android.provider.BaseColumns;

public class HoursDbContract {
    private HoursDbContract() {};

    public static final class DailyReportEntry implements BaseColumns {
        public static final String TABLE_NAME = "daily_report";
        public static final String COLUMN_DATE = "daily_report_column_date";
        public static final String COLUMN_ARRIVAL = "daily_report_column_arrival";
        public static final String COLUMN_EXIT = "daily_report_column_exit";


        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_DATE + " DATE UNIQUE NOT NULL, " +
                        COLUMN_ARRIVAL + " TIME NOT NULL, " +
                        COLUMN_EXIT + " TIME NOT NULL)";
    }

}
