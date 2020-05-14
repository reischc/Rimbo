package com.rimbo;

public class Reminder {
    private String date;
    private String time;
    private String name;
    private int importanceLevel;
    private String location;
    private String vehicle;
    private String repetition;
    private String kindOfReminder;

    /*-------------------------
           Constructor
    -------------------------*/
    public Reminder(String date, String time, String name, int importanceLevel, String location, String vehicle, String repetition, String kindOfReminder) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.importanceLevel = importanceLevel;
        this.location = location;
        this.vehicle = vehicle;
        this.repetition = repetition;
        this.kindOfReminder = kindOfReminder;
    }
    /*-------------------------
              Methods
     ------------------------*/

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

    public int getImportanceLevel() {
        return importanceLevel;
    }

    public String getLocation() {
        return location;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getRepetition() {
        return repetition;
    }

    public String getKindOfReminder() {
        return kindOfReminder;
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

    public void setImportanceLevel(int importanceLevel) {
        this.importanceLevel = importanceLevel;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public void setKindOfReminder(String kindOfReminder) {
        this.kindOfReminder = kindOfReminder;
    }
}
