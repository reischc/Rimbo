package com.rimbo;

import java.util.ArrayList;
import java.util.List;

public class Reminder {
    private int id;
    private String name;
    private String date;
    private String time;
    private String notification;
    private String locationStreet;
    private String locationPlace;
    private String vehicle;
    private String importanceLevel;
    private boolean done;
    private static List<Reminder> allReminders = new ArrayList<>();

    /*-------------------------
           Constructor
    -------------------------*/

    public Reminder(int id, String name, String date, String time, String notification, String locationStreet, String locationPlace, String vehicle, String importanceLevel, boolean done) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.notification = notification;
        this.locationStreet = locationStreet;
        this.locationPlace = locationPlace;
        this.vehicle = vehicle;
        this.importanceLevel = importanceLevel;
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

    public String getLocationStreet() {
        return locationStreet;
    }

    public String getLocationPlace() {
        return locationPlace;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getNotification() {
        return notification;
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

    public void setLocationStreet(String locationStreet) {
        this.locationStreet = locationStreet;
    }

    public void setLocationPlace(String locationPlace) {
        this.locationPlace = locationPlace;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
