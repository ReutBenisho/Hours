package com.example.hours;

import junit.framework.TestCase;

import org.junit.Before;

public class HoursManagerWithExitNoMiddaysTest extends TestCase {

    @Before
    public void resetHoursManager(){
        HoursManager.getInstance().clear();
    }

    public void test1_Arrival_07_49_Exit_22_33() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 49);
        info.mExitTime = new Timestamp(22, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 49);
        expHoursInfo.mExitTime = new Timestamp(22, 33);
        expHoursInfo.mTotalTime.total = new Timestamp(14, 02);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(5, 38);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test2_Arrival_07_52_Exit_20_31() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 52);
        info.mExitTime = new Timestamp(20, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 52);
        expHoursInfo.mExitTime = new Timestamp(20, 31);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 57);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test3_Arrival_8_5_Exit_21_06() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 5);
        info.mExitTime = new Timestamp(21, 6);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 5);
        expHoursInfo.mExitTime = new Timestamp(21, 6);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 19);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test4_Arrival_8_2_Exit_19_38() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 2);
        info.mExitTime = new Timestamp(19, 38);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 2);
        expHoursInfo.mExitTime = new Timestamp(19, 38);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 54);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 30);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test5_Arrival_8_22_Exit_18_42() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 22);
        info.mExitTime = new Timestamp(18, 42);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 22);
        expHoursInfo.mExitTime = new Timestamp(18, 42);
        expHoursInfo.mTotalTime.total = new Timestamp(9, 50);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(1, 26);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test6_Arrival_7_58_Exit_22_35() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 58);
        info.mExitTime = new Timestamp(22, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 58);
        expHoursInfo.mExitTime = new Timestamp(22, 35);
        expHoursInfo.mTotalTime.total = new Timestamp(13, 55);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(5, 31);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test7_Arrival_8_26_Exit_18_49() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 26);
        info.mExitTime = new Timestamp(18, 49);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 26);
        expHoursInfo.mExitTime = new Timestamp(18, 49);
        expHoursInfo.mTotalTime.total = new Timestamp(9, 53);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(1, 29);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test8_Arrival_7_54_Exit_21_22() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 54);
        info.mExitTime = new Timestamp(21, 22);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 54);
        expHoursInfo.mExitTime = new Timestamp(21, 22);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 46);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 22);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test9_Arrival_8_29_Exit_22_14() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 29);
        info.mExitTime = new Timestamp(22, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 29);
        expHoursInfo.mExitTime = new Timestamp(22, 14);
        expHoursInfo.mTotalTime.total = new Timestamp(13, 3);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 39);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test10_Arrival_8_22_Exit_19_33() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 22);
        info.mExitTime = new Timestamp(19, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 22);
        expHoursInfo.mExitTime = new Timestamp(19, 33);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 41);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 17);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test11_Arrival_8_22_Exit_20_30() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 22);
        info.mExitTime = new Timestamp(20, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 22);
        expHoursInfo.mExitTime = new Timestamp(20, 30);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 26);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 2);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test12_Arrival_8_53_Exit_21_39() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 53);
        info.mExitTime = new Timestamp(21, 39);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 53);
        expHoursInfo.mExitTime = new Timestamp(21, 39);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 4);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 40);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test13_Arrival_8_25_Exit_22_4() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 25);
        info.mExitTime = new Timestamp(22, 4);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 25);
        expHoursInfo.mExitTime = new Timestamp(22, 4);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 57);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test14_Arrival_8_27_Exit_20_48() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 27);
        info.mExitTime = new Timestamp(20, 48);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 27);
        expHoursInfo.mExitTime = new Timestamp(20, 48);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 39);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 15);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }
}