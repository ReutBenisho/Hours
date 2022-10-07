package com.example.hours;

import static org.junit.Assert.assertNotNull;

import junit.framework.TestCase;
import junit.runner.Version;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HoursManagerTest {

    public static HoursManager mHoursManager;

    @BeforeClass
    public static void init(){
        mHoursManager = HoursManager.getInstance();
    }
    @Before
    public void resetHoursManager(){
        mHoursManager.clear();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(mHoursManager);
    }

}