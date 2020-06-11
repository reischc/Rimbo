package com.rimbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listViewReminderChecklist;
    private ListView listViewReminderEdit;
    private ListView listViewReminderChecklistTimeless;
    private ListView listViewReminderEditTimeless;
    private Button btnCalendar;
    private Button btnChangeEditChecklist;
    private List<Reminder> allReminder = new ArrayList<>();
    private List<String> reminderNameList = new ArrayList<>();
    private List<String> reminderNameListTimeless = new ArrayList<>();
    private TextView txtTimeless;
    private TextView txtDate;

    private String dateToday;

    SQLite db = new SQLite(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //find items by id
        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnChangeEditChecklist = (Button)findViewById(R.id.btnChangeEditChecklist);
        listViewReminderChecklist = (ListView)findViewById(R.id.listViewReminderChecklist);
        listViewReminderEdit = (ListView)findViewById(R.id.listViewReminderEdit);
        listViewReminderChecklistTimeless = (ListView)findViewById(R.id.listViewReminderChecklistTimeless);
        listViewReminderEditTimeless = (ListView)findViewById(R.id.listViewReminderEditTimeless);

        txtTimeless = (TextView) findViewById(R.id.dateTimeless);
        txtDate = (TextView) findViewById(R.id.date);

        //set title for timeless reminders
        txtTimeless.setText("Timeless Reminders");

        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        if (date == null){
            txtDate.setText("Today, "+ formatter.format(calendar.getTime()));
        }
        else{
            txtDate.setText(date);
        }

        //get the date from calendar
        if (date == null) {
            dateToday = formatter.format(calendar.getTime());
        } else {
            String[] arrayDate = date.split(",");
            System.out.println(arrayDate[0]);
            System.out.println(arrayDate[1]);
            dateToday = arrayDate[1];
            dateToday = dateToday.trim();
        }

        //set listView mod
        listViewReminderChecklist.setChoiceMode(listViewReminderChecklist.CHOICE_MODE_MULTIPLE);
        listViewReminderChecklistTimeless.setChoiceMode(listViewReminderChecklistTimeless.CHOICE_MODE_MULTIPLE);


        //activate click Listener
        btnCalendar.setOnClickListener(this);
        btnChangeEditChecklist.setOnClickListener(this);

        listViewReminderChecklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView reminder = (CheckedTextView) view;
                reminder.setMinWidth(200);
                Context context;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you want to mark this reminder as completed? (completed reminders are deleted)");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //set on edit list item click listener
        listViewReminderEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listViewReminderEdit.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EditReminder.class);
                intent.putExtra("name", item);
                startActivity(intent);
                finish();
            }
        });

        //set on edit list item long click listener
        listViewReminderEdit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listViewReminderEdit.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DetailsReminder.class);
                intent.putExtra("name", item);
                startActivity(intent);
                finish();
                return true;
            }
        });
        //set on edit timeless list item click listener
        listViewReminderEditTimeless.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listViewReminderEditTimeless.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), EditReminder.class);
                intent.putExtra("name", item);
                startActivity(intent);
                finish();
            }
        });
        //set on edit list item long click listener
        listViewReminderEditTimeless.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) listViewReminderEdit.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), DetailsReminder.class);


                intent.putExtra("name", item);
                startActivity(intent);
                finish();
                return true;
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
        String name;
        if (allReminder.size() != 0) {
            for (int i = 0; i+1 <= allReminder.size(); i++) {
                name = allReminder.get(i).getName();
                if (dateToday.equals(allReminder.get(i).getDate())) {
                    reminderNameList.add(name);
                }
                if (allReminder.get(i).getDate().equals("")) {
                    reminderNameListTimeless.add(name);
                }
            }
        }

        ArrayAdapter<String> arrayAdapterChecklist = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked , reminderNameList);
        listViewReminderChecklist.setAdapter(arrayAdapterChecklist);

        ArrayAdapter<String> arrayAdapterEdit = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , reminderNameList);
        listViewReminderEdit.setAdapter(arrayAdapterEdit);

        ArrayAdapter<String> arrayAdapterChecklistTimeless = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, reminderNameListTimeless);
        listViewReminderChecklistTimeless.setAdapter(arrayAdapterChecklistTimeless);

        ArrayAdapter<String> arrayAdapterEditTimeless = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , reminderNameListTimeless);
        listViewReminderEditTimeless.setAdapter(arrayAdapterEditTimeless);
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
        if (v.getId() == R.id.btnCalendar) {
            Intent intent = new Intent(getApplicationContext(), Calendar.class);
            startActivity(intent);
            finish();
        } else {
            if (listViewReminderChecklist.getVisibility() == View.VISIBLE) {
                Toast.makeText(this, "Edit mode!", Toast.LENGTH_SHORT).show();
                listViewReminderChecklist.setVisibility(View.GONE);
                listViewReminderEdit.setVisibility(View.VISIBLE);
                listViewReminderChecklistTimeless.setVisibility(View.GONE);
                listViewReminderEditTimeless.setVisibility(View.VISIBLE);
                btnChangeEditChecklist.setBackgroundResource(R.drawable.checklist);
            } else {
                Toast.makeText(this, "Check list!", Toast.LENGTH_SHORT).show();
                listViewReminderChecklist.setVisibility(View.VISIBLE);
                listViewReminderEdit.setVisibility(View.GONE);
                listViewReminderChecklistTimeless.setVisibility(View.VISIBLE);
                listViewReminderEditTimeless.setVisibility(View.GONE);
                btnChangeEditChecklist.setBackgroundResource(R.drawable.edit);
            }
        }
    }

}

