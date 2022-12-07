package com.example.hours.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.loader.content.CursorLoader;

import com.example.hours.calcUtils.Timestamp;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.utils.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private final List<DailyReport> mDailyReports = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            //ourInstance.initializeDailyReports();
        }
        return ourInstance;
    }

    public static void loadFromDataBase(HoursOpenHelper dbHelper){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {
                HoursProviderContract.DailyReports._ID,
                HoursProviderContract.DailyReports.COLUMN_DATE,
                HoursProviderContract.DailyReports.COLUMN_ARRIVAL,
                HoursProviderContract.DailyReports.COLUMN_EXIT};
        String orderBy = HoursProviderContract.DailyReports.COLUMN_DATE;
        Cursor cursor = App.getContext().getContentResolver().query(HoursProviderContract.DailyReports.CONTENT_URI,
                columns, null, null, orderBy);
        loadDailyReportsFromCursor(cursor);
    }

    private static void loadDailyReportsFromCursor(Cursor cursor) {
        int idPos = cursor.getColumnIndex(HoursProviderContract.DailyReports._ID);
        int datePos = cursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_DATE);
        int arrivalPos = cursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_ARRIVAL);
        int exitPos = cursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_EXIT);
        DataManager db = getInstance();
        db.mDailyReports.clear();
        while(cursor.moveToNext())
        {
            int reportId = cursor.getInt(idPos);

            String dateStr = cursor.getString(datePos);
            Date date;
            try {

                date = (new SimpleDateFormat("yyyyMMdd")).parse(dateStr);
            }
            catch (ParseException ex){
                date = new Date(2022 - 1900, 1, 1);
            }

            Timestamp arrival = new Timestamp(cursor.getString(arrivalPos));
            Timestamp exit = new Timestamp(cursor.getString(exitPos));
            DailyReport report = new DailyReport(reportId, date, arrival, exit);
            db.mDailyReports.add(report);
        }
        cursor.close();
    }

    public List<DailyReport> getDailyReports() {
        return mDailyReports;
    }
//
//    public int findNote(NoteInfo note) {
//        for(int index = 0; index < mNotes.size(); index++) {
//            if(note.equals(mNotes.get(index)))
//                return index;
//        }
//
//        return -1;
//    }
//
//    public void removeNote(int index) {
//        mNotes.remove(index);
//    }
//
//    public List<CourseInfo> getCourses() {
//        return mCourses;
//    }
//
//    public CourseInfo getCourse(String id) {
//        for (CourseInfo course : mCourses) {
//            if (id.equals(course.getCourseId()))
//                return course;
//        }
//        return null;
//    }
//
//    public List<NoteInfo> getNotes(CourseInfo course) {
//        ArrayList<NoteInfo> notes = new ArrayList<>();
//        for(NoteInfo note:mNotes) {
//            if(course.equals(note.getCourse()))
//                notes.add(note);
//        }
//        return notes;
//    }
//
//    public int getNoteCount(CourseInfo course) {
//        int count = 0;
//        for(NoteInfo note:mNotes) {
//            if(course.equals(note.getCourse()))
//                count++;
//        }
//        return count;
//    }

    private DataManager() {
    }

    //region Initialization code

    public void initializeDailyReports() {
        final DataManager dm = getInstance();

        DailyReport report = new DailyReport(1, new Date(2022 - 1900, 10, 30), new Timestamp(7, 30), new Timestamp(16, 24));
        DailyReport report2 = new DailyReport(2, new Date(2022 - 1900, 10, 31), new Timestamp(7, 30), new Timestamp(17, 25));
        DailyReport report3 = new DailyReport(3, new Date(2022 - 1900, 11, 1),new Timestamp(7, 30), new Timestamp(17, 30));
        DailyReport report4 = new DailyReport(4, new Date(2022 - 1900, 11, 2), new Timestamp(7, 30), new Timestamp(16, 0));
        mDailyReports.add(report);
        mDailyReports.add(report2);
        mDailyReports.add(report3);
        mDailyReports.add(report4);
    }

    //endregion

}
