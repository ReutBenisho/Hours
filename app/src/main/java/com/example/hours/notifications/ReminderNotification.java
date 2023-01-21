package com.example.hours.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.hours.R;

public class ReminderNotification {

    private static final String NOTIFICATION_TAG = "Hours Notifications";

    public static void notify(final Context context,
                              final String title,
                              final String text,
                              Intent intent,
                              String action,
                              Intent actionIntent) {

        Bitmap appIcon = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_hours_launcher);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_TAG)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_clock)
                .setContentTitle("Hours Reminder")
                .setContentText(title)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setLargeIcon(appIcon)
                .setTicker("Hours Reminder")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(text)
                        .setBigContentTitle(title)
                        .setSummaryText("Hours Reminder"))
                .setAutoCancel(true)
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                intent,
                                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT));

        if(!action.equals(""))
        {
                builder.addAction(0, action,
                        PendingIntent.getActivity(
                            context,
                            0,
                            actionIntent,
                            PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT));

        }

        notify(context, builder.build());
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.notify(NOTIFICATION_TAG, 0, notification);
        } else {
            nm.notify(NOTIFICATION_TAG.hashCode(), notification);
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     * {@link #notify(Context, String, String, Intent, String, Intent)}.
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            nm.cancel(NOTIFICATION_TAG, 0);
        } else {
            nm.cancel(NOTIFICATION_TAG.hashCode());
        }
    }
}
