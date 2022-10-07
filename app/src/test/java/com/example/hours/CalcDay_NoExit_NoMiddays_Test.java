package com.example.hours;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalcDay_NoExit_NoMiddays_Test {
//
//    public void compareHoursInfo(HoursInfo expHoursInfo, HoursInfo resHoursInfo){
//        assertEquals("Different arrival ", expHoursInfo.mArrivalTime.toString(), resHoursInfo.mArrivalTime.toString());
//        assertEquals("Different half day", expHoursInfo.mHalfDay.toString(), resHoursInfo.mHalfDay.toString());
//        assertEquals("Different full day", expHoursInfo.mFullDay.toString(), resHoursInfo.mFullDay.toString());
//        assertEquals("Different zero hours", expHoursInfo.mZeroHours.toString(), resHoursInfo.mZeroHours.toString());
//        assertEquals("Different 3 and a half hours ", expHoursInfo.m3AndHalfHours.toString(), resHoursInfo.m3AndHalfHours.toString());
//        assertEquals("Different 6 hours", expHoursInfo.m6Hours.toString(), resHoursInfo.m6Hours.toString());
//        assertEquals("Different took launch break", expHoursInfo.mIsArrivalDuringLaunchBreak, resHoursInfo.mIsArrivalDuringLaunchBreak);
//        assertEquals("Different took launch break", expHoursInfo.mTookLaunchBreak, resHoursInfo.mTookLaunchBreak);
//        assertEquals("Different took evening break", expHoursInfo.mTookEveningBreak, resHoursInfo.mTookEveningBreak);
//        assertEquals("Different took night break", expHoursInfo.mTookNightBreak, resHoursInfo.mTookNightBreak);
//    }

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
public void test1_Arrival_07_30() {
        mHoursManager.info.arrivalTime = new Timestamp(7, 30);
        mHoursManager.CalcDayNoExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 30);
        expHoursInfo.halfDay = new Timestamp(11, 42);
        expHoursInfo.fullDay = new Timestamp(16, 24);
        expHoursInfo.zeroHours = new Timestamp(17, 24);
        expHoursInfo.additional3AndHalfHours = new Timestamp(20, 6);
        expHoursInfo.additional6Hours = new Timestamp(22, 36);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test 
public void test2_Arrival_07_49() {
        mHoursManager.info.arrivalTime = new Timestamp(7, 49);
        mHoursManager.CalcDayNoExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 49);
        expHoursInfo.halfDay = new Timestamp(12, 01);
        expHoursInfo.fullDay = new Timestamp(16, 43);
        expHoursInfo.zeroHours = new Timestamp(17, 43);
        expHoursInfo.additional3AndHalfHours = new Timestamp(20, 25);
        expHoursInfo.additional6Hours = new Timestamp(22, 55);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }
}