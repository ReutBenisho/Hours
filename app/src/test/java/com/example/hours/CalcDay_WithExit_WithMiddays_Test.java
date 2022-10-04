package com.example.hours;

import junit.framework.TestCase;

import org.junit.Before;

public class CalcDay_WithExit_WithMiddays_Test extends TestCase {

    @Before
    public void resetHoursManager(){
        HoursManager.getInstance().clear();
    }

    public void test1_Arrival_08_09_Exit_16_46_Arrival_19_15_Exit_19_51_Arrival_20_40_Exit_21_23() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 9);
        info.customBreaks.add(new Break(16, 46, 19, 15));
        info.customBreaks.add(new Break(19, 51, 20, 40));
        info.exitTime = new Timestamp(21, 23);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8,9);
        expHoursInfo.exitTime = new Timestamp(21, 23);
        expHoursInfo.totalTime.total = new Timestamp(9, 26 );
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 02);
        expHoursInfo.customBreaks.add(new Break(16, 46, 19, 15));
        expHoursInfo.customBreaks.add(new Break(19, 51, 20, 40));
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test2_Arrival_8_44_Exit_16_48_Arrival_19_15_Exit_20_13() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 44);
        info.customBreaks.add(new Break(16, 48, 19, 15));
        info.exitTime = new Timestamp(20, 13);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 44);
        expHoursInfo.customBreaks.add(new Break(16, 48, 19, 15));
        expHoursInfo.exitTime = new Timestamp(20, 13);
        expHoursInfo.totalTime.total = new Timestamp(8, 32);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test3_Arrival_8_16_Exit_13_41_Arrival_15_55_Exit_20_11() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 16);
        info.customBreaks.add(new Break(13, 41, 15, 55));
        info.exitTime = new Timestamp(20, 11);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 16);
        expHoursInfo.customBreaks.add(new Break(13, 41, 15, 55));
        expHoursInfo.exitTime = new Timestamp(20, 11);
        expHoursInfo.totalTime.total = new Timestamp(9, 17);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 53);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test4_Arrival_7_52_Exit_16_49_Arrival_19_40_Exit_20_49() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(7, 52);
        info.customBreaks.add(new Break(16, 49, 19, 40));
        info.exitTime = new Timestamp(20, 49);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 52);
        expHoursInfo.customBreaks.add(new Break(16, 49, 19, 40));
        expHoursInfo.exitTime = new Timestamp(20, 49);
        expHoursInfo.totalTime.total = new Timestamp(9, 36);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 12);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test5_Arrival_8_7_Exit_16_46_Arrival_19_15_Exit_19_51() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 7);
        info.customBreaks.add(new Break(16, 46, 19, 15));
        info.exitTime = new Timestamp(19, 51);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 7);
        expHoursInfo.customBreaks.add(new Break(16, 46, 19, 15));
        expHoursInfo.exitTime = new Timestamp(19, 51);
        expHoursInfo.totalTime.total = new Timestamp(8, 45);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 21);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test6_Arrival_8_23_Exit_16_48_Arrival_19_15_Exit_20_09() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 23);
        info.customBreaks.add(new Break(16, 48, 19, 15));
        info.exitTime = new Timestamp(20, 9);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 23);
        expHoursInfo.customBreaks.add(new Break(16, 48, 19, 15));
        expHoursInfo.exitTime = new Timestamp(20, 9);
        expHoursInfo.totalTime.total = new Timestamp(8, 49);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 25);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test7_Arrival_8_3_Exit_12_49_Arrival_19_26_Exit_21_38() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 3);
        info.customBreaks.add(new Break(12, 49, 19, 26));
        info.exitTime = new Timestamp(21, 38);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 3);
        expHoursInfo.customBreaks.add(new Break(12, 49, 19, 26));
        expHoursInfo.exitTime = new Timestamp(21, 38);
        expHoursInfo.totalTime.total = new Timestamp(7, 8);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 26);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test8_Arrival_8_30_Exit_16_48_Arrival_19_15_Exit_21_38() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 30);
        info.customBreaks.add(new Break(16, 48,19,15));
        info.exitTime = new Timestamp(20, 28);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 30);
        expHoursInfo.customBreaks.add(new Break(16, 48, 19, 15));
        expHoursInfo.exitTime = new Timestamp(20, 28);
        expHoursInfo.totalTime.total = new Timestamp(9, 1);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 37);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test9_Arrival_8_12_Exit_17_16_Arrival_18_8_Exit_21_44() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 12);
        info.customBreaks.add(new Break(17, 16, 18, 8));
        info.exitTime = new Timestamp(21, 44);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 12);
        expHoursInfo.customBreaks.add(new Break(17, 16, 18, 8));
        expHoursInfo.exitTime = new Timestamp(21, 44);
        expHoursInfo.totalTime.total = new Timestamp(12, 10);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 46);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test10_Arrival_8_44_Exit_13_29_Arrival_15_34_Exit_18_47() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime.setTime(8, 44);
        info.customBreaks.add(new Break(13, 29, 15, 34));
        info.exitTime.setTime(18, 47);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime.setTime(8, 44);
        expHoursInfo.customBreaks.add(new Break(13, 29, 15, 34));
        expHoursInfo.exitTime.setTime(18, 47);
        expHoursInfo.totalTime.total.setTime(7, 58);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(0, 26);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test11_Arrival_8_38_Exit_12_35_Arrival_14_15_Exit_21_24() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 38);
        info.customBreaks.add(new Break(12, 35, 14, 15));
        info.exitTime = new Timestamp(21, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 38);
        expHoursInfo.customBreaks.add(new Break(12, 35, 14, 15));
        expHoursInfo.exitTime = new Timestamp(21, 24);
        expHoursInfo.totalTime.total = new Timestamp(10, 54);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 30);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test12_Arrival_7_40_Exit_12_41_Arrival_15_45_Exit_17_31() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(7, 40);
        info.customBreaks.add(new Break(12, 41, 15, 45));
        info.exitTime = new Timestamp(17, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 40);
        expHoursInfo.customBreaks.add(new Break(12, 41, 15, 45));
        expHoursInfo.exitTime = new Timestamp(17, 31);
        expHoursInfo.totalTime.total = new Timestamp(6, 47);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(1, 37);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test13_Arrival_8_33_Exit_13_29_Arrival_14_12_Exit_18_39() {
        HoursInfo info = new HoursInfo();
        info.arrivalTime = new Timestamp(8, 33);
        info.customBreaks.add(new Break(13, 29, 14, 12));
        info.exitTime = new Timestamp(18, 39);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 33);
        expHoursInfo.customBreaks.add(new Break(13, 29, 14, 12));
        expHoursInfo.exitTime = new Timestamp(18, 39);
        expHoursInfo.totalTime.total = new Timestamp(9, 23);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 59);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }
}