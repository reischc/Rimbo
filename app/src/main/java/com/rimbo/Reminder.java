package com.rimbo;

import java.util.ArrayList;
import java.util.List;

public class Reminder {
    private int id;
    private String date;
    private String time;
    private String name;
    private String importanceLevel;
    private String location;
    private String vehicle;
    private String timer;
    private boolean done;
    private static List<Reminder> allReminders = new ArrayList<>();

    /*-------------------------
           Constructor
    -------------------------*/
    public Reminder(int id, String date, String time, String name, String importanceLevel, String location, String vehicle, String timer, boolean done) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.name = name;
        this.importanceLevel = importanceLevel;
        this.location = location;
        this.vehicle = vehicle;
        this.timer = timer;
        this.done = done;
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

    public int getId() {
        return id;
    }

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

    public String getTimer() {
        return timer;
    }

    public boolean isDone() {
        return done;
    }

    /*-------------------------
                     Setter
    -------------------------*/

    public void setId(int id) {
        this.id = id;
    }

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

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
