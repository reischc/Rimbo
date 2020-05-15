package com.rimbo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }



        /*---------------------------
                    Menu
        ---------------------------*/
        /*Button btnCalendarToday = (Button) findViewById(R.id.btnCalendarToday);
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
*/


}
