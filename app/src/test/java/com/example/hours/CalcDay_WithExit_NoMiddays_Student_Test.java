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

public class CalcDay_WithExit_NoMiddays_Student_Test {

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
    public void test1_Arrival_07_55_Exit_16_29() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 55);
        mHoursManager.info.userInfo.exitTime = new Timestamp(16, 29);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 55);
        expHoursInfo.userInfo.exitTime = new Timestamp(16, 29);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 4);
        expHoursInfo.calcInfo.totalTime.isFullDay = false;
        expHoursInfo.calcInfo.totalTime.unpaidAbsence = new Timestamp(0, 20);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test2_Arrival_07_25_Exit_16_30() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 25);
        mHoursManager.info.userInfo.exitTime = new Timestamp(16, 30);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 25);
        expHoursInfo.userInfo.exitTime = new Timestamp(16, 30);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 35);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(0, 11);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test3_Arrival_07_27_Exit_16_29() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 27);
        mHoursManager.info.userInfo.exitTime = new Timestamp(16, 29);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 27);
        expHoursInfo.userInfo.exitTime = new Timestamp(16, 29);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 32);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(0, 8);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test4_Arrival_07_26_Exit_19_29() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 26);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 29);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 26);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 29);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(11, 33);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 58);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 11);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test5_Arrival_07_16_Exit_19_28() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 16);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 28);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 16);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 28);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(11, 42);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 56);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 22);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test6_Arrival_07_21_Exit_19_28() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 21);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 28);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 21);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 28);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(11, 37);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 57);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 16);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test7_Arrival_07_26_Exit_19_28() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 26);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 28);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 26);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 28);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(11, 32);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 58);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 10);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test8_Arrival_07_34_Exit_19_28() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 34);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 28);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 34);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 28);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(11, 24);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 56);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 4);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test9_Arrival_07_29_Exit_19_28() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 29);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 28);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 29);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 28);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(11, 29);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 55);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 10);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test10_Arrival_11_23_Exit_20_20() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(11, 23);
        mHoursManager.info.userInfo.exitTime = new Timestamp(20, 20);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(11, 23);
        expHoursInfo.userInfo.exitTime = new Timestamp(20, 20);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 15);
        expHoursInfo.calcInfo.totalTime.isFullDay = false;
        expHoursInfo.calcInfo.totalTime.unpaidAbsence = new Timestamp(0, 9);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test11_Arrival_8_47_Exit_17_52() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(8, 47);
        mHoursManager.info.userInfo.exitTime = new Timestamp(17, 52);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(8, 47);
        expHoursInfo.userInfo.exitTime = new Timestamp(17, 52);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 35);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(0, 11);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test12_Arrival_8_14_Exit_18_18() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(8, 14);
        mHoursManager.info.userInfo.exitTime = new Timestamp(18, 18);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(8, 14);
        expHoursInfo.userInfo.exitTime = new Timestamp(18, 18);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(9, 34);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 10);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test13_Arrival_7_53_Exit_19_03() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 53);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 3);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 53);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 3);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(10, 40);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 55);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(1, 21);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test14_Arrival_7_43_Exit_19_03() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 43);
        mHoursManager.info.userInfo.exitTime = new Timestamp(19, 10);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(7, 43);
        expHoursInfo.userInfo.exitTime = new Timestamp(19, 10);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(10, 57);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(1, 59);
        expHoursInfo.calcInfo.totalTime.additional150Hours = new Timestamp(0, 34);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test15_Arrival_9_14_Exit_19_03() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(9, 14);
        mHoursManager.info.userInfo.exitTime = new Timestamp(18, 17);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(9, 14);
        expHoursInfo.userInfo.exitTime = new Timestamp(18, 17);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 33);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(0, 9);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test
    public void test16_Arrival_8_38_Exit_17_43() {
        mHoursManager.info.userInfo.isStudent = true;
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(8, 38);
        mHoursManager.info.userInfo.exitTime = new Timestamp(17, 43);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.userInfo.isStudent = true;
        expHoursInfo.userInfo.arrivalTime = new Timestamp(8, 38);
        expHoursInfo.userInfo.exitTime = new Timestamp(17, 43);
        expHoursInfo.calcInfo.totalTime.total = new Timestamp(8, 35);
        expHoursInfo.calcInfo.totalTime.isFullDay = true;
        expHoursInfo.calcInfo.totalTime.additional125Hours = new Timestamp(0, 11);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }
}