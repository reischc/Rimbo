package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class CancelAlarm extends AppCompatActivity implements View.OnClickListener {
    /* all GUI elements */
    private Button btnStopAlarm;

    /* all other elements */
    private AlarmReceiver alarmReceiver = new AlarmReceiver();
    private List<Reminder> allReminder = new ArrayList<>();
    private String name;
    private int id;

    /* objects from other classes */
    AlarmList alarmList = new AlarmList();
    SQLite db = new SQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_alarm);

        //get elements with id
        btnStopAlarm = (Button) findViewById(R.id.btnStopAlarm);

        //set click listeners
        btnStopAlarm.setOnClickListener(this);

        //get intent extras
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getIntExtra("id", 0);
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

}