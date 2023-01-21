package com.example.hours.notifications;

import static com.example.hours.activities.MainActivity.EXTRA_FRAGMENT_TAG;
import static com.example.hours.notifications.ReminderReceiver.EXTRA_EXTRA_TEXT;
import static com.example.hours.notifications.ReminderReceiver.EXTRA_PARAM;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.example.hours.R;
import com.example.hours.activities.MainActivity;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.fragments.DailyReportFragment;
import com.example.hours.fragments.MonthlySummaryFragment;
import com.example.hours.report.ReportManager;
import com.example.hours.utils.App;
import com.example.hours.utils.SharedPreferencesUtil;

public class ReminderManager {

    public enum ReminderType_e{
        ENTER_ARRIVAL,
        ENTER_EXIT,
        END_OF_MONTH,

        MOTHERS_TRANSPORT,
        AFTERNOON_TRANSPORT,
        EVENING_TRANSPORT,
        NIGHT_TRANSPORT,

        LUNCH_BREAK,
        EVENING_BREAK,

        COUNT
    }

    public static class Data{
        Data(){};
        public String title;
        public String text;
        public Intent intent;
        public String action;
        public Intent actionIntent;
    }

    private static AlarmManager sAlarmManager;

    public static Data getIntent(Context context, ReminderType_e type, String extraText){
        Data data = new Data();
        switch (type) {
            case ENTER_ARRIVAL:
                data.title = "הזן זמן כניסה";
                data.text = "תזכורת להזין שעת כניסה באפליקציה עבור החישוב היומי";
                data.intent = new Intent(context, MainActivity.class);
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, DailyReportFragment.TAG);
                data.intent.putExtra(EXTRA_PARAM, type);
                data.action = "נכנסתי עכשיו";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case ENTER_EXIT:
                data.title = "הזן זמן יציאה";
                data.text = "תזכורת להזין שעת יציאה באפליקציה עבור החישוב היומי";
                data.intent = new Intent(context, MainActivity.class);
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, DailyReportFragment.TAG);
                data.intent.putExtra(EXTRA_PARAM, type);
                data.action = "יצאתי עכשיו";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case END_OF_MONTH:
                data.title = "שעות אפס";
                data.text = extraText + " שעות אפס לא מנוצלות החודש" + "נותרו ";
                data.intent = new Intent(context, MainActivity.class);
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי שוב מחר";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case MOTHERS_TRANSPORT:
                data.title = "להתארגן להסעה";
                data.text = "הסעת אמהות יוצאת בקרוב, לא לשכוח לצאת בזמן";
                data.intent = new Intent();
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי עוד דקה";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case AFTERNOON_TRANSPORT:
                data.title = "להתארגן להסעה";
                data.text = "הסעת צהריים יוצאת בקרוב, לא לשכוח לצאת בזמן";
                data.intent = new Intent();
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי עוד דקה";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case EVENING_TRANSPORT:
                data.title = "להתארגן להסעה";
                data.text = "הסעת ערב יוצאת בקרוב, לא לשכוח לצאת בזמן";
                data.intent = new Intent();
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי עוד דקה";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case NIGHT_TRANSPORT:
                data.title = "להתארגן להסעה";
                data.text = "הסעת לילה יוצאת בקרוב, לא לשכוח לצאת בזמן";
                data.intent = new Intent();
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי עוד דקה";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case LUNCH_BREAK:
                data.title = "הפסקה בקרוב";
                data.text = "הפסקת צהריים מתחילה בעוד 5 דקות, להתארגן לצאת";
                data.intent = new Intent();
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי עוד דקה";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
            case EVENING_BREAK:
                data.title = "הפסקה בקרוב";
                data.text = "הפסקת ערב מתחילה בעוד 5 דקות, להתארגן לצאת";
                data.intent = new Intent();
                data.intent.putExtra(EXTRA_FRAGMENT_TAG, MonthlySummaryFragment.TAG);
                data.action = "הזכר לי עוד דקה";
                data.actionIntent = new Intent(context, ReportManager.class);
                data.actionIntent.putExtra(EXTRA_PARAM, type);
                data.actionIntent.putExtra(EXTRA_EXTRA_TEXT, extraText);
                break;
        }

        return data;
    }

    public static void init(AlarmManager alarmManager){
        sAlarmManager = alarmManager;
    }

    public static void updateNotification(Context context, ReminderType_e type, String extraText, long alarmTime, boolean active){
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra(EXTRA_PARAM, type);
        intent.putExtra(EXTRA_EXTRA_TEXT, extraText);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        if(active)
            sAlarmManager.set(AlarmManager.ELAPSED_REALTIME, alarmTime, pendingIntent);
        else
            sAlarmManager.cancel(pendingIntent);

    }

    public static void updateAll(Context context){

        Timestamp reminderTime = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.default_reminder_time_in_minutes)));
        Timestamp time;
        long minutes;
        long alarmTime;
        boolean isActive;

        //Arrival Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_early_arrival_time)));
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_lunch_break)); // TODO: change to correct notification
        updateNotification(context, ReminderType_e.ENTER_ARRIVAL, "", alarmTime, isActive);

        //Exit Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_exit_time)));
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_lunch_break)); // TODO: change to correct notification
        updateNotification(context, ReminderType_e.ENTER_EXIT, "", alarmTime, isActive);

        //End of month Notification
        //TODO: complete this using new prefernce of days before end of month
        //TODO: need to send in extradata the amount of zero hours remining for usage

        //Mothers transport Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_notify_mothers_transportation)));// TODO: change to correct preference - create new prefernce in default time
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_mothers_transportation));
        updateNotification(context, ReminderType_e.MOTHERS_TRANSPORT, "", alarmTime, isActive);

        //Afternoon transport Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_exit_time)));// TODO: change to correct preference - create new prefernce in default time
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_afternoon_transportation));
        updateNotification(context, ReminderType_e.AFTERNOON_TRANSPORT, "", alarmTime, isActive);

        //Evening transport Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_evening_break_time)));// TODO: change to correct preference - create new prefernce in default time
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_evening_transportation));
        updateNotification(context, ReminderType_e.EVENING_TRANSPORT, "", alarmTime, isActive);

        //Night transport Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_night_break_time)));// TODO: change to correct preference - create new prefernce in default time
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_night_transportation));
        updateNotification(context, ReminderType_e.NIGHT_TRANSPORT, "", alarmTime, isActive);

        //Lunch break Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_lunch_break_time)));
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_lunch_break));
        updateNotification(context, ReminderType_e.LUNCH_BREAK, "", alarmTime, isActive);

        //Evening break Notification
        time = new Timestamp(SharedPreferencesUtil.getString(App.getStr(R.string.pref_default_evening_break_time)));
        minutes = time.sub(reminderTime).getDuration().toMinutes();
        alarmTime = minutes * 60 * 1000;
        isActive = SharedPreferencesUtil.getBoolean(App.getStr(R.string.pref_notify_evening_break));
        updateNotification(context, ReminderType_e.EVENING_BREAK, "", alarmTime, isActive);
    }
}
