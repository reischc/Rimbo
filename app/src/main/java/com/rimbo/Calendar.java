package com.rimbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        Button btnBack = (Button) findViewById(R.id.buttonBack);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                month = month + 1;
                String stringDayOfMonth;
                String stringMonth;
                int dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK);
                String dayOfWeekString;
                switch (dayOfWeek){
                    case 2:
                        dayOfWeekString = "Monday";
                        break;
                    case 3:
                        dayOfWeekString = "Tuesday";
                        break;
                    case 4:
                        dayOfWeekString = "Wednesday";
                        break;
                    case 5:
                        dayOfWeekString = "Thursday";
                        break;
                    case 6:
                        dayOfWeekString = "Friday";
                        break;
                    case 7:
                        dayOfWeekString = "Saturday";
                        break;
                    default:
                        dayOfWeekString = "Sunday";
                }

                if (dayOfMonth < 10) {
                    stringDayOfMonth = "0"+dayOfMonth;
                } else {
                    stringDayOfMonth = String.valueOf(dayOfMonth);
                }
                if (month < 10) {
                    stringMonth = "0"+month;
                } else {
                    stringMonth = String.valueOf(month);
                }

                String date = dayOfWeekString+", "+ stringDayOfMonth +"."+stringMonth+"."+year;

                Intent intent = new Intent(Calendar.this, MainActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Intent activityCalendarIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activityCalendarIntent); } });
    }
}
