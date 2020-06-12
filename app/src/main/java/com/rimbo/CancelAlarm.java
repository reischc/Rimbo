package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/*

    In dieser Klasse wird der Alarm abgefangen und kann mit einem Knopfdruck beendet werden.

 */

public class CancelAlarm extends AppCompatActivity implements View.OnClickListener {
    /* all GUI elements */
    private Button btnStopAlarm;

    /* all other elements */
    private AlarmReceiver alarmReceiver = new AlarmReceiver();
    private List<Reminder> allReminder = new ArrayList<>();
    private String name;
    private int id;

    private TextView txtName;
    private TextView txtDateTime;


    private int idDB = 0;
    private String nameDB = "";
    private String date = "";
    private String time = "";
    private String notification = "";
    private String locationStreet = "";
    private String locationPlace = "";
    private String vehicle = "";
    private String importance = "";

    /* objects from other classes */
    AlarmList alarmList = new AlarmList();
    SQLite db = new SQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_alarm);

        //get elements with id
        btnStopAlarm = (Button) findViewById(R.id.btnStopAlarm);

        txtName = (TextView) findViewById(R.id.txtName);
        txtDateTime = (TextView) findViewById(R.id.txtDateTime);

        //set click listeners
        btnStopAlarm.setOnClickListener(this);

        //get intent extras
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getIntExtra("id", 0);

        loadDataOfReminder();
    }

    @Override
    public void onClick(View v) {
        alarmReceiver.stopAlarm();
        db.deleteReminder(id);
        alarmList.startAlarm(this);
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
        finish();
    }
    public void loadDataOfReminder() {
        Intent intent = getIntent();
        nameDB = intent.getStringExtra("name");
        for (Reminder reminder : allReminder) {
            if (nameDB.equals(reminder.getName())) {
                idDB = reminder.getId();
                date = reminder.getDate();
                time = reminder.getTime();
                notification = reminder.getNotification();
                locationStreet = reminder.getLocationStreet();
                locationPlace = reminder.getLocationPlace();
                vehicle = reminder.getVehicle();
                importance = reminder.getImportanceLevel();
            }
        }
        txtName.setText(nameDB);
    }
    public void loadReminder(){
        SQLite db = new SQLite(this);
        allReminder = db.getAllReminder();
    }


}