package com.example.hours.report;

import static com.example.hours.notifications.ReminderReceiver.EXTRA_EXTRA_TEXT;
import static com.example.hours.notifications.ReminderReceiver.EXTRA_PARAM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;


import androidx.loader.content.CursorLoader;

import com.example.hours.calcUtils.CalcInfo;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.calcUtils.Totals;
import com.example.hours.calcUtils.UserInfo;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.db.DailyReport;
import com.example.hours.notifications.ReminderManager;
import com.example.hours.utils.App;

import java.text.DateFormatSymbols;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReportManager extends BroadcastReceiver {
    private static Totals sMonthTotals;
    private static Totals sMonthRemaining;
    private static HoursManager sHoursManager;
    private static ArrayList<DailyReport> sDailyReports;
    private static int sCurrentMonth;
    private static int sCurrentYear;

    public static void init(){
        sMonthTotals = new Totals();
        sMonthRemaining= new Totals();
        sHoursManager = HoursManager.getInstance();
        sDailyReports = new ArrayList<>();
        setCurrentMonth();
    }


    public static String getCurrentMonthText(){
        // TODO: fix function to return string in the correct language
        Locale loc = App.getLocale();
        DateFormatSymbols dfs = new DateFormatSymbols().getInstance(loc);
        String[] months = dfs.getMonths();
        String month = months[sCurrentMonth-1];
        return month + " " + sCurrentYear;
    }

    public static CursorLoader getLoader(Context context){
        Uri uri = HoursProviderContract.DailyReports.CONTENT_URI;
        String[] reportsColumns = {
                HoursProviderContract.DailyReports._ID,
                HoursProviderContract.DailyReports.COLUMN_DATE,
                HoursProviderContract.DailyReports.COLUMN_ARRIVAL,
                HoursProviderContract.DailyReports.COLUMN_EXIT
        };
        String yearAndMonth = String.format("%04d%02d", sCurrentYear, sCurrentMonth );
        String selection = "substr(" + HoursProviderContract.DailyReports.COLUMN_DATE + ", 1, 6) == '" + yearAndMonth + "'";
        String reportOrderBy = HoursProviderContract.DailyReports.COLUMN_DATE + " ASC";
        return new CursorLoader(context, uri, reportsColumns, selection, null, reportOrderBy);
    }


    public static void setCurrentMonth(){
        sCurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        sCurrentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    public static void decreaseMonth(){
        sCurrentMonth = (sCurrentMonth  + 12 - 1) % 12;
        sCurrentMonth = (sCurrentMonth == 0) ? 12 : sCurrentMonth;
        sCurrentYear = (sCurrentMonth == 12) ? sCurrentYear - 1 : sCurrentYear;
    }

    public static void increaseMonth(){
        sCurrentMonth = (sCurrentMonth + 1) % 13;
        sCurrentMonth = (sCurrentMonth == 0) ? 1 : sCurrentMonth;
        sCurrentYear = (sCurrentMonth == 1) ? sCurrentYear + 1 : sCurrentYear;
    }

    public static void updateTotals(ArrayList<DailyReport> reports){
        sDailyReports = reports;
        sMonthTotals.clear();
        sMonthRemaining.clear();
        if(sDailyReports != null)
        {
            for (DailyReport report: sDailyReports) {
                UserInfo userInfo = new UserInfo();
                userInfo.arrivalTime = report.getArrival();
                userInfo.exitTime = report.getExit();
                CalcInfo calcInfo = sHoursManager.CalcDayWithExit(userInfo).calcInfo;
                sMonthTotals.zeroHours = sMonthTotals.zeroHours.add(calcInfo.totalTime.zeroHours);
                sMonthTotals.additionalHours = sMonthTotals.additionalHours.add(calcInfo.totalTime.additionalHours);
                sMonthTotals.additional125Hours = sMonthTotals.additional125Hours.add(calcInfo.totalTime.additional125Hours);
                sMonthTotals.additional150Hours = sMonthTotals.additional150Hours.add(calcInfo.totalTime.additional150Hours);
                sMonthTotals.unpaidAbsence = sMonthTotals.unpaidAbsence.add(calcInfo.totalTime.unpaidAbsence);
                sMonthTotals.globalAbsence = sMonthTotals.globalAbsence.add(calcInfo.totalTime.globalAbsence);
                sMonthTotals.regularHours = sMonthTotals.regularHours.add(calcInfo.totalTime.total);
            }

            if(sMonthTotals.globalAbsence.greaterThanZero())
            {
                // Taking zero hours
                if(sMonthTotals.globalAbsence.equalsOrLessThan(sMonthTotals.zeroHours)){
                    sMonthRemaining.zeroHours = sMonthTotals.zeroHours.sub(sMonthTotals.globalAbsence);
                    sMonthRemaining.globalAbsence = new Timestamp(0);
                }
                else
                {
                    sMonthRemaining.zeroHours = new Timestamp(0);
                    sMonthRemaining.globalAbsence = sMonthTotals.globalAbsence.sub(sMonthTotals.zeroHours);

                    // Taking additional hours
                    if(sMonthRemaining.globalAbsence.greaterThanZero())
                    {
                        if(sMonthRemaining.globalAbsence.equalsOrLessThan(sMonthTotals.additionalHours)){
                            sMonthRemaining.additionalHours = sMonthTotals.additionalHours.sub(sMonthRemaining.globalAbsence);
                            sMonthRemaining.globalAbsence = new Timestamp(0);
                        }
                        else
                        {
                            sMonthRemaining.additionalHours = new Timestamp(0);
                            sMonthRemaining.globalAbsence = sMonthTotals.globalAbsence.sub(sMonthTotals.additionalHours);
                        }
                    }
                }
            }
        }
    }



    public static void updateReportArrival(String strDate, int hour, int minute) {
        // TODO: call content provider for inserting / updating row
    }

    public static void updateReportExit(String strDate, int hour, int minute) {
        // TODO: call content provider for inserting / updating row
    }

    public static String getExtraData(ReminderManager.ReminderType_e type){
        String extraData = "";
        switch (type) {
            case ENTER_ARRIVAL:
                break;
            case ENTER_EXIT:
                break;
            case END_OF_MONTH:
                extraData = String.format("נותרו %s שעות אפס לא מנוצלות החודש", getRemainingZeroHours());
                break;
            case MOTHERS_TRANSPORT:
                break;
            case AFTERNOON_TRANSPORT:
                break;
            case EVENING_TRANSPORT:
                break;
            case NIGHT_TRANSPORT:
                break;
            case LUNCH_BREAK:
                break;
            case EVENING_BREAK:
                break;
            case COUNT:
                break;
        }
        return extraData;
    }

    public static String getRegularHours(){
        return sMonthTotals.regularHours.toString();
    }

    public static String getZeroHours() {
        return sMonthTotals.zeroHours.toString();
    }

    public static String getAdditionalHours() {
        return sMonthTotals.additionalHours.toString();
    }

    public static String getAdditional125Hours() {
        return sMonthTotals.additional125Hours.toString();
    }

    public static String getAdditional150Hours() {
        return sMonthTotals.additional150Hours.toString();
    }

    public static String getGlobalAbsence() {
        return sMonthTotals.globalAbsence.toString();
    }

    public static String getUnpaidAbsence() {
        return sMonthTotals.unpaidAbsence.toString();
    }

    public static String getRemainingGlobalAbsence() {
        return sMonthRemaining.globalAbsence.toString();
    }

    public static String getRemainingZeroHours() {
        return sMonthRemaining.zeroHours.toString();
    }

    public static String getRemainingAdditionalHours() {
        return sMonthRemaining.additionalHours.toString();
    }

    public static void updateTotals(Cursor cursor) {
        if(sDailyReports == null)
            sDailyReports = new ArrayList<>();
        sDailyReports.clear();
        while(cursor.moveToNext()){
            DailyReport report = new DailyReport();
            int datePos = cursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_DATE);
            int arrivalPos = cursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_ARRIVAL);
            int exitPos = cursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_EXIT);
            try {
                report.setDate((new SimpleDateFormat("yyyyMMdd")).parse(cursor.getString(datePos)));
            }
            catch (ParseException ex){
                report.setDate(new Date(2022 - 1900, 1, 1));
            }
            report.setArrival(new Timestamp(cursor.getString(arrivalPos)));
            report.setExit(new Timestamp(cursor.getString(exitPos)));
            sDailyReports.add(report);
        }

        updateTotals(sDailyReports);
    }

    public static void clearReports() {
        sDailyReports.clear();
        sMonthTotals.clear();
        sMonthRemaining.clear();
    }

    public static ArrayList<DailyReport> getReports() {
        return sDailyReports;
    }

    public static int getYear() {
        return sCurrentYear;
    }

    public static int getMonth() {
        return sCurrentMonth;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ReminderManager.ReminderType_e type = (ReminderManager.ReminderType_e) intent.getSerializableExtra(EXTRA_PARAM);
        String extraText = intent.getStringExtra(EXTRA_EXTRA_TEXT);

        int year = Year.now().getValue();
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String strDate = String.format("%04d%02d%02d", year, month, day);

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);

        switch(type){
            case ENTER_ARRIVAL:
                ReportManager.updateReportArrival(strDate, hour, minute);
                break;
            case ENTER_EXIT:
                ReportManager.updateReportExit(strDate, hour, minute);
                break;
            case END_OF_MONTH:
                // TODO: create notification tomorrow for zero hours
                long tomorrow = 0;
                ReminderManager.updateNotification(context, type, getRemainingZeroHours(), tomorrow, true);
                break;
            case MOTHERS_TRANSPORT:
                // TODO: create notification in 1 minute for transportation
                break;
            case AFTERNOON_TRANSPORT:
                // TODO: create notification in 1 minute for transportation
                break;
            case EVENING_TRANSPORT:
                // TODO: create notification in 1 minute for transportation
                break;
            case NIGHT_TRANSPORT:
                // TODO: create notification in 1 minute for transportation
                break;
            case LUNCH_BREAK:
                // TODO: create notification in 1 minute for transportation
                break;
            case EVENING_BREAK:
                // TODO: create notification in 1 minute for transportation
                break;
        }

    }
}
