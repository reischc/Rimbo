package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

public class NewReminder extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        //get all items by ID
        Button buttonBack = (Button) findViewById(R.id.btnBack);
        Button buttonCreate = (Button) findViewById(R.id.btnCreateReminder);
        CheckBox checkBoxLocation = (CheckBox) findViewById(R.id.checkBoxLocation);
        CheckBox checkBoxVehicle = (CheckBox) findViewById(R.id.checkBoxVehicle);
        CheckBox checkBoxImportance = (CheckBox) findViewById(R.id.checkBoxImportance);
        CheckBox checkBoxTimer = (CheckBox) findViewById(R.id.checkBoxTimer);

        //load all listeners
        buttonBack.setOnClickListener(this);
        buttonCreate.setOnClickListener(this);
        checkBoxLocation.setOnCheckedChangeListener(this);
        checkBoxVehicle.setOnCheckedChangeListener(this);
        checkBoxImportance.setOnCheckedChangeListener(this);
        checkBoxTimer.setOnCheckedChangeListener(this);
    }

    /*----------------------------------
                Checkboxes
     ---------------------------------*/
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.checkBoxLocation:
                EditText txtLocation = (EditText) findViewById(R.id.txtLocation);
                if (isChecked) {
                    txtLocation.setVisibility(View.VISIBLE);
                } else {
                    txtLocation.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBoxVehicle:
                RadioButton radioButtonWalking = (RadioButton) findViewById(R.id.radioButtonWalking);
                RadioButton radioButtonBicycle = (RadioButton) findViewById(R.id.radioButtonBicycle);
                RadioButton radioButtonAutomobile = (RadioButton) findViewById(R.id.radioButtonAutomobile);
                RadioButton radioButtonTrain = (RadioButton) findViewById(R.id.radioButtonTrain);
                if (isChecked) {
                    radioButtonAutomobile.setVisibility(View.VISIBLE);
                    radioButtonBicycle.setVisibility(View.VISIBLE);
                    radioButtonTrain.setVisibility(View.VISIBLE);
                    radioButtonWalking.setVisibility(View.VISIBLE);
                } else {
                    radioButtonAutomobile.setVisibility(View.GONE);
                    radioButtonBicycle.setVisibility(View.GONE);
                    radioButtonTrain.setVisibility(View.GONE);
                    radioButtonWalking.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBoxImportance:
                RadioButton radioButtonNormal = (RadioButton) findViewById(R.id.radioButtonNormal);
                RadioButton radioButtonImportant = (RadioButton) findViewById(R.id.radioButtonImportant);
                RadioButton radioButtonVeryImportant = (RadioButton) findViewById(R.id.radioButtonVeryImportant);
                if (isChecked) {
                    radioButtonNormal.setVisibility(View.VISIBLE);
                    radioButtonImportant.setVisibility(View.VISIBLE);
                    radioButtonVeryImportant.setVisibility(View.VISIBLE);
                } else {
                    radioButtonNormal.setVisibility(View.GONE);
                    radioButtonImportant.setVisibility(View.GONE);
                    radioButtonVeryImportant.setVisibility(View.GONE);
                }
                break;
            case R.id.checkBoxTimer:
                EditText txtTimer = (EditText) findViewById(R.id.txtTimer);
                if (isChecked) {
                    txtTimer.setVisibility(View.VISIBLE);
                } else {
                    txtTimer.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    /*---------------------------------------------
                Create & Back Button
     --------------------------------------------*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCreateReminder:
                if (true) {

                }
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
