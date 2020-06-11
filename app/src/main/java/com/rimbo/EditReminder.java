package com.rimbo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EditReminder extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    /*------------------------
          all GUI elements
     -----------------------*/
    private Button btnBack;
    private Button btnUpdate;

    private ImageButton btnNotification;
    private ImageButton btnAlarm;
    private ImageButton btnWalking;
    private ImageButton btnBycicle;
    private ImageButton btnCar;
    private ImageButton btnTrain;
    private ImageButton btnNormal;
    private ImageButton btnImportant;
    private ImageButton btnVeryImportant;

    private EditText txtName;
    private EditText txtLocationStreet;
    private EditText txtLocationPlace;
    private TextView txtDate;
    private TextView txtTime;

    private Switch switchDate;
    private Switch switchTime;
    private Switch switchLocation;
    private Switch switchVehicle;

    private LinearLayout layoutTime;
    private LinearLayout layoutNotification;
    private LinearLayout layoutNotificationBtn;
    private LinearLayout layoutVehicle;
    private LinearLayout layoutVehicleBtn;
    private LinearLayout layoutDate;
    private LinearLayout layoutLocation;
    private LinearLayout layoutLocationInput;

    /* all elements from reminder */
    private int id = 0;
    private String name = "";
    private String date = "";
    private String time = "";
    private String notification = "notification";
    private String locationStreet = "";
    private String locationPlace = "";
    private String vehicle = "walking";
    private String importance = "normal";

    List<Reminder> allReminder = new List<Reminder>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Reminder> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(Reminder reminder) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends Reminder> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends Reminder> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Reminder get(int index) {
            return null;
        }

        @Override
        public Reminder set(int index, Reminder element) {
            return null;
        }

        @Override
        public void add(int index, Reminder element) {

        }

        @Override
        public Reminder remove(int index) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<Reminder> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Reminder> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<Reminder> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    /* objects from other classes */
    AlarmList alarmList = new AlarmList();

    /* Date and Time */
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView mDisplayTime;
    private Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        //get all items by ID
        btnBack = (Button) findViewById(R.id.btnBack);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnNotification = (ImageButton) findViewById(R.id.btnNotification);
        btnAlarm = (ImageButton) findViewById(R.id.btnAlarm);
        btnWalking = (ImageButton) findViewById(R.id.btnWalking);
        btnBycicle = (ImageButton) findViewById(R.id.btnBycicle);
        btnCar = (ImageButton) findViewById(R.id.btnCar);
        btnTrain = (ImageButton) findViewById(R.id.btnTrain);
        btnNormal = (ImageButton) findViewById(R.id.btnNormal);
        btnImportant = (ImageButton) findViewById(R.id.btnImportant);
        btnVeryImportant = (ImageButton) findViewById(R.id.btnVeryImportant);

        txtName = (EditText) findViewById(R.id.txtName);
        txtLocationStreet = (EditText) findViewById(R.id.txtLocationStreet);
        txtLocationPlace = (EditText) findViewById(R.id.txtLocationPlace);
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);

        switchDate = (Switch) findViewById(R.id.switchDate);
        switchTime = (Switch) findViewById(R.id.switchTime);
        switchLocation = (Switch) findViewById(R.id.switchLocation);
        switchVehicle = (Switch) findViewById(R.id.switchVehicle);

        layoutTime = (LinearLayout) findViewById(R.id.layoutTime);
        layoutNotification = (LinearLayout) findViewById(R.id.layoutNotification);
        layoutNotificationBtn = (LinearLayout) findViewById(R.id.layoutNotificationBtn);
        layoutVehicle = (LinearLayout) findViewById(R.id.layoutVehicle);
        layoutVehicleBtn = (LinearLayout) findViewById(R.id.layoutVehicleBtn);
        layoutDate = (LinearLayout) findViewById(R.id.layoutDate);
        layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
        layoutLocationInput = (LinearLayout) findViewById(R.id.layoutLocationInput);

        //load all listeners
        btnBack.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnNotification.setOnClickListener(this);
        btnAlarm.setOnClickListener(this);
        btnWalking.setOnClickListener(this);
        btnBycicle.setOnClickListener(this);
        btnCar.setOnClickListener(this);
        btnTrain.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnImportant.setOnClickListener(this);
        btnVeryImportant.setOnClickListener(this);

        switchDate.setOnCheckedChangeListener(this);
        switchTime.setOnCheckedChangeListener(this);
        switchLocation.setOnCheckedChangeListener(this);
        switchVehicle.setOnCheckedChangeListener(this);

        loadReminder();
        loadDataOfReminder();
        activateNeededElements();

        /* Date and Time */
        mDisplayDate = (TextView) findViewById(R.id.txtDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar cal = java.util.Calendar.getInstance();
                int year = cal.get(java.util.Calendar.YEAR);
                int month = cal.get(java.util.Calendar.MONTH);
                int day = cal.get(java.util.Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditReminder.this, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "." + month + "." + year;
                mDisplayDate.setText(date);
            }
        };

        mDisplayTime = (TextView) findViewById(R.id.txtTime);

        java.util.Calendar calendar = java.util.Calendar.getInstance();

        final int hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mDisplayTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();
            }
        });

    }

    /*---------------------------------
          load the data of reminder
     ---------------------------------*/
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

        txtName.setText(name);
    }

    /*----------------------------------
        activate all needed elements
     ---------------------------------*/
    public void activateNeededElements() {
        if (!date.equals("")) {
            switchDate.setChecked(true);
            txtDate.setText(date);
            if (!time.equals("")) {
                switchTime.setChecked(true);
                txtTime.setText(time);
                if (!notification.equals("notification")) {
                    btnAlarm.callOnClick();
                }
            }
        }
        if (!locationStreet.equals("")) {
            switchLocation.setChecked(true);
            txtLocationStreet.setText(locationStreet);
            txtLocationPlace.setText(locationPlace);
            switchVehicle.setChecked(true);
            switch (vehicle) {
                case "walking":
                    btnWalking.callOnClick();
                    break;
                case "bycicle":
                    btnBycicle.callOnClick();
                    break;
                case "car":
                    btnCar.callOnClick();
                    break;
                case "train":
                    btnTrain.callOnClick();
                    break;
                default:
                    break;
            }
        }
        switch (importance) {
            case "normal":
                btnNormal.callOnClick();
                break;
            case "important":
                btnImportant.callOnClick();
                break;
            case "very important":
                btnVeryImportant.callOnClick();
                break;
            default:
                break;
        }
    }

    /*----------------------------------
            load all reminders
     ---------------------------------*/
    public void loadReminder() {
        SQLite db = new SQLite(this);
        allReminder = db.getAllReminder();
    }

    /*----------------------------------
                Switches
     ---------------------------------*/
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.switchDate:
                if (buttonView.isChecked()) {
                    txtDate.setVisibility(View.VISIBLE);
                    layoutDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                    layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    txtDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    if (txtDate.getText() == "") {
                        txtDate.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                layoutTime.setVisibility(View.VISIBLE);
                                layoutDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                                layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                                txtDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                            }
                        });
                    } else {
                        layoutTime.setVisibility(View.VISIBLE);
                        layoutDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                        layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                        txtDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    }
                } else {
                    txtDate.setVisibility(View.GONE);
                    txtTime.setVisibility(View.GONE);
                    layoutTime.setVisibility(View.GONE);
                    layoutDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                    txtDate.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));

                    layoutNotification.setVisibility(View.GONE);
                    layoutNotificationBtn.setVisibility(View.GONE);
                    switchTime.setChecked(false);

                }
                break;
            case R.id.switchTime:
                if (buttonView.isChecked()) {
                    txtTime.setVisibility(View.VISIBLE);
                    layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                    txtTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    if (txtTime.getText() == "") {
                        txtTime.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                layoutNotification.setVisibility(View.VISIBLE);
                                layoutNotificationBtn.setVisibility(View.VISIBLE);
                                layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                                txtTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                            }
                        });
                    } else {
                        layoutNotification.setVisibility(View.VISIBLE);
                        layoutNotificationBtn.setVisibility(View.VISIBLE);
                        layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                        txtTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    }
                } else {
                    txtTime.setVisibility(View.GONE);
                    layoutNotification.setVisibility(View.GONE);
                    layoutNotificationBtn.setVisibility(View.GONE);
                    layoutTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    txtTime.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                }
                break;
            case R.id.switchLocation:
                if (buttonView.isChecked()) {
                    layoutLocationInput.setVisibility(View.VISIBLE);
                    layoutLocation.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                    layoutLocationInput.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    if (txtLocationStreet.getText().toString().matches("") && txtLocationPlace.getText().toString().matches("")) {
                        txtLocationStreet.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                layoutVehicle.setVisibility(View.VISIBLE);
                                layoutVehicleBtn.setVisibility(View.VISIBLE);
                            }
                        });
                    } else {
                        layoutVehicle.setVisibility(View.VISIBLE);
                        layoutVehicleBtn.setVisibility(View.VISIBLE);
                        layoutLocationInput.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_none));
                    }
                } else {
                    layoutLocationInput.setVisibility(View.GONE);
                    layoutLocation.setBackground(ContextCompat.getDrawable(EditReminder.this, R.drawable.border_bottom));
                    layoutVehicle.setVisibility(View.GONE);
                    layoutVehicleBtn.setVisibility(View.GONE);
                }
                break;
            case R.id.switchVehicle:
                if (buttonView.isChecked()) {
                    layoutVehicleBtn.setVisibility(View.VISIBLE);
                } else {
                    layoutVehicleBtn.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    /*--------------------------------------------
               function buttons
    --------------------------------------------*/
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNotification:
                notification = "notification";
                btnNotification.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnNotification.setImageResource(R.drawable.notification_white);
                btnAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnAlarm.setImageResource(R.drawable.alarm);
                break;
            case R.id.btnAlarm:
                notification = "alarm";
                btnAlarm.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnAlarm.setImageResource(R.drawable.alarm_white);
                btnNotification.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnNotification.setImageResource(R.drawable.notification);
                break;
            case R.id.btnWalking:
                vehicle = "walking";
                btnWalking.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnWalking.setImageResource(R.drawable.walking_white);
                btnBycicle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnCar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnTrain.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnBycicle.setImageResource(R.drawable.bycicle);
                btnCar.setImageResource(R.drawable.car);
                btnTrain.setImageResource(R.drawable.train);
                break;
            case R.id.btnBycicle:
                vehicle = "bycicle";
                btnBycicle.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnBycicle.setImageResource(R.drawable.bycicle_white);
                btnWalking.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnCar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnTrain.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnWalking.setImageResource(R.drawable.walking);
                btnCar.setImageResource(R.drawable.car);
                btnTrain.setImageResource(R.drawable.train);
                break;
            case R.id.btnCar:
                vehicle = "car";
                btnCar.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnCar.setImageResource(R.drawable.car_white);
                btnWalking.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnBycicle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnTrain.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnWalking.setImageResource(R.drawable.walking);
                btnBycicle.setImageResource(R.drawable.bycicle);
                btnTrain.setImageResource(R.drawable.train);
                break;
            case R.id.btnTrain:
                vehicle = "train";
                btnTrain.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnTrain.setImageResource(R.drawable.train_white);
                btnWalking.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnBycicle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnCar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnWalking.setImageResource(R.drawable.walking);
                btnBycicle.setImageResource(R.drawable.bycicle);
                btnCar.setImageResource(R.drawable.car);
                break;
            case R.id.btnNormal:
                importance = "normal";
                btnNormal.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnNormal.setImageResource(R.drawable.exclamation1_white);
                btnImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnVeryImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnImportant.setImageResource(R.drawable.exclamation2);
                btnVeryImportant.setImageResource(R.drawable.exclamation3);
                break;
            case R.id.btnImportant:
                importance = "important";
                btnImportant.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnImportant.setImageResource(R.drawable.exclamation2_white);
                btnNormal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnVeryImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnNormal.setImageResource(R.drawable.exclamation1);
                btnVeryImportant.setImageResource(R.drawable.exclamation3);
                break;
            case R.id.btnVeryImportant:
                importance = "very important";
                btnVeryImportant.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnVeryImportant.setImageResource(R.drawable.excla3_white);
                btnNormal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnNormal.setImageResource(R.drawable.exclamation1);
                btnImportant.setImageResource(R.drawable.exclamation2);
                break;
            case R.id.btnBack:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnUpdate:
                if (txtName.getText().toString().matches("")) {
                    Toast.makeText(this, "You must set a name!", Toast.LENGTH_SHORT).show();
                } else {
                    name = txtName.getText().toString();
                    date = txtDate.getText().toString();
                    time = txtTime.getText().toString();
                    locationStreet = txtLocationStreet.getText().toString();
                    locationPlace = txtLocationPlace.getText().toString();

                    //update reminder in the sqlite file
                    Reminder reminder = new Reminder(id, name, date, time, notification, locationStreet, locationPlace, vehicle, importance, false);
                    SQLite db = new SQLite(this);
                    db.updateReminder(reminder);

                    alarmList.startAlarm(this);

                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public static class DeatilsReminder extends AppCompatActivity {

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
}
