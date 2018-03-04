package com.way.mat.templatemvp.util;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.way.mat.templatemvp.R;
import com.way.mat.templatemvp.ui.activity.main.MainActivity;

public class NotificationHelper {

    public static Notification makeNotification(Context context, String title, String text) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context, "regular_channel")
                        .setContentIntent(getLaunchIntent(context))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBuilder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        }

        return mBuilder.build();
    }

    private static PendingIntent getLaunchIntent(Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        return resultPendingIntent;
    }

}
