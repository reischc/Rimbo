package com.rimbo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    Date currentTime = Calendar.getInstance().getTime();

    @Override
    public void onReceive(Context context, Intent intent) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(5000);

        Notification notification = new Notification.Builder(context)
                .setContentTitle("Alarm is ON")
                .setContentText("Alarm is daaaaaa")
                .setSmallIcon(R.drawable.rimbo_logo).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);

        String var = intent.getStringExtra("type");
        Date date = (Date) intent.getSerializableExtra("date");
        intent.removeExtra("type");

        Uri noti;
        if (var.equals("alarm")) {
            noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        } else {
            noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        if (date.equals(currentTime)) {
            MediaPlayer mp = MediaPlayer.create(context, noti);
            mp.start();
        }

    }
}
