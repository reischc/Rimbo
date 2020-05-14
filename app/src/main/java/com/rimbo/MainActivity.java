package com.rimbo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        


        /*---------------------------
                    Menu
        ---------------------------*/
        Button btnCalendarToday = (Button) findViewById(R.id.btnCalendarToday);
        Button btnInsert = (Button) findViewById(R.id.btnInsert);
        Button btnSettings = (Button) findViewById(R.id.btnSettings);

        btnCalendarToday.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnCalendarToday:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnInsert:
                Intent intent1 = new Intent(getApplicationContext(), NewReminder.class);
                startActivity(intent1);
                break;
            case R.id.btnSettings:
                Intent intent2 = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent2);
                break;
            default:
                break;
        }

    }
}
