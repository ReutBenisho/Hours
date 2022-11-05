package com.example.hours.db;

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
            ourInstance.initializeDailyReports();
        }
        return ourInstance;
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
        DailyReport report2 = new DailyReport(new Date(2022 - 1900, 10, 31), Duration.of(8 * 60 + 12, ChronoUnit.MINUTES), Duration.of(17 * 60 + 30, ChronoUnit.MINUTES));
        DailyReport report3 = new DailyReport(new Date(2022 - 1900, 11, 1), Duration.of(8 * 60 + 22, ChronoUnit.MINUTES), Duration.of(19 * 60 + 12, ChronoUnit.MINUTES));
        DailyReport report4 = new DailyReport(new Date(2022 - 1900, 11, 2), Duration.of(7 * 60 + 22, ChronoUnit.MINUTES), Duration.of(18 * 60 + 46, ChronoUnit.MINUTES));
        mDailyReports.add(report);
        mDailyReports.add(report2);
        mDailyReports.add(report3);
        mDailyReports.add(report4);
    }

    //endregion

}
