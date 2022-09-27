package com.example.hours;

import junit.framework.TestCase;

public class HoursManagerTest extends TestCase {

    public void testGetInstance() {
        HoursManager hm = HoursManager.getInstance();
        assertNotNull(hm);
    }

    public void testGetInfoByArrivalTime() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().GetInfoByArrivalTime(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 30);
        expHoursInfo.mHalfDay = new Timestamp(11, 42);
        expHoursInfo.mFullDay = new Timestamp(16, 24);
        expHoursInfo.mZeroHours = new Timestamp(17, 24);
        expHoursInfo.m3AndHalfHours = new Timestamp(20, 6);
        expHoursInfo.m6Hours = new Timestamp(22, 36);
        expHoursInfo.mTookLaunchBreak = true;
        expHoursInfo.mTookEveningBreak = true;
        expHoursInfo.mTookNightBreak = false;
        assertEquals(expHoursInfo, resHoursInfo);
    }
}