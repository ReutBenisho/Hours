package com.example.hours;

import junit.framework.TestCase;

public class HoursManagerTest extends TestCase {

    public void testGetInstance() {
        HoursManager hm = HoursManager.getInstance();
        assertNotNull(hm);
    }

}