package com.rimbo;

import java.util.ArrayList;
import java.util.List;

public class Reminder {
    private String date;
    private String time;
    private String name;
    private String importanceLevel;
    private String location;
    private String vehicle;
    private String timer;
    static List<Reminder> allReminders = new ArrayList<>();

    /*-------------------------
           Constructor
    -------------------------*/
    public Reminder(String date, String time, String name, String importanceLevel, String location, String vehicle, String timer) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.importanceLevel = importanceLevel;
        this.location = location;
        this.vehicle = vehicle;
        this.timer = timer;
    }
    /*-------------------------
              Methods
     ------------------------*/

    //add a object to allReminders list
    public static void addReminder(Reminder reminder) {
        allReminders.add(reminder);
    }
    /*-------------------------
             Getter
     ------------------------*/
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getImportanceLevel() {
        return importanceLevel;
    }

    public String getLocation() {
        return location;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getKindOfReminder() {
        return timer;
    }
    /*-------------------------
             Setter
     ------------------------*/
    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImportanceLevel(String importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setKindOfReminder(String timer) {
        this.timer = timer;
    }
}
