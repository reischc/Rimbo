package com.rimbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewReminder;
    private List<Reminder> allReminder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //find items by id
        Button btnCalendar = (Button)findViewById(R.id.btnCalendar);
        this.listViewReminder = (ListView)findViewById(R.id.listViewReminder);


        TextView theDate = (TextView) findViewById(R.id.date);


        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        if (date == null){
            theDate.setText("Today, "+ formatter.format(calendar.getTime()));
        }
        else{
            theDate.setText(date);
        }

        //set listView mod
        this.listViewReminder.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //activate click Listener
        btnCalendar.setOnClickListener(this);

        this.listViewReminder.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navCalendar:
                        break;
                    case R.id.navAddReminder:
                        Intent intent1 = new Intent(getApplicationContext(), NewReminder.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.navSettings:
                        Intent intent2 = new Intent(getApplicationContext(), Settings.class);
                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        loadReminder();
        initListViewData();
    }

    public void initListViewData() {
        List<String> reminderNameList = new ArrayList<>();
        String name;
        if (allReminder.size() != 0) {
            for (int i = 0; i+1 <= allReminder.size(); i++) {
                name = allReminder.get(i).getName();
                reminderNameList.add(name);
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked , reminderNameList);
        this.listViewReminder.setAdapter(arrayAdapter);

    }

    public void loadReminder() {
        SQLite db = new SQLite(this);
        allReminder = db.getAllReminder();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), Calendar.class);
        startActivity(intent);
        finish();
    }

}

