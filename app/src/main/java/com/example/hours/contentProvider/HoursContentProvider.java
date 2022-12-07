package com.example.hours.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.hours.db.HoursDbContract;
import com.example.hours.db.HoursOpenHelper;

public class HoursContentProvider extends ContentProvider {
    private HoursOpenHelper mDbOpenHelper;
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    public static final int DAILY_REPORTS = 0;
    public static final int DAILY_REPORTS_ROW = 1;

    static{
        sUriMatcher.addURI(HoursProviderContract.AUTHORITY, HoursProviderContract.DailyReports.PATH, DAILY_REPORTS);
        sUriMatcher.addURI(HoursProviderContract.AUTHORITY, HoursProviderContract.DailyReports.PATH + "/#", DAILY_REPORTS_ROW);
    }
    public HoursContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        long rowId = -1;
        String rowSelection = null;
        String[] rowSelectionArgs = null;
        int nRows = -1;
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();

        int uriMatch = sUriMatcher.match(uri);
        switch(uriMatch) {
            case DAILY_REPORTS:
                nRows = db.delete(HoursDbContract.DailyReportEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DAILY_REPORTS_ROW:
                rowId = ContentUris.parseId(uri);
                rowSelection = HoursDbContract.DailyReportEntry._ID + " = ?";
                rowSelectionArgs = new String[]{Long.toString(rowId)};
                nRows = db.delete(HoursDbContract.DailyReportEntry.TABLE_NAME, rowSelection, rowSelectionArgs);
                break;
        }

        return nRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();
        long rowId = -1;
        Uri rowUri = null;
        int uriMatch = sUriMatcher.match(uri);
        switch (uriMatch){
            case DAILY_REPORTS:
                rowId = db.insert(HoursDbContract.DailyReportEntry.TABLE_NAME, null, values);
                // content://com.example.hours.provider/notes/1
                rowUri = ContentUris.withAppendedId(uri, rowId);
                break;
        }
        return rowUri;
    }

    @Override
    public boolean onCreate() {
        mDbOpenHelper = new HoursOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        long rowId = -1;
        String rowSelection = null;
        String[] rowSelectionArgs = null;
        int uriMatch = sUriMatcher.match(uri);
        switch (uriMatch){
            case DAILY_REPORTS:
                cursor = db.query(HoursDbContract.DailyReportEntry.TABLE_NAME,
                        projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DAILY_REPORTS_ROW:
                rowId = ContentUris.parseId(uri);
                rowSelection = HoursDbContract.DailyReportEntry._ID + " = ?";
                rowSelectionArgs  = new String[] {Long.toString(rowId)};
                cursor = db.query(HoursDbContract.DailyReportEntry.TABLE_NAME, projection, rowSelection, rowSelectionArgs, null, null, null);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        long rowId = -1;
        String rowSelection = null;
        String[] rowSelectionArgs = null;
        int nRows = -1;
        SQLiteDatabase db = mDbOpenHelper.getWritableDatabase();

        int uriMatch = sUriMatcher.match(uri);
        switch(uriMatch) {
            case DAILY_REPORTS:
                nRows = db.update(HoursDbContract.DailyReportEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case DAILY_REPORTS_ROW:
                rowId = ContentUris.parseId(uri);
                rowSelection = HoursDbContract.DailyReportEntry._ID + " = ?";
                rowSelectionArgs = new String[]{Long.toString(rowId)};
                nRows = db.update(HoursDbContract.DailyReportEntry.TABLE_NAME, values, rowSelection, rowSelectionArgs);
                break;
        }

        return nRows;
    }
}