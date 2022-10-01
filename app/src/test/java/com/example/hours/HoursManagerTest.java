package com.example.hours;

import junit.framework.TestCase;

public class HoursManagerTest extends TestCase {

    public void compareHoursInfo(HoursInfo expHoursInfo, HoursInfo resHoursInfo){
        assertEquals("Different arrival ", expHoursInfo.mArrivalTime.toString(), resHoursInfo.mArrivalTime.toString());
        assertEquals("Different half day", expHoursInfo.mHalfDay.toString(), resHoursInfo.mHalfDay.toString());
        assertEquals("Different full day", expHoursInfo.mFullDay.toString(), resHoursInfo.mFullDay.toString());
        assertEquals("Different zero hours", expHoursInfo.mZeroHours.toString(), resHoursInfo.mZeroHours.toString());
        assertEquals("Different 3 and a half hours ", expHoursInfo.m3AndHalfHours.toString(), resHoursInfo.m3AndHalfHours.toString());
        assertEquals("Different 6 hours", expHoursInfo.m6Hours.toString(), resHoursInfo.m6Hours.toString());
        assertEquals("Different took launch break", expHoursInfo.mTookLaunchBreak, resHoursInfo.mTookLaunchBreak);
        assertEquals("Different took evening break", expHoursInfo.mTookEveningBreak, resHoursInfo.mTookEveningBreak);
        assertEquals("Different took night break", expHoursInfo.mTookNightBreak, resHoursInfo.mTookNightBreak);
    }
    public void testGetInstance() {
        HoursManager hm = HoursManager.getInstance();
        assertNotNull(hm);
    }

    public void testGetInfoByArrivalTime1() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDay(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 30);
        expHoursInfo.mHalfDay = new Timestamp(11, 42);
        expHoursInfo.mFullDay = new Timestamp(16, 24);
        expHoursInfo.mZeroHours = new Timestamp(17, 24);
        expHoursInfo.m3AndHalfHours = new Timestamp(20, 6);
        expHoursInfo.m6Hours = new Timestamp(23, 06);
        expHoursInfo.mTookLaunchBreak = true;
        expHoursInfo.mTookEveningBreak = true;
        expHoursInfo.mTookNightBreak = true;
        compareHoursInfo(expHoursInfo, resHoursInfo);
    }

    public void testGetInfoByArrivalTime2() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 49);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDay(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 49);
        expHoursInfo.mHalfDay = new Timestamp(12, 01);
        expHoursInfo.mFullDay = new Timestamp(16, 43);
        expHoursInfo.mZeroHours = new Timestamp(17, 43);
        expHoursInfo.m3AndHalfHours = new Timestamp(20, 25);
        expHoursInfo.m6Hours = new Timestamp(23, 25);
        expHoursInfo.mTookLaunchBreak = true;
        expHoursInfo.mTookEveningBreak = true;
        expHoursInfo.mTookNightBreak = true;
        compareHoursInfo(expHoursInfo, resHoursInfo);
    }
}