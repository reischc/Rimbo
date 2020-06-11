package com.rimbo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitTest {
    /*private SQLite db;

    @Before
    public void createDb() {
        db = null;
        db = new SQLite();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void addReminderTest() {
        Long size = db.getSize();

        Reminder reminder = new Reminder(1, "Test","11.12.2020","","","","","","",false);
        db.addReminder(reminder);

        Long secondSize = db.getSize();
        assertEquals(1, secondSize - size);
    }*/

    @Test
    public void loadDataOfReminderTest() {
        DetailsReminder detailsReminder = new DetailsReminder();
        Reminder reminder1 = new Reminder(1, "Test1","11.12.2020","","","","","","important",false);
        Reminder reminder2 = new Reminder(2,"Test2","","","","Traubenstrasse 17","Oberdiessbach","walking","normal", false);
        Reminder reminder3 = new Reminder(3,"Test3","13.3.2020","12:18","alarm","","","","very important",false);
        List<Reminder> allReminder = new ArrayList<>(reminder1, reminder2, reminder3);

    }

}
