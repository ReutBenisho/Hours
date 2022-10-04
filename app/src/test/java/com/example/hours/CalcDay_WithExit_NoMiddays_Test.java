package com.example.hours;

import junit.framework.TestCase;

import org.junit.Before;

public class CalcDay_WithExit_NoMiddays_Test extends TestCase {

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

    public void test15_Arrival_8_1_Exit_21_36() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 1);
        info.mExitTime = new Timestamp(21, 36);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 1);
        expHoursInfo.mExitTime = new Timestamp(21, 36);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 53);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 29);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test16_Arrival_8_5_Exit_21_5() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 5);
        info.mExitTime = new Timestamp(21, 5);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 5);
        expHoursInfo.mExitTime = new Timestamp(21, 5);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 18);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 54);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test17_Arrival_8_24_Exit_19_44() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 24);
        info.mExitTime = new Timestamp(19, 44);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 24);
        expHoursInfo.mExitTime = new Timestamp(19, 44);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 38);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 14);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test18_Arrival_8_4_Exit_21_33() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 4);
        info.mExitTime = new Timestamp(21, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 4);
        expHoursInfo.mExitTime = new Timestamp(21, 33);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 47);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 23);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test19_Arrival_8_25_Exit_20_14() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 25);
        info.mExitTime = new Timestamp(20, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 25);
        expHoursInfo.mExitTime = new Timestamp(20, 14);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 7);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 43);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test19_Arrival_8_16_Exit_21_53() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 16);
        info.mExitTime = new Timestamp(21, 53);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 16);
        expHoursInfo.mExitTime = new Timestamp(21, 53);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 55);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 31);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test20_Arrival_8_32_Exit_22_33() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 32);
        info.mExitTime = new Timestamp(22, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 32);
        expHoursInfo.mExitTime = new Timestamp(22, 33);
        expHoursInfo.mTotalTime.total = new Timestamp(13, 19);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test21_Arrival_8_14_Exit_21_15() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 14);
        info.mExitTime = new Timestamp(21, 15);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 14);
        expHoursInfo.mExitTime = new Timestamp(21, 15);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 19);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test22_Arrival_8_34_Exit_22_02() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 34);
        info.mExitTime = new Timestamp(22, 2);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 34);
        expHoursInfo.mExitTime = new Timestamp(22, 2);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 46);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 22);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test23_Arrival_8_30_Exit_21_31() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 30);
        info.mExitTime = new Timestamp(21,31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 30);
        expHoursInfo.mExitTime = new Timestamp(21, 31);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 19);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test24_Arrival_8_10_Exit_20_20() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 10);
        info.mExitTime = new Timestamp(20, 20);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 10);
        expHoursInfo.mExitTime = new Timestamp(20, 20);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 28);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 4);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test25_Arrival_8_49_Exit_13_59() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 49);
        info.mExitTime = new Timestamp(13, 59);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 49);
        expHoursInfo.mExitTime = new Timestamp(13, 59);
        expHoursInfo.mTotalTime.total = new Timestamp(4, 41);
        expHoursInfo.mTotalTime.isFullDay = false;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test26_Arrival_7_30_Exit_16_24() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 30);
        info.mExitTime = new Timestamp(16, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 30);
        expHoursInfo.mExitTime = new Timestamp(16, 24);
        expHoursInfo.mTotalTime.total = new Timestamp(8, 24);
        expHoursInfo.mTotalTime.isFullDay = true;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test27_Arrival_8_44_Exit_22_14() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 44);
        info.mExitTime = new Timestamp(22, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 44);
        expHoursInfo.mExitTime = new Timestamp(22, 14);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 48);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 24);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test28_Arrival_8_58_Exit_18_25() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 58);
        info.mExitTime = new Timestamp(18, 25);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 58);
        expHoursInfo.mExitTime = new Timestamp(18, 25);
        expHoursInfo.mTotalTime.total = new Timestamp(8, 57);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp(0, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test29_Arrival_8_26_Exit_18_25() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 26);
        info.mExitTime = new Timestamp(22, 0);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 26);
        expHoursInfo.mExitTime = new Timestamp(22, 0);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 52);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 28);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test30_Arrival_8_47_Exit_20_35() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 47);
        info.mExitTime = new Timestamp(20, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 47);
        expHoursInfo.mExitTime = new Timestamp(20, 35);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 6);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 42);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test31_Arrival_9_1_Exit_21_39() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(9, 1);
        info.mExitTime = new Timestamp(21, 39);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(9, 1);
        expHoursInfo.mExitTime = new Timestamp(21, 39);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 56);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 32);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test32_Arrival_8_47_Exit_21_35() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 47);
        info.mExitTime = new Timestamp(21, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 47);
        expHoursInfo.mExitTime = new Timestamp(21, 35);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 6);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 42);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test33_Arrival_8_44_Exit_20_59() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 44);
        info.mExitTime = new Timestamp(20, 59);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 44);
        expHoursInfo.mExitTime = new Timestamp(20, 59);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 33);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 9);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test34_Arrival_8_12_Exit_20_25() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 12);
        info.mExitTime = new Timestamp(20, 25);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 12);
        expHoursInfo.mExitTime = new Timestamp(20, 25);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 31);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 7);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test35_Arrival_8_21_Exit_19_35() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 21);
        info.mExitTime = new Timestamp(19, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 21);
        expHoursInfo.mExitTime = new Timestamp(19, 35);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 44);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 20);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test36_Arrival_8_35_Exit_15_18() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 35);
        info.mExitTime = new Timestamp(15, 18);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 35);
        expHoursInfo.mExitTime = new Timestamp(15, 18);
        expHoursInfo.mTotalTime.total = new Timestamp(6, 13);
        expHoursInfo.mTotalTime.isFullDay = false;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test37_Arrival_8_46_Exit_19_48() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 46);
        info.mExitTime = new Timestamp(19, 48);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 46);
        expHoursInfo.mExitTime = new Timestamp(19, 48);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 20);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(1, 56);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test38_Arrival_8_31_Exit_21_13() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 31);
        info.mExitTime = new Timestamp(21, 13);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 31);
        expHoursInfo.mExitTime = new Timestamp(21, 13);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 0);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 36);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test39_Arrival_8_16_Exit_21_13() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 16);
        info.mExitTime = new Timestamp(21, 9);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 16);
        expHoursInfo.mExitTime = new Timestamp(21, 9);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 11);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 47);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test40_Arrival_9_1_Exit_20_15() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(9, 1);
        info.mExitTime = new Timestamp(20, 15);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(9, 1);
        expHoursInfo.mExitTime = new Timestamp(20, 15);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 32);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test41_Arrival_8_29_Exit_18_6() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 29);
        info.mExitTime = new Timestamp(18, 6);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 29);
        expHoursInfo.mExitTime = new Timestamp(18, 6);
        expHoursInfo.mTotalTime.total = new Timestamp(9, 7);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp(0, 43);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test42_Arrival_7_34_Exit_20_40() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 34);
        info.mExitTime = new Timestamp(20, 40);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 34);
        expHoursInfo.mExitTime = new Timestamp(20, 40);
        expHoursInfo.mTotalTime.total = new Timestamp(12, 24);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(4, 0);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test43_Arrival_8_13_Exit_19_41() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 13);
        info.mExitTime = new Timestamp(19, 41);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 13);
        expHoursInfo.mExitTime = new Timestamp(19, 41);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 46);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 22);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test44_Arrival_8_19_Exit_19_24() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 19);
        info.mExitTime = new Timestamp(19, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 19);
        expHoursInfo.mExitTime = new Timestamp(19, 24);
        expHoursInfo.mTotalTime.total = new Timestamp(10, 35);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(2, 11);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test45_Arrival_7_30_Exit_12_30() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 30);
        info.mExitTime = new Timestamp(12, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 30);
        expHoursInfo.mExitTime = new Timestamp(12, 30);
        expHoursInfo.mTotalTime.total = new Timestamp(5, 0);
        expHoursInfo.mTotalTime.isFullDay = false;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test46_Arrival_8_39_Exit_21_14() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 39);
        info.mExitTime = new Timestamp(21, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 39);
        expHoursInfo.mExitTime = new Timestamp(21, 14);
        expHoursInfo.mTotalTime.total = new Timestamp(11, 53);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(3, 29);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }
}