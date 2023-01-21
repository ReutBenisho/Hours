package com.example.hours.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class ReminderReceiver extends BroadcastReceiver {
    public static String EXTRA_PARAM = "com.example.hours.extra.EXTRA_PARAM";
    public static String EXTRA_IS_ACTIVE = "com.example.hours.extra.EXTRA_IS_ACTIVE";
    public static String EXTRA_EXTRA_TEXT = "com.example.hours.extra.EXTRA_EXTRA_TEXT";

    // This function is called when alarmManager timeouted on an pendingIntent
    @Override
    public void onReceive(Context context, Intent intent) {
        ReminderManager.ReminderType_e type = (ReminderManager.ReminderType_e) intent.getSerializableExtra(EXTRA_PARAM);
        String extraText = intent.getStringExtra(EXTRA_EXTRA_TEXT);
        ReminderManager.Data notificationData = ReminderManager.getIntent(context, type, extraText);
        ReminderNotification.notify(context,
                notificationData.title,
                notificationData.text,
                notificationData.intent,
                notificationData.action,
                notificationData.actionIntent);
    }
}
