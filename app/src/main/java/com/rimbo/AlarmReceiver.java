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

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {
    /* all normal elements */
    private Date currentTime = Calendar.getInstance().getTime();
    private Date helperTime = currentTime;
    private String reminderName;
    private int id;
    private String notificationType;

    /* Audio elements */
    private static Uri noti;
    private static MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {

        notificationType = intent.getStringExtra("type");
        Date date = (Date) intent.getSerializableExtra("date");
        reminderName = intent.getStringExtra("name");
        id = intent.getIntExtra("id", 0);
        helperTime.setTime(currentTime.getTime() - 5000);
        Date beforeTimer = helperTime;
        if (date.after(beforeTimer)) {
            helperTime.setTime(currentTime.getTime() + 10000);
            Date afterTimer = helperTime;
            intent.removeExtra("type");
            if (date.before(afterTimer)) {

                Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(5000);

                Notification notification = new Notification.Builder(context)
                        .setContentTitle(reminderName)
                        .setContentText("Alarm is daaaaaa")
                        .setSmallIcon(R.drawable.rimbo_logo).build();

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notification.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, notification);


                if (notificationType.equals("alarm")) {
                    noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                } else {
                    noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }

                mp = MediaPlayer.create(context, noti);
                startAlarm();

                if (notificationType.equals("alarm")) {
                    Intent i = new Intent();
                    i.setClassName("com.rimbo", "com.rimbo.CancelAlarm");
                    i.putExtra("name", reminderName);
                    i.putExtra("id", id);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
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
