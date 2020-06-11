package com.rimbo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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
    private static final String columnIDReminder = "ID_Note";
    private static final String columnName = "Name";
    private static final String columnDate = "Date";
    private static final String columnTime = "Time";
    private static final String columnNotification = "Notification";
    private static final String columnLocationStreet = "LocationStreet";
    private static final String columnLocationPlace = "LocationPlace";
    private static final String columnVehicle = "Vehicle";
    private static final String columnImportance = "Importance";
    private static final String columnDone = "Done";
    private int id;

    public SQLite(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "Create table "+tableName+"("+columnIDReminder+" Integer Primary key AUTOINCREMENT,"+columnName+" TEXT, "+columnDate+" TEXT, "+columnTime+" TEXT, "+columnNotification+" TEXT, "+columnLocationStreet+" TEXT, "+columnLocationPlace+" TEXT, "+columnVehicle+" TEXT, "+columnImportance+" TEXT, "+columnDone+" boolean"+")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists "+tableName);
        onCreate(db);
    }

    public void addReminder(Reminder reminder) {
        //reloadDatabase();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fill values with content
        values.put(columnName, reminder.getName());
        values.put(columnDate, reminder.getDate());
        values.put(columnTime, reminder.getTime());
        values.put(columnNotification, reminder.getNotification());
        values.put(columnLocationStreet, reminder.getLocationStreet());
        values.put(columnLocationPlace, reminder.getLocationPlace());
        values.put(columnVehicle, reminder.getVehicle());
        values.put(columnImportance, reminder.getImportanceLevel());
        values.put(columnDone, reminder.isDone());

        //insert values into db
        db.insert(tableName, null, values);

        //close Connection
        db.close();
    }

    public List<Reminder> getAllReminder() {
        List<Reminder> allReminder = new ArrayList<>();
        String selectQuery = "Select * FROM "+tableName;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), Boolean.parseBoolean(String.valueOf(cursor.getInt(9))));
                allReminder.add(reminder);
            } while(cursor.moveToNext());
        }
        return allReminder;
    }

    public void updateReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //fill values with content
        values.put(columnName, reminder.getName());
        values.put(columnDate, reminder.getDate());
        values.put(columnTime, reminder.getTime());
        values.put(columnNotification, reminder.getNotification());
        values.put(columnLocationStreet, reminder.getLocationStreet());
        values.put(columnLocationPlace, reminder.getLocationPlace());
        values.put(columnVehicle, reminder.getVehicle());
        values.put(columnImportance, reminder.getImportanceLevel());
        values.put(columnDone, reminder.isDone());

        //insert values into db
        db.update(tableName, values, "ID_Note = ?", new String[] {String.valueOf(reminder.getId())});

        //close Connection
        db.close();
    }

    public void deleteReminder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, "ID_Note = ?", new String[] {String.valueOf(id)});
    }

    public long getSize() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, tableName);

        db.close();

        return count;
    }

    public void reloadDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Drop Table if exists "+tableName);
        onCreate(db);
    }
}
