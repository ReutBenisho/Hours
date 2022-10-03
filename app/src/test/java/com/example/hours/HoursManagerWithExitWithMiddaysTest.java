package com.example.hours;

import junit.framework.TestCase;

import org.junit.Before;

public class HoursManagerWithExitWithMiddaysTest extends TestCase {

    @Before
    public void resetHoursManager(){
        HoursManager.getInstance().clear();
    }

    public void test1_Arrival_08_09_Exit_16_46_Arrival_19_15_Exit_19_51_Arrival_20_40_Exit_21_23() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 9);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 46, 19, 15), false));
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(19, 51, 20, 40), false));
        info.mExitTime = new Timestamp(21, 23);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8,9);
        expHoursInfo.mExitTime = new Timestamp(21, 23);
        expHoursInfo.mTotalTime.total = new Timestamp(9, 26 );
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(1, 02);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 46, 19, 15), true));
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(19, 51, 20, 40), true));
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test2_Arrival_8_44_Exit_16_48_Arrival_19_15_Exit_20_13() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 44);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 48, 19, 15), false));
        info.mExitTime = new Timestamp(20, 13);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 44);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 48, 19, 15), false));
        expHoursInfo.mExitTime = new Timestamp(20, 13);
        expHoursInfo.mTotalTime.total = new Timestamp(8, 32);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp(0, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test3_Arrival_8_16_Exit_13_41_Arrival_15_55_Exit_20_11() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 16);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(13, 41, 15, 55), false));
        info.mExitTime = new Timestamp(20, 11);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 16);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(13, 41, 15, 55), false));
        expHoursInfo.mExitTime = new Timestamp(20, 11);
        expHoursInfo.mTotalTime.total = new Timestamp(9, 17);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp(0, 53);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test4_Arrival_7_52_Exit_16_49_Arrival_19_40_Exit_20_49() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(7, 52);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 49, 19, 40), false));
        info.mExitTime = new Timestamp(20, 49);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(7, 52);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 49, 19, 40), false));
        expHoursInfo.mExitTime = new Timestamp(20, 49);
        expHoursInfo.mTotalTime.total = new Timestamp(9, 36);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.additionalHours = new Timestamp(1, 12);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test5_Arrival_8_7_Exit_16_46_Arrival_19_15_Exit_19_51() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 7);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 46, 19, 15), false));
        info.mExitTime = new Timestamp(19, 51);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 7);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 46, 19, 15), false));
        expHoursInfo.mExitTime = new Timestamp(19, 51);
        expHoursInfo.mTotalTime.total = new Timestamp(8, 45);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp(0, 21);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test6_Arrival_8_23_Exit_16_48_Arrival_19_15_Exit_20_09() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 23);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 48, 19, 15), false));
        info.mExitTime = new Timestamp(20, 9);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 23);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(16, 48, 19, 15), false));
        expHoursInfo.mExitTime = new Timestamp(20, 9);
        expHoursInfo.mTotalTime.total = new Timestamp(8, 49);
        expHoursInfo.mTotalTime.isFullDay = true;
        expHoursInfo.mTotalTime.zeroHours = new Timestamp(0, 25);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    public void test7_Arrival_8_3_Exit_12_49_Arrival_19_26_Exit_21_38() {
        HoursInfo info = new HoursInfo();
        info.mArrivalTime = new Timestamp(8, 3);
        info.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(12, 49, 19, 38), false));
        info.mExitTime = new Timestamp(21, 38);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit(info);
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mArrivalTime = new Timestamp(8, 3);
        expHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(12, 49, 19, 38), false));
        expHoursInfo.mExitTime = new Timestamp(21, 38);
        expHoursInfo.mTotalTime.total = new Timestamp(6, 58);
        expHoursInfo.mTotalTime.isFullDay = false;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }
}