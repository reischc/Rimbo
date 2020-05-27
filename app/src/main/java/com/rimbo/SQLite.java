package com.rimbo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    //database name & version
    private static final String databaseName = "Rimbo";
    private static final int databaseVersion = 1;

    //table columns
    private static final String tableName = "Note";
    private static final String ColumnIDNote = "ID_Note";
    private static final String ColumnName = "Name";
    private static final String ColumnDate = "Date";
    private static final String ColumnTime = "Time";
    private static final String ColumnLocation = "Location";
    private static final String ColumnVehicle = "Vehicle";
    private static final String ColumnImportance = "Importance";
    private static final String ColumnTimer = "Timer";

    public SQLite(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "Create table"+tableName+"("+ColumnIDNote+"Integer Primary key"+ColumnName+"String"+ColumnDate+"String"+ColumnTime+"String"+ColumnLocation+"String"+ColumnVehicle+"String"+ColumnImportance+"String"+ColumnTimer+"String"+")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    
}
