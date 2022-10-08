package com.example.hours;

import static org.junit.Assert.assertEquals;

import com.example.hours.utils.Break;
import com.example.hours.utils.HoursInfo;
import com.example.hours.utils.HoursManager;
import com.example.hours.utils.Timestamp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CalcDay_WithExit_WithMiddays_Test {

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
public void test1_Arrival_08_09_Exit_16_46_Arrival_19_15_Exit_19_51_Arrival_20_40_Exit_21_23() {

        mHoursManager.info.arrivalTime = new Timestamp(8, 9);
       mHoursManager.info.customBreaks.add(new Break(16, 46, 19, 15));
       mHoursManager.info.customBreaks.add(new Break(19, 51, 20, 40));
       mHoursManager.info.exitTime = new Timestamp(21, 23);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8,9);
        expHoursInfo.exitTime = new Timestamp(21, 23);
        expHoursInfo.totalTime.total = new Timestamp(9, 26 );
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 02);
        expHoursInfo.customBreaks.add(new Break(16, 46, 19, 15));
        expHoursInfo.customBreaks.add(new Break(19, 51, 20, 40));
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test2_Arrival_8_44_Exit_16_48_Arrival_19_15_Exit_20_13() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 44);
       mHoursManager.info.customBreaks.add(new Break(16, 48, 19, 15));
       mHoursManager.info.exitTime = new Timestamp(20, 13);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 44);
        expHoursInfo.customBreaks.add(new Break(16, 48, 19, 15));
        expHoursInfo.exitTime = new Timestamp(20, 13);
        expHoursInfo.totalTime.total = new Timestamp(8, 32);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 8);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test_3_Arrival_8_16_Exit_13_41_Arrival_15_55_Exit_20_11() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 16);
       mHoursManager.info.customBreaks.add(new Break(13, 41, 15, 55));
       mHoursManager.info.exitTime = new Timestamp(20, 11);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 16);
        expHoursInfo.customBreaks.add(new Break(13, 41, 15, 55));
        expHoursInfo.exitTime = new Timestamp(20, 11);
        expHoursInfo.totalTime.total = new Timestamp(9, 17);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 53);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test4_Arrival_7_52_Exit_16_49_Arrival_19_40_Exit_20_49() {

       mHoursManager.info.arrivalTime = new Timestamp(7, 52);
       mHoursManager.info.customBreaks.add(new Break(16, 49, 19, 40));
       mHoursManager.info.exitTime = new Timestamp(20, 49);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 52);
        expHoursInfo.customBreaks.add(new Break(16, 49, 19, 40));
        expHoursInfo.exitTime = new Timestamp(20, 49);
        expHoursInfo.totalTime.total = new Timestamp(9, 36);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 12);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test5_Arrival_8_7_Exit_16_46_Arrival_19_15_Exit_19_51() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 7);
       mHoursManager.info.customBreaks.add(new Break(16, 46, 19, 15));
       mHoursManager.info.exitTime = new Timestamp(19, 51);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 7);
        expHoursInfo.customBreaks.add(new Break(16, 46, 19, 15));
        expHoursInfo.exitTime = new Timestamp(19, 51);
        expHoursInfo.totalTime.total = new Timestamp(8, 45);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 21);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test6_Arrival_8_23_Exit_16_48_Arrival_19_15_Exit_20_09() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 23);
       mHoursManager.info.customBreaks.add(new Break(16, 48, 19, 15));
       mHoursManager.info.exitTime = new Timestamp(20, 9);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 23);
        expHoursInfo.customBreaks.add(new Break(16, 48, 19, 15));
        expHoursInfo.exitTime = new Timestamp(20, 9);
        expHoursInfo.totalTime.total = new Timestamp(8, 49);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 25);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test_7_Arrival_8_3_Exit_12_49_Arrival_19_26_Exit_21_38() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 3);
       mHoursManager.info.customBreaks.add(new Break(12, 49, 19, 26));
       mHoursManager.info.exitTime = new Timestamp(21, 38);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 3);
        expHoursInfo.customBreaks.add(new Break(12, 49, 19, 26));
        expHoursInfo.exitTime = new Timestamp(21, 38);
        expHoursInfo.totalTime.total = new Timestamp(7, 8);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 26);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test8_Arrival_8_30_Exit_16_48_Arrival_19_15_Exit_21_38() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 30);
       mHoursManager.info.customBreaks.add(new Break(16, 48,19,15));
       mHoursManager.info.exitTime = new Timestamp(20, 28);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 30);
        expHoursInfo.customBreaks.add(new Break(16, 48, 19, 15));
        expHoursInfo.exitTime = new Timestamp(20, 28);
        expHoursInfo.totalTime.total = new Timestamp(9, 1);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 37);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test9_Arrival_8_12_Exit_17_16_Arrival_18_8_Exit_21_44() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 12);
       mHoursManager.info.customBreaks.add(new Break(17, 16, 18, 8));
       mHoursManager.info.exitTime = new Timestamp(21, 44);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 12);
        expHoursInfo.customBreaks.add(new Break(17, 16, 18, 8));
        expHoursInfo.exitTime = new Timestamp(21, 44);
        expHoursInfo.totalTime.total = new Timestamp(12, 10);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 46);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test10_Arrival_8_44_Exit_13_29_Arrival_15_34_Exit_18_47() {

       mHoursManager.info.arrivalTime.setTime(8, 44);
       mHoursManager.info.customBreaks.add(new Break(13, 29, 15, 34));
       mHoursManager.info.exitTime.setTime(18, 47);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime.setTime(8, 44);
        expHoursInfo.customBreaks.add(new Break(13, 29, 15, 34));
        expHoursInfo.exitTime.setTime(18, 47);
        expHoursInfo.totalTime.total.setTime(7, 58);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(0, 26);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test_11_Arrival_8_38_Exit_12_35_Arrival_14_15_Exit_21_24() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 38);
       mHoursManager.info.customBreaks.add(new Break(12, 35, 14, 15));
       mHoursManager.info.exitTime = new Timestamp(21, 24);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 38);
        expHoursInfo.customBreaks.add(new Break(12, 35, 14, 15));
        expHoursInfo.exitTime = new Timestamp(21, 24);
        expHoursInfo.totalTime.total = new Timestamp(10, 54);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 30);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test12_Arrival_7_40_Exit_12_41_Arrival_15_45_Exit_17_31() {

       mHoursManager.info.arrivalTime = new Timestamp(7, 40);
       mHoursManager.info.customBreaks.add(new Break(12, 41, 15, 45));
       mHoursManager.info.exitTime = new Timestamp(17, 31);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 40);
        expHoursInfo.customBreaks.add(new Break(12, 41, 15, 45));
        expHoursInfo.exitTime = new Timestamp(17, 31);
        expHoursInfo.totalTime.total = new Timestamp(6, 47);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(1, 37);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test13_Arrival_8_33_Exit_13_29_Arrival_14_12_Exit_18_39() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 33);
       mHoursManager.info.customBreaks.add(new Break(13, 29, 14, 12));
       mHoursManager.info.exitTime = new Timestamp(18, 39);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 33);
        expHoursInfo.customBreaks.add(new Break(13, 29, 14, 12));
        expHoursInfo.exitTime = new Timestamp(18, 39);
        expHoursInfo.totalTime.total = new Timestamp(9, 23);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 59);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test_14_Arrival_8_18_Exit_11_15_Arrival_11_50_Exit_19_48() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 18);
       mHoursManager.info.customBreaks.add(new Break(11, 15, 11, 50));
       mHoursManager.info.exitTime = new Timestamp(19, 48);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 18);
        expHoursInfo.customBreaks.add(new Break(11, 15, 11, 50));
        expHoursInfo.exitTime = new Timestamp(19, 48);
        expHoursInfo.totalTime.total = new Timestamp(10, 13);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 49);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test15_Arrival_7_38_Exit_13_35_Arrival_13_54_Exit_16_38() {

       mHoursManager.info.arrivalTime = new Timestamp(7, 38);
       mHoursManager.info.customBreaks.add(new Break(13, 35, 13, 54));
       mHoursManager.info.exitTime = new Timestamp(16, 38);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 38);
        expHoursInfo.customBreaks.add(new Break(13, 35, 13, 54));
        expHoursInfo.exitTime = new Timestamp(16, 38);
        expHoursInfo.totalTime.total = new Timestamp(8, 30);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 6);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test16_Arrival_9_49_Exit_13_0_Arrival_18_13_Exit_21_39() {

       mHoursManager.info.arrivalTime = new Timestamp(9, 49);
       mHoursManager.info.customBreaks.add(new Break(13, 0, 18, 13));
       mHoursManager.info.exitTime = new Timestamp(21, 39);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(9, 49);
        expHoursInfo.customBreaks.add(new Break(13, 0, 18, 13));
        expHoursInfo.exitTime = new Timestamp(21, 39);
        expHoursInfo.totalTime.total = new Timestamp(6, 37);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(1, 47);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test17_Arrival_7_32_Exit_14_56_Arrival_19_0_Exit_20_59() {

       mHoursManager.info.arrivalTime = new Timestamp(7, 32);
       mHoursManager.info.customBreaks.add(new Break(14, 56, 19, 0));
       mHoursManager.info.exitTime = new Timestamp(20, 59);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 32);
        expHoursInfo.customBreaks.add(new Break(14, 56, 19, 0));
        expHoursInfo.exitTime = new Timestamp(20, 59);
        expHoursInfo.totalTime.total = new Timestamp(8, 53);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 29);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test18_Arrival_8_40_Exit_15_55_Arrival_18_35_Exit_20_10() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 40);
       mHoursManager.info.customBreaks.add(new Break(15, 55, 18, 35));
       mHoursManager.info.exitTime = new Timestamp(20, 10);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 40);
        expHoursInfo.customBreaks.add(new Break(15, 55, 18, 35));
        expHoursInfo.exitTime = new Timestamp(20, 10);
        expHoursInfo.totalTime.total = new Timestamp(8, 20);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(0, 4);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test19_Arrival_8_37_Exit_15_55_Arrival_16_34_Exit_19_12() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 37);
       mHoursManager.info.customBreaks.add(new Break(15, 55, 16, 34));
       mHoursManager.info.exitTime = new Timestamp(19, 12);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 37);
        expHoursInfo.customBreaks.add(new Break(15, 55, 16, 34));
        expHoursInfo.exitTime = new Timestamp(19, 12);
        expHoursInfo.totalTime.total = new Timestamp(9, 26);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 2);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test20_Arrival_8_54_Exit_16_32_Arrival_20_0_Exit_22_19() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 54);
       mHoursManager.info.customBreaks.add(new Break(16, 32, 20, 0));
       mHoursManager.info.exitTime = new Timestamp(22, 19);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 54);
        expHoursInfo.customBreaks.add(new Break(16, 32, 20, 0));
        expHoursInfo.exitTime = new Timestamp(22, 19);
        expHoursInfo.totalTime.total = new Timestamp(9, 27);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 3);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Test
public void test21_Arrival_8_26_Exit_16_36_Arrival_20_37_Exit_22_16() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 26);
       mHoursManager.info.customBreaks.add(new Break(16, 36, 20, 37));
       mHoursManager.info.exitTime = new Timestamp(22, 16);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 26);
        expHoursInfo.customBreaks.add(new Break(16, 36, 20, 37));
        expHoursInfo.exitTime = new Timestamp(22, 16);
        expHoursInfo.totalTime.total = new Timestamp(9, 19);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 55);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

    @Ignore("disabled until implementation is finished")
    @Test
    public void test_22_Arrival_8_9_Exit_18_6_Arrival_18_32_Exit_19_23_Arrival_19_28_Exit_21_14() {

       mHoursManager.info.arrivalTime = new Timestamp(8, 9);
       mHoursManager.info.customBreaks.add(new Break(18, 6, 18, 32));
       mHoursManager.info.customBreaks.add(new Break(19, 23, 19, 28));
       mHoursManager.info.exitTime = new Timestamp(21, 14);
        mHoursManager.CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 9);
        expHoursInfo.customBreaks.add(new Break(18, 6, 18, 32));
        expHoursInfo.customBreaks.add(new Break(19, 23, 19, 28));
        expHoursInfo.exitTime = new Timestamp(21, 14);
        expHoursInfo.totalTime.total = new Timestamp(11, 52);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 28);
        assertEquals(expHoursInfo.toString(), mHoursManager.info.toString());
    }

}