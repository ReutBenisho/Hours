package com.example.hours;

import junit.framework.TestCase;

import org.junit.Rule;

public class HoursManagerWithExitTest extends TestCase {
//
//    public void compareHoursInfo(HoursInfo expHoursInfo, HoursInfo resHoursInfo){
//        assertEquals("Different arrival ", expHoursInfo.mArrivalTime.toString(), resHoursInfo.mArrivalTime.toString());
//        assertEquals("Different full day", expHoursInfo.mFullDay.toString(), resHoursInfo.mFullDay.toString());
//        assertEquals("Different zero hours", expHoursInfo.mZeroHours.toString(), resHoursInfo.mZeroHours.toString());
//        assertEquals("Different 3 and a half hours ", expHoursInfo.m3AndHalfHours.toString(), resHoursInfo.m3AndHalfHours.toString());
//        assertEquals("Different 6 hours", expHoursInfo.m6Hours.toString(), resHoursInfo.m6Hours.toString());
//        assertEquals("Different took launch break", expHoursInfo.mIsArrivalDuringLaunchBreak, resHoursInfo.mIsArrivalDuringLaunchBreak);
//        assertEquals("Different took launch break", expHoursInfo.mTookLaunchBreak, resHoursInfo.mTookLaunchBreak);
//        assertEquals("Different took evening break", expHoursInfo.mTookEveningBreak, resHoursInfo.mTookEveningBreak);
//        assertEquals("Different took night break", expHoursInfo.mTookNightBreak, resHoursInfo.mTookNightBreak);
//        assertEquals("Different exit ", expHoursInfo.mExitTime.toString(), resHoursInfo.mExitTime.toString());
//        assertEquals("Different total time ", expHoursInfo.mTotalTime.total.toString(), resHoursInfo.mTotalTime.total.toString());
//        assertEquals("Different is full day ", expHoursInfo.mTotalTime.isFullDay, resHoursInfo.mTotalTime.isFullDay);
//        assertEquals("Different total zero hours ", expHoursInfo.mTotalTime.zeroHours.toString(), resHoursInfo.mTotalTime.zeroHours.toString());
//        assertEquals("Different total additional hours ", expHoursInfo.mTotalTime.additionalHours.toString(), resHoursInfo.mTotalTime.additionalHours.toString());assertEquals("Different half day", expHoursInfo.mHalfDay.toString(), resHoursInfo.mHalfDay.toString());
//    }
    public void testGetInstance() {
        HoursManager hm = HoursManager.getInstance();
        assertNotNull(hm);
    }

    public void testArrival_07_49_Exit_22_33() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 49);
        info.mExitTime = new Timestamp(22, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 49);
        expHoursInfo.mExitTime = new Timestamp(22, 33);
        expHoursInfo.mTotalTime = new HoursInfo.Totals();
        expHoursInfo.mTotalTime.total = new Timestamp(14, 02);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp();
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(5, 38);
        expHoursInfo.mArrivalTime = new Timestamp(7, 49);
        expHoursInfo.mHalfDay = new Timestamp(12, 01);
        expHoursInfo.mFullDay = new Timestamp(16, 43);
        expHoursInfo.mZeroHours = new Timestamp(17, 43);
        expHoursInfo.m3AndHalfHours = new Timestamp(20, 25);
        expHoursInfo.m6Hours = new Timestamp(23, 25);
        expHoursInfo.mIsArrivalDuringLaunchBreak = false;
        expHoursInfo.mTookLaunchBreak = true;
        expHoursInfo.mTookEveningBreak = true;
        expHoursInfo.mTookNightBreak = true;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

}