package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DeatilsReminder extends AppCompatActivity {

    private TextView txtName2;
    private TextView txtDate2;
    private TextView txtTime2;
    private TextView txtNotification2;
    private TextView txtLocation2;
    private TextView txtLocation3;
    private TextView txtVehicle2;
    private TextView txtImportance2;

    private LinearLayout layoutName;
    private LinearLayout layoutDate;
    private LinearLayout layoutTime;
    private LinearLayout layoutNotification;
    private LinearLayout layoutLocation;
    private LinearLayout layoutLocation2;
    private LinearLayout layoutVehicle;
    private LinearLayout layoutImportance;

    private List<Reminder> allReminder = new ArrayList<>();

    private int id = 0;
    private String name = "";
    private String date = "";
    private String time = "";
    private String notification = "";
    private String locationStreet = "";
    private String locationPlace = "";
    private String vehicle = "";
    private String importance = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_reminder);

        txtName2 = (TextView) findViewById(R.id.txtName2);
        txtDate2 = (TextView) findViewById(R.id.txtDate2);
        txtTime2 = (TextView) findViewById(R.id.txtTime2);
        txtNotification2 = (TextView) findViewById(R.id.txtNotification2);
        txtLocation2 = (TextView) findViewById(R.id.txtLocation2);
        txtLocation3 = (TextView) findViewById(R.id.txtLocation3);
        txtVehicle2 = (TextView) findViewById(R.id.txtVehicle2);
        txtImportance2 = (TextView) findViewById(R.id.txtImportance2);

        layoutName = (LinearLayout) findViewById(R.id.layoutName);
        layoutDate = (LinearLayout) findViewById(R.id.layoutDate);
        layoutTime = (LinearLayout) findViewById(R.id.layoutTime);
        layoutNotification = (LinearLayout) findViewById(R.id.layoutNotification);
        layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
        layoutLocation2 = (LinearLayout) findViewById(R.id.layoutLocation2);
        layoutVehicle = (LinearLayout) findViewById(R.id.layoutVehicle);
        layoutImportance = (LinearLayout) findViewById(R.id.layoutImportance);

        loadReminder();
        loadDataOfReminder();


    }
    public void loadDataOfReminder() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        for (Reminder reminder : allReminder) {
            if (name.equals(reminder.getName())) {
                id = reminder.getId();
                date = reminder.getDate();
                time = reminder.getTime();
                notification = reminder.getNotification();
                locationStreet = reminder.getLocationStreet();
                locationPlace = reminder.getLocationPlace();
                vehicle = reminder.getVehicle();
                importance = reminder.getImportanceLevel();
            }
        }
        txtName2.setText(name);
        txtDate2.setText(date);
        txtTime2.setText(time);
        txtNotification2.setText(notification);
        txtLocation2.setText(locationStreet);
        txtLocation3.setText(locationPlace);
        txtVehicle2.setText(vehicle);
        txtImportance2.setText(importance);
    }

    public void loadReminder() {
        SQLite db = new SQLite(this);
        allReminder = db.getAllReminder();
    }
}