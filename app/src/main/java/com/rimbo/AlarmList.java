package com.rimbo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*

    In dieser Klasse wird die Datenbank ausgelesen und dann geschaut welcher Timer der nächste ist, also als nächster starten sollte.
    Für diesen wird dann ein Alarm Manager erstellt und somit wird der Alarm zur richtigen Zeit gestartet.

 */


public class AlarmList {
    /*----------------------------------
                start alarm
    ----------------------------------*/
    /*
        Alle Reminder auslesen und die mit Datum und Zeit in allDates Liste speichern
     */
    public void startAlarm(Context context) {
        SQLite db = new SQLite(context);
        List<Reminder> allReminder = db.getAllReminder();
        List<Date> allDates = new ArrayList<>();
        Date minDate = new Date();
        for (int i = 0; i < allReminder.size(); i++) {
            Date date = null;
            if (!allReminder.get(i).getTime().equals("")) {
                String reminderDateTime = allReminder.get(i).getDate() + " " + allReminder.get(i).getTime();
                try {
                    date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(reminderDateTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (date != null) {
                allDates.add(date);
            }
        }

        /*
            Das kleinste Datum ausssuchen und solange weiter gehen bis das Datum grösser ist als der jetztige Zeitpunkt, dann Alarm starten
        */
        if (allDates.size() != 0) {

            minDate = Collections.min(allDates);
            Collections.sort(allDates);
            Date currentTime = Calendar.getInstance().getTime();

            for (Date date : allDates) {

                if (date.after(currentTime)) {
                    for (Reminder reminder : allReminder) {
                        Date reminderDate = null;
                        if (!reminder.getTime().equals("")) {
                            try {
                                reminderDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(reminder.getDate() + " " + reminder.getTime());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            if (reminderDate.equals(date)) {
                                Intent intent;
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                                intent = new Intent(context, AlarmReceiver.class);
                                if (reminder.getNotification().equals("alarm")) {
                                    intent.putExtra("type", "alarm");
                                    intent.putExtra("date", reminderDate);
                                    intent.putExtra("name", reminder.getName());
                                    intent.putExtra("id", reminder.getId());
                                } else {
                                    intent.putExtra("type", "notification");
                                    intent.putExtra("date", reminderDate);
                                    intent.putExtra("name", reminder.getName());
                                    intent.putExtra("id", reminder.getId());
                                }
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 122, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                context.sendBroadcast(intent);

                                Long millis = reminderDate.getTime() - currentTime.getTime();
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(reminderDate);
                                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                            }
                        }
                    }
                }
            }
        }
    }
}
