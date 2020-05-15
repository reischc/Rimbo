package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

public class NewReminder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        CheckBox checkBoxLocation = (CheckBox) findViewById(R.id.checkBoxLocation);
        CheckBox checkBoxVehicle = (CheckBox) findViewById(R.id.checkBoxVehicle);
        CheckBox checkBoxImportance = (CheckBox) findViewById(R.id.checkBoxImportance);
        CheckBox checkBoxKindOfReminder = (CheckBox) findViewById(R.id.checkBoxKind);


    }
}
