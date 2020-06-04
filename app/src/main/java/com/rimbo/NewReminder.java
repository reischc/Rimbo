package com.rimbo;

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
import android.widget.AutoCompleteTextView;
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

import java.util.Calendar;

public class NewReminder extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    /*------------------------
            all elements
     -----------------------*/
    private Button btnBack;
    private Button btnCreate;

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
    private LinearLayout layoutTime;
    private LinearLayout layoutNotification;
    private LinearLayout layoutNotificationBtn;
    private LinearLayout layoutVehicle;
    private LinearLayout layoutVehicleBtn;
    private LinearLayout layoutDate;
    private LinearLayout layoutLocation;
    private LinearLayout layoutLocationInput;

    private String name = "";
    private String date = "";
    private String time = "";
    private String notification = "notification";
    private String locationStreet = "";
    private String locationPlace = "";
    private String vehicle = "walking";
    private String importance = "normal";


    /* Date and Time */
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView mDisplayTime;
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        //get all items by ID
        btnBack = (Button) findViewById(R.id.btnBack);
        btnCreate = (Button) findViewById(R.id.btnCreate);
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
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtLocationStreet = (EditText) findViewById(R.id.txtLocationStreet);
        txtLocationPlace = (EditText) findViewById(R.id.txtLocationPlace);

        switchDate = (Switch) findViewById(R.id.switchDate);
        switchTime = (Switch) findViewById(R.id.switchTime);
        switchLocation = (Switch) findViewById(R.id.switchLocation);

        layoutTime = (LinearLayout) findViewById(R.id.layoutTime);
        layoutNotification = (LinearLayout) findViewById(R.id.layoutNotification);
        layoutNotificationBtn = (LinearLayout) findViewById(R.id.layoutNotificationBtn);
        layoutVehicle = (LinearLayout) findViewById(R.id.layoutVehicle);
        layoutVehicleBtn = (LinearLayout) findViewById(R.id.layoutVehicleBtn);
        layoutDate = (LinearLayout)  findViewById(R.id.layoutDate);
        layoutLocation = (LinearLayout) findViewById(R.id.layoutLocation);
        layoutLocationInput = (LinearLayout) findViewById(R.id.layoutLocationInput);

        //load all listeners
        btnBack.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
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


        /* Date and Time */
        mDisplayDate = (TextView) findViewById(R.id.txtDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NewReminder.this, mDateSetListener, year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth+ "." +month+"."+year;
                mDisplayDate.setText(date);
            }
        };

        mDisplayTime = (TextView) findViewById(R.id.txtTime);

        Calendar calendar = Calendar.getInstance();

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        mDisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mDisplayTime.setText(hourOfDay+":"+minute);
                    }
                },hour,minute,android.text.format.DateFormat.is24HourFormat(mContext));
                timePickerDialog.show();
            }
        });

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
                    layoutDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                    layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                    txtDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
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
                                layoutDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                                layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                                txtDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                            }
                        });
                    } else {
                        layoutTime.setVisibility(View.VISIBLE);
                        layoutDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                        layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                        txtDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                    }
                } else {
                    txtDate.setVisibility(View.GONE);
                    txtTime.setVisibility(View.GONE);
                    layoutTime.setVisibility(View.GONE);
                    layoutDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                    layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                    txtDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));

                    layoutNotification.setVisibility(View.GONE);
                    layoutNotificationBtn.setVisibility(View.GONE);
                    switchTime.setChecked(false);

                }
                break;
            case R.id.switchTime:
                if (buttonView.isChecked()){
                    txtTime.setVisibility(View.VISIBLE);
                    layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                    txtTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
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
                                layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                                txtTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                            }
                        });
                    } else {
                        layoutNotification.setVisibility(View.VISIBLE);
                        layoutNotificationBtn.setVisibility(View.VISIBLE);
                        layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                        txtTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                    }
                } else {
                    txtTime.setVisibility(View.GONE);
                    layoutNotification.setVisibility(View.GONE);
                    layoutNotificationBtn.setVisibility(View.GONE);
                    layoutTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                    txtTime.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                }
                break;
            case R.id.switchLocation:
                if (buttonView.isChecked()){
                    layoutLocationInput.setVisibility(View.VISIBLE);
                    layoutLocation.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));
                    layoutLocationInput.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
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
                                layoutLocationInput.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));

                            }
                        });
                        txtLocationPlace.addTextChangedListener(new TextWatcher() {
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
                                layoutLocationInput.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));

                            }
                        });
                    } else {
                        layoutVehicle.setVisibility(View.VISIBLE);
                        layoutVehicleBtn.setVisibility(View.VISIBLE);
                        layoutLocationInput.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_none));

                    }
                } else {
                    layoutLocationInput.setVisibility(View.GONE);
                    layoutVehicle.setVisibility(View.GONE);
                    layoutVehicleBtn.setVisibility(View.GONE);
                    layoutLocation.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_bottom));
                }
                break;
            default:
                break;
        }
    }

    /*---------------------------------------------
                Create & Back Button
     --------------------------------------------*/
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNotification:
                notification = "notification";
                btnNotification.setBackgroundTintList(getResources().getColorStateList(R.color.button));
                btnNotification.setImageResource(R.drawable.notification_white);
                btnAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnAlarm.setImageResource(R.drawable.alarm);
                break;
            case R.id.btnAlarm:
                notification = "alarm";
                btnAlarm.setBackgroundTintList(getResources().getColorStateList(R.color.button));
                btnAlarm.setImageResource(R.drawable.alarm_white);
                btnNotification.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnNotification.setImageResource(R.drawable.notification);
                break;
            case R.id.btnWalking:
                vehicle = "walking";
                btnWalking.setBackgroundTintList(getResources().getColorStateList(R.color.button));
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
                btnBycicle.setBackgroundTintList(getResources().getColorStateList(R.color.button));
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
                btnCar.setBackgroundTintList(getResources().getColorStateList(R.color.button));
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
                btnTrain.setBackgroundTintList(getResources().getColorStateList(R.color.button));
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
                btnNormal.setBackgroundTintList(getResources().getColorStateList(R.color.button));
                btnNormal.setImageResource(R.drawable.exclamation1_white);
                btnImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnVeryImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnImportant.setImageResource(R.drawable.exclamation2);
                btnVeryImportant.setImageResource(R.drawable.exclamation3);
                break;
            case R.id.btnImportant:
                importance = "important";
                btnImportant.setBackgroundTintList(getResources().getColorStateList(R.color.button));
                btnImportant.setImageResource(R.drawable.exclamation2_white);
                btnNormal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnVeryImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnNormal.setImageResource(R.drawable.exclamation1);
                btnVeryImportant.setImageResource(R.drawable.exclamation3);
                break;
            case R.id.btnVeryImportant:
                importance = "very important";
                btnVeryImportant.setBackgroundTintList(getResources().getColorStateList(R.color.button));
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
            case R.id.btnCreate:

                if (txtName.getText().toString().matches("")) {
                    Toast.makeText(this, "You must set a name!", Toast.LENGTH_SHORT).show();
                } else {
                    name = txtName.getText().toString();
                    date = txtDate.getText().toString();
                    time = txtTime.getText().toString();
                    locationStreet = txtLocationStreet.getText().toString();
                    locationPlace = txtLocationPlace.getText().toString();

                    //create the reminder and add him reminder to the list
                    Reminder reminder = new Reminder(0, name, date, time, notification, locationStreet, locationPlace, vehicle, importance, false);
                    SQLite db = new SQLite(this);
                    db.addReminder(reminder);

                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent1);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
