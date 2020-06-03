package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.content.res.ColorStateList;
import android.media.Image;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.Switch;
import android.widget.TextView;

import android.widget.TextView;
import android.widget.TimePicker;

import android.widget.Toast;

import org.w3c.dom.Text;

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
    private TextView txtDate;
    private TextView txtTime;
    private TextView txtLocation;

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
        txtLocation = (TextView) findViewById(R.id.txtLocation);

        switchDate = (Switch) findViewById(R.id.switchDate);
        switchTime = (Switch) findViewById(R.id.switchTime);
        switchLocation = (Switch) findViewById(R.id.switchLocation);
        switchVehicle = (Switch) findViewById(R.id.switchVehicle);

        layoutTime = (LinearLayout) findViewById(R.id.layoutTime);
        layoutNotification = (LinearLayout) findViewById(R.id.layoutNotification);
        layoutNotificationBtn = (LinearLayout) findViewById(R.id.layoutNotificationBtn);
        layoutVehicle = (LinearLayout) findViewById(R.id.layoutVehicle);
        layoutVehicleBtn = (LinearLayout) findViewById(R.id.layoutVehicleBtn);
        layoutDate = (LinearLayout)  findViewById(R.id.layoutDate);

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
        switchVehicle.setOnCheckedChangeListener(this);


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
                    layoutDate.setBackground(ContextCompat.getDrawable(NewReminder.this, R.drawable.border_top));
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
                            }
                        });
                    } else {
                        layoutTime.setVisibility(View.VISIBLE);
                    }
                } else {
                    txtDate.setVisibility(View.GONE);
                    txtTime.setVisibility(View.GONE);
                    layoutTime.setVisibility(View.GONE);

                    layoutNotification.setVisibility(View.GONE);
                    layoutNotificationBtn.setVisibility(View.GONE);
                    switchTime.setChecked(false);

                }
                break;
            case R.id.switchTime:
                if (buttonView.isChecked()){
                    txtTime.setVisibility(View.VISIBLE);
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
                            }
                        });
                    } else {
                        layoutNotification.setVisibility(View.VISIBLE);
                        layoutNotificationBtn.setVisibility(View.VISIBLE);
                    }
                } else {
                    txtTime.setVisibility(View.GONE);
                    layoutNotification.setVisibility(View.GONE);
                    layoutNotificationBtn.setVisibility(View.GONE);
                }
                break;
            case R.id.switchLocation:
                if (buttonView.isChecked()){
                    txtLocation.setVisibility(View.VISIBLE);
                    if (txtLocation.getText() == "") {
                        txtLocation.addTextChangedListener(new TextWatcher() {
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
                    }
                } else {
                    txtLocation.setVisibility(View.GONE);
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

    /*---------------------------------------------
                Create & Back Button
     --------------------------------------------*/
    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNotification:
                btnNotification.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnAlarm.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnAlarm:
                btnAlarm.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnNotification.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnWalking:
                btnWalking.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnBycicle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnCar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnTrain.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnBycicle:
                btnBycicle.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnWalking.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnCar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnTrain.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnCar:
                btnCar.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnWalking.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnBycicle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnTrain.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnTrain:
                btnTrain.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnWalking.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnBycicle.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnCar.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnNormal:
                btnNormal.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnVeryImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnImportant:
                btnImportant.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnNormal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnVeryImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnVeryImportant:
                btnVeryImportant.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                btnNormal.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                btnImportant.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#d8d8d8")));
                break;
            case R.id.btnBack:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnCreate:

                String vehicle = "";
                String importance = "";

                if (txtName.getText().toString().matches("")) {
                    Toast.makeText(this, "You must set a name!", Toast.LENGTH_SHORT).show();
                } else {

                    /*

                    //get the vehicle
                    int radioButtonID = radioGroupVehicle.getCheckedRadioButtonId();
                    View radioButton = radioGroupVehicle.findViewById(radioButtonID);
                    int idx = radioGroupVehicle.indexOfChild(radioButton);
                    switch (idx) {
                        case 0:
                            vehicle = "walking";
                            break;
                        case 1:
                            vehicle = "bicycle";
                            break;
                        case 2:
                            vehicle = "automobile";
                            break;
                        case 3:
                            vehicle = "train";
                            break;
                        default:
                            break;
                    }
                    //get the importance
                    int radioButtonIDImportance = radioGroupImportance.getCheckedRadioButtonId();
                    View radioButtonImportance = radioGroupImportance.findViewById(radioButtonIDImportance);
                    int idxImportance = radioGroupImportance.indexOfChild(radioButtonImportance);
                    switch (idxImportance) {
                        case 0:
                            importance = "normal";
                            break;
                        case 1:
                            importance = "important";
                            break;
                        case 2:
                            importance = "very important";
                            break;
                        default:
                            break;
                    }

                    //create the reminder and add him reminder to the list
                    Reminder reminder = new Reminder(0, txtDate.getText().toString(), txtTime.getText().toString(), txtName.getText().toString(), importance, txtLocation.getText().toString(), vehicle, txtTimer.getText().toString(),done);
                    SQLite db = new SQLite(this);
                    db.addReminder(reminder);
*/
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
