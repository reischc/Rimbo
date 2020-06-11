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
    Date helperTime = currentTime;

    Uri noti;
    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {

        String var = intent.getStringExtra("type");
        Date date = (Date) intent.getSerializableExtra("date");
        helperTime.setTime(currentTime.getTime() - 20000);
        Date beforeTimer = helperTime;
        if (date.after(beforeTimer)) {
            helperTime.setTime(currentTime.getTime() + 40000);
            Date afterTimer = helperTime;
            intent.removeExtra("type");
            if (date.before(afterTimer)) {

                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(5000);

                Notification notification = new Notification.Builder(context)
                        .setContentTitle("Alarm is ON")
                        .setContentText("Alarm is daaaaaa")
                        .setSmallIcon(R.drawable.rimbo_logo).build();

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, notification);


                if (var.equals("alarm")) {
                    noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                } else {
                    noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }

                mp = MediaPlayer.create(context, noti);
                startAlarm();

                Intent i = new Intent();
                i.setClassName("com.rimbo", "com.rimbo.NewReminder");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                stopAlarm();
            }
        }
    }

    private void startAlarm() {
        mp.start();
    }

    public void stopAlarm() {
        mp.stop();
    }

}
