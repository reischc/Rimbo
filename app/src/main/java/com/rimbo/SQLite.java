package com.rimbo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    //database name & version
    private static final String databaseName = "Rimbo";
    private static final int databaseVersion = 1;

    //table columns
    private static final String tableName = "Note";
    private static final String ColumnIDReminder = "ID_Note";
    private static final String ColumnName = "Name";
    private static final String ColumnDate = "Date";
    private static final String ColumnTime = "Time";
    private static final String ColumnLocation = "Location";
    private static final String ColumnVehicle = "Vehicle";
    private static final String ColumnImportance = "Importance";
    private static final String ColumnTimer = "Timer";
    private static final String ColumnDone = "Done";
    private int id;

    public SQLite(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "Create table "+tableName+"("+ColumnIDReminder+" Integer Primary key AUTOINCREMENT,"+ColumnName+" TEXT, "+ColumnDate+" TEXT, "+ColumnTime+" TEXT, "+ColumnLocation+" TEXT, "+ColumnVehicle+" TEXT, "+ColumnImportance+" TEXT, "+ColumnTimer+" TEXT, "+ColumnDone+" boolean"+")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists "+tableName);
        onCreate(db);
    }

    public void addReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fill values with content
        values.put(ColumnName, reminder.getName());
        values.put(ColumnDate, reminder.getDate());
        values.put(ColumnTime, reminder.getTime());
        values.put(ColumnLocation, reminder.getLocation());
        values.put(ColumnVehicle, reminder.getVehicle());
        values.put(ColumnImportance, reminder.getImportanceLevel());
        values.put(ColumnTimer, reminder.getTimer());
        values.put(ColumnDone, reminder.isDone());

        //insert values into db
        db.insert(tableName, null, values);

        //close Connection
        db.close();
    }

    public List<Reminder> getReminder() {
        List<Reminder> allReminder = new ArrayList<>();
        String selectQuery = "Select * FROM "+tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), Boolean.parseBoolean(String.valueOf(cursor.getInt(8))));
                allReminder.add(reminder);
            } while(cursor.moveToNext());
        }
        return allReminder;
    }

    public void updateReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fill values with content
        values.put(ColumnName, reminder.getName());
        values.put(ColumnDate, reminder.getDate());
        values.put(ColumnTime, reminder.getTime());
        values.put(ColumnLocation, reminder.getLocation());
        values.put(ColumnVehicle, reminder.getVehicle());
        values.put(ColumnImportance, reminder.getImportanceLevel());
        values.put(ColumnTimer, reminder.getTimer());
        values.put(ColumnDone, reminder.isDone());

        //insert values into db
        db.update(tableName, values, "id = ?", new String[] {String.valueOf(reminder.getId())});

        //close Connection
        db.close();
    }
}
