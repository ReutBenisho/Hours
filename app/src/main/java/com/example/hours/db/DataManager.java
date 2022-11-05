package com.example.hours.db;

import static com.example.hours.db.HoursDbContract.DailyReportEntry;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hours.calcUtils.Timestamp;

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
                DailyReportEntry.COLUMN_DATE,
                DailyReportEntry.COLUMN_ARRIVAL,
                DailyReportEntry.COLUMN_EXIT};
        Cursor cursor = db.query(DailyReportEntry.TABLE_NAME, columns,
                null, null, null, null, DailyReportEntry.COLUMN_DATE
        );
        loadDailyReportsFromCursor(cursor);
    }

    private static void loadDailyReportsFromCursor(Cursor cursor) {
        int datePos = cursor.getColumnIndex(DailyReportEntry.COLUMN_DATE);
        int arrivalPos = cursor.getColumnIndex(DailyReportEntry.COLUMN_ARRIVAL);
        int exitPos = cursor.getColumnIndex(DailyReportEntry.COLUMN_EXIT);
        DataManager db = getInstance();
        db.mDailyReports.clear();
        while(cursor.moveToNext())
        {
            String dateStr = cursor.getString(datePos);
            Date date;
            try {

                date = (new SimpleDateFormat("dd-MM-yyyy")).parse(dateStr);
            }
            catch (ParseException ex){
                date = new Date(2022 - 1900, 1, 1);
            }

            Timestamp arrival = new Timestamp(cursor.getString(arrivalPos));
            Timestamp exit = new Timestamp(cursor.getString(exitPos));
            DailyReport report = new DailyReport(date, arrival.getDuration(), exit.getDuration());
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

        DailyReport report = new DailyReport(new Date(2022 - 1900, 10, 30), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 24, ChronoUnit.MINUTES));
        DailyReport report2 = new DailyReport(new Date(2022 - 1900, 10, 31), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(17 * 60 + 25, ChronoUnit.MINUTES));
        DailyReport report3 = new DailyReport(new Date(2022 - 1900, 11, 1), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(17 * 60 + 30, ChronoUnit.MINUTES));
        DailyReport report4 = new DailyReport(new Date(2022 - 1900, 11, 2), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 0, ChronoUnit.MINUTES));
        mDailyReports.add(report);
        mDailyReports.add(report2);
        mDailyReports.add(report3);
        mDailyReports.add(report4);
    }

    //endregion

}
