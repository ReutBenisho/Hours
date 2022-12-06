package com.example.hours;

import static org.junit.Assert.assertEquals;

import com.example.hours.calcUtils.HoursInfo;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.utils.Defaults;

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
//        assertEquals("Different took lunch break", expHoursInfo.mIsArrivalDuringLunchBreak, resHoursInfo.mIsArrivalDuringLunchBreak);
//        assertEquals("Different took lunch break", expHoursInfo.mTookLunchBreak, resHoursInfo.mTookLunchBreak);
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
        mHoursManager.mInfo.userInfo.arrivalTime = new Timestamp(Defaults.getArrival());
        mHoursManager.CalcDayNoExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.arrivalTime = new Timestamp(Defaults.getArrival());
        expHoursInfo.calcInfo.halfDay = Defaults.getArrival().add(Defaults.getHalfDay());
        expHoursInfo.calcInfo.fullDay = Defaults.getArrival().add(Defaults.getFullDayWithLunchBreak());
        expHoursInfo.calcInfo.zeroHours = Defaults.getArrival().add(Defaults.getFullDayWithLunchBreak().add(Defaults.getZeroHours()));
        expHoursInfo.calcInfo.additional3AndHalfHours =
                Defaults.getArrival().add(Defaults.getFullDayWithLunchBreak())
                        .add(Defaults.getZeroHours())
                        .add(Defaults.getAdditionalHours())
                        .add(Defaults.getEveningDuration());
        expHoursInfo.calcInfo.additional6Hours =
                Defaults.getArrival().add(Defaults.getFullDayWithLunchBreak())
                        .add(Defaults.getMaxAdditionalHours())
                        .add(Defaults.getEveningDuration());
        expHoursInfo.breaks.tookEveningBreak = true;
        assertEquals(expHoursInfo.toString(), mHoursManager.mInfo.toString());
    }

    @Test 
public void test2_Arrival_07_49() {
        mHoursManager.mInfo.userInfo.arrivalTime = new Timestamp(7, 49);
        mHoursManager.CalcDayNoExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 49);
        expHoursInfo.calcInfo.halfDay = new Timestamp(12, 1);
        expHoursInfo.calcInfo.fullDay = new Timestamp(16, 43);
        expHoursInfo.calcInfo.zeroHours = new Timestamp(17, 43);
        expHoursInfo.calcInfo.additional3AndHalfHours = new Timestamp(20, 25);
        expHoursInfo.calcInfo.additional6Hours = new Timestamp(22, 55);
        expHoursInfo.breaks.tookEveningBreak = true;
        assertEquals(expHoursInfo.toString(), mHoursManager.mInfo.toString());
    }
}