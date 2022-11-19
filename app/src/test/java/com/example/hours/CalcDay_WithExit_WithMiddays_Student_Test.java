package com.example.hours;

import static org.junit.Assert.assertEquals;

import com.example.hours.calcUtils.Break;
import com.example.hours.calcUtils.HoursInfo;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalcDay_WithExit_WithMiddays_Student_Test {

    public static HoursManager mHoursManager;

    @BeforeClass
    public static void init(){
        mHoursManager = HoursManager.getInstance();
    }
    @Before
    public void resetHoursManager(){
        mHoursManager.clear();
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test_ignored_1_Arrival_07_38_Exit_13_35_Arrival_13_54_Exit_21_24() {
        mHoursManager.mInfo.userInfo.isStudent = true;
        mHoursManager.mInfo.userInfo.arrivalTime = new Timestamp(7, 38);
        mHoursManager.mInfo.breaks.customBreaks.add(new Break(13, 35, 13, 54));
        mHoursManager.mInfo.userInfo.exitTime = new Timestamp(21, 24);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 38);
        expHoursInfo.breaks.customBreaks.add(new Break(13, 35, 13, 54));
        expHoursInfo.userInfo.exitTime = new Timestamp(21, 24);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(13, 4);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 58);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(2, 42);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().mInfo.toString());
    }

    //@Ignore("disabled until implementation is finished")
    @Test
    public void test_ignored_2_Arrival_09_30_Exit_13_29_Arrival_14_12_Exit_18_06_Arrival_18_31_Exit_21_19() {
        mHoursManager.mInfo.userInfo.isStudent = true;
        mHoursManager.mInfo.userInfo.arrivalTime = new Timestamp(9, 30);
        mHoursManager.mInfo.breaks.customBreaks.add(new Break(13, 29, 14, 12));
        mHoursManager.mInfo.breaks.customBreaks.add(new Break(18, 6, 18, 31));
        mHoursManager.mInfo.userInfo.exitTime = new Timestamp(21, 19);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(9, 30);
        expHoursInfo.breaks.customBreaks.add(new Break(13, 29, 14, 12));
        expHoursInfo.breaks.customBreaks.add(new Break(18, 6, 18, 31));
        expHoursInfo.userInfo.exitTime = new Timestamp(21, 19);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(10, 29);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(2, 0);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(0, 5);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().mInfo.toString());
    }
}