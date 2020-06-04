package com.rimbo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());*/

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(5000);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Alarm is ON")
                .setContentText("Alarm is daaaaaa")
                .setSmallIcon(R.drawable.rimbo_logo).build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags|= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

        Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        Ringtone r = RingtoneManager.getRingtone(context,noti);
        r.play();
    }
}
