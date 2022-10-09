package com.example.hours;

import static org.junit.Assert.assertEquals;

import com.example.hours.calcUtils.HoursInfo;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalcDay_WithExit_NoMiddays_Test{

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
public void test1_Arrival_07_49_Exit_22_33() {
        HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 49);
        HoursManager.getInstance().info.exitTime = new Timestamp(22, 33);
        HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 49);
        expHoursInfo.exitTime = new Timestamp(22, 33);
        expHoursInfo.totalTime.total = new Timestamp(14, 02);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(5, 38);
        assertEquals(expHoursInfo.toString(), HoursManager.getInstance().info.toString());
    }

    @Test 
public void test2_Arrival_07_52_Exit_20_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 52);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 52);
        expHoursInfo.exitTime = new Timestamp(20, 31);
        expHoursInfo.totalTime.total = new Timestamp(11, 57);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test3_Arrival_8_5_Exit_21_06() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 5);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 6);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 5);
        expHoursInfo.exitTime = new Timestamp(21, 6);
        expHoursInfo.totalTime.total = new Timestamp(12, 19);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test4_Arrival_8_2_Exit_19_38() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 2);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 38);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 2);
        expHoursInfo.exitTime = new Timestamp(19, 38);
        expHoursInfo.totalTime.total = new Timestamp(10, 54);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 30);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test5_Arrival_8_22_Exit_18_42() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 22);
       HoursManager.getInstance().info.exitTime = new Timestamp(18, 42);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 22);
        expHoursInfo.exitTime = new Timestamp(18, 42);
        expHoursInfo.totalTime.total = new Timestamp(9, 50);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 26);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test6_Arrival_7_58_Exit_22_35() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 58);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 58);
        expHoursInfo.exitTime = new Timestamp(22, 35);
        expHoursInfo.totalTime.total = new Timestamp(13, 55);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(5, 31);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test7_Arrival_8_26_Exit_18_49() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 26);
       HoursManager.getInstance().info.exitTime = new Timestamp(18, 49);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 26);
        expHoursInfo.exitTime = new Timestamp(18, 49);
        expHoursInfo.totalTime.total = new Timestamp(9, 53);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 29);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test8_Arrival_7_54_Exit_21_22() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 54);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 22);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 54);
        expHoursInfo.exitTime = new Timestamp(21, 22);
        expHoursInfo.totalTime.total = new Timestamp(12, 46);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 22);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test9_Arrival_8_29_Exit_22_14() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 29);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 29);
        expHoursInfo.exitTime = new Timestamp(22, 14);
        expHoursInfo.totalTime.total = new Timestamp(13, 3);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 39);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test10_Arrival_8_22_Exit_19_33() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 22);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 22);
        expHoursInfo.exitTime = new Timestamp(19, 33);
        expHoursInfo.totalTime.total = new Timestamp(10, 41);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 17);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test11_Arrival_8_22_Exit_20_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 22);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 22);
        expHoursInfo.exitTime = new Timestamp(20, 30);
        expHoursInfo.totalTime.total = new Timestamp(11, 26);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 2);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test12_Arrival_8_53_Exit_21_39() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 53);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 39);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 53);
        expHoursInfo.exitTime = new Timestamp(21, 39);
        expHoursInfo.totalTime.total = new Timestamp(12, 4);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 40);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test13_Arrival_8_25_Exit_22_4() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 25);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 4);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 25);
        expHoursInfo.exitTime = new Timestamp(22, 4);
        expHoursInfo.totalTime.total = new Timestamp(12, 57);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test14_Arrival_8_27_Exit_20_48() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 27);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 48);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 27);
        expHoursInfo.exitTime = new Timestamp(20, 48);
        expHoursInfo.totalTime.total = new Timestamp(11, 39);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 15);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test15_Arrival_8_1_Exit_21_36() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 1);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 36);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 1);
        expHoursInfo.exitTime = new Timestamp(21, 36);
        expHoursInfo.totalTime.total = new Timestamp(12, 53);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 29);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test16_Arrival_8_5_Exit_21_5() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 5);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 5);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 5);
        expHoursInfo.exitTime = new Timestamp(21, 5);
        expHoursInfo.totalTime.total = new Timestamp(12, 18);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 54);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test17_Arrival_8_24_Exit_19_44() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 24);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 44);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 24);
        expHoursInfo.exitTime = new Timestamp(19, 44);
        expHoursInfo.totalTime.total = new Timestamp(10, 38);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 14);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test18_Arrival_8_4_Exit_21_33() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 4);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 4);
        expHoursInfo.exitTime = new Timestamp(21, 33);
        expHoursInfo.totalTime.total = new Timestamp(12, 47);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 23);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test19_Arrival_8_25_Exit_20_14() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 25);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 25);
        expHoursInfo.exitTime = new Timestamp(20, 14);
        expHoursInfo.totalTime.total = new Timestamp(11, 7);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 43);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test19_Arrival_8_16_Exit_21_53() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 16);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 53);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 16);
        expHoursInfo.exitTime = new Timestamp(21, 53);
        expHoursInfo.totalTime.total = new Timestamp(12, 55);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 31);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test20_Arrival_8_32_Exit_22_33() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 32);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 33);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 32);
        expHoursInfo.exitTime = new Timestamp(22, 33);
        expHoursInfo.totalTime.total = new Timestamp(13, 19);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test21_Arrival_8_14_Exit_21_15() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 14);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 15);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 14);
        expHoursInfo.exitTime = new Timestamp(21, 15);
        expHoursInfo.totalTime.total = new Timestamp(12, 19);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test22_Arrival_8_34_Exit_22_02() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 34);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 2);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 34);
        expHoursInfo.exitTime = new Timestamp(22, 2);
        expHoursInfo.totalTime.total = new Timestamp(12, 46);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 22);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test23_Arrival_8_30_Exit_21_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 30);
       HoursManager.getInstance().info.exitTime = new Timestamp(21,31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 30);
        expHoursInfo.exitTime = new Timestamp(21, 31);
        expHoursInfo.totalTime.total = new Timestamp(12, 19);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 55);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test24_Arrival_8_10_Exit_20_20() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 10);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 20);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 10);
        expHoursInfo.exitTime = new Timestamp(20, 20);
        expHoursInfo.totalTime.total = new Timestamp(11, 28);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 4);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test25_Arrival_8_49_Exit_13_59() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 49);
       HoursManager.getInstance().info.exitTime = new Timestamp(13, 59);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 49);
        expHoursInfo.exitTime = new Timestamp(13, 59);
        expHoursInfo.totalTime.total = new Timestamp(4, 41);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(3, 43);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test26_Arrival_7_30_Exit_16_24() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 30);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 30);
        expHoursInfo.exitTime = new Timestamp(16, 24);
        expHoursInfo.totalTime.total = new Timestamp(8, 24);
        expHoursInfo.totalTime.isFullDay = true;
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test27_Arrival_8_44_Exit_22_14() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 44);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 44);
        expHoursInfo.exitTime = new Timestamp(22, 14);
        expHoursInfo.totalTime.total = new Timestamp(12, 48);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 24);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test28_Arrival_8_58_Exit_18_25() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 58);
       HoursManager.getInstance().info.exitTime = new Timestamp(18, 25);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 58);
        expHoursInfo.exitTime = new Timestamp(18, 25);
        expHoursInfo.totalTime.total = new Timestamp(8, 57);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test29_Arrival_8_26_Exit_18_25() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 26);
       HoursManager.getInstance().info.exitTime = new Timestamp(22, 0);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 26);
        expHoursInfo.exitTime = new Timestamp(22, 0);
        expHoursInfo.totalTime.total = new Timestamp(12, 52);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 28);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test30_Arrival_8_47_Exit_20_35() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 47);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 47);
        expHoursInfo.exitTime = new Timestamp(20, 35);
        expHoursInfo.totalTime.total = new Timestamp(11, 6);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 42);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test31_Arrival_9_1_Exit_21_39() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(9, 1);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 39);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(9, 1);
        expHoursInfo.exitTime = new Timestamp(21, 39);
        expHoursInfo.totalTime.total = new Timestamp(11, 56);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 32);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test32_Arrival_8_47_Exit_21_35() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 47);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 47);
        expHoursInfo.exitTime = new Timestamp(21, 35);
        expHoursInfo.totalTime.total = new Timestamp(12, 6);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 42);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test33_Arrival_8_44_Exit_20_59() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 44);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 59);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 44);
        expHoursInfo.exitTime = new Timestamp(20, 59);
        expHoursInfo.totalTime.total = new Timestamp(11, 33);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 9);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test34_Arrival_8_12_Exit_20_25() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 12);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 25);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 12);
        expHoursInfo.exitTime = new Timestamp(20, 25);
        expHoursInfo.totalTime.total = new Timestamp(11, 31);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 7);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test35_Arrival_8_21_Exit_19_35() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 21);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 35);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 21);
        expHoursInfo.exitTime = new Timestamp(19, 35);
        expHoursInfo.totalTime.total = new Timestamp(10, 44);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 20);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test36_Arrival_8_35_Exit_15_18() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 35);
       HoursManager.getInstance().info.exitTime = new Timestamp(15, 18);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 35);
        expHoursInfo.exitTime = new Timestamp(15, 18);
        expHoursInfo.totalTime.total = new Timestamp(6, 13);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(2, 11);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test37_Arrival_8_46_Exit_19_48() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 46);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 48);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 46);
        expHoursInfo.exitTime = new Timestamp(19, 48);
        expHoursInfo.totalTime.total = new Timestamp(10, 20);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 56);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test38_Arrival_8_31_Exit_21_13() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 31);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 13);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 31);
        expHoursInfo.exitTime = new Timestamp(21, 13);
        expHoursInfo.totalTime.total = new Timestamp(12, 0);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 36);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test39_Arrival_8_16_Exit_21_13() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 16);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 9);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 16);
        expHoursInfo.exitTime = new Timestamp(21, 9);
        expHoursInfo.totalTime.total = new Timestamp(12, 11);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 47);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test40_Arrival_9_1_Exit_20_15() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(9, 1);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 15);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(9, 1);
        expHoursInfo.exitTime = new Timestamp(20, 15);
        expHoursInfo.totalTime.total = new Timestamp(10, 32);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test41_Arrival_8_29_Exit_18_6() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 29);
       HoursManager.getInstance().info.exitTime = new Timestamp(18, 6);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 29);
        expHoursInfo.exitTime = new Timestamp(18, 6);
        expHoursInfo.totalTime.total = new Timestamp(9, 7);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 43);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test42_Arrival_7_34_Exit_20_40() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 34);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 40);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 34);
        expHoursInfo.exitTime = new Timestamp(20, 40);
        expHoursInfo.totalTime.total = new Timestamp(12, 24);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 0);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test43_Arrival_8_13_Exit_19_41() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 13);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 41);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 13);
        expHoursInfo.exitTime = new Timestamp(19, 41);
        expHoursInfo.totalTime.total = new Timestamp(10, 46);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 22);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test44_Arrival_8_19_Exit_19_24() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 19);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 19);
        expHoursInfo.exitTime = new Timestamp(19, 24);
        expHoursInfo.totalTime.total = new Timestamp(10, 35);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 11);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test45_Arrival_7_30_Exit_12_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 30);
       HoursManager.getInstance().info.exitTime = new Timestamp(12, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 30);
        expHoursInfo.exitTime = new Timestamp(12, 30);
        expHoursInfo.totalTime.total = new Timestamp(5, 0);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(3, 24);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test46_Arrival_8_39_Exit_21_14() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 39);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 39);
        expHoursInfo.exitTime = new Timestamp(21, 14);
        expHoursInfo.totalTime.total = new Timestamp(11, 53);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 29);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test47_Arrival_7_24_Exit_16_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 24);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 24);
        expHoursInfo.exitTime = new Timestamp(16, 30);
        expHoursInfo.totalTime.total = new Timestamp(8, 36);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 12);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test48_Arrival_7_46_Exit_16_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 46);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 46);
        expHoursInfo.exitTime = new Timestamp(16, 31);
        expHoursInfo.totalTime.total = new Timestamp(8, 15);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(0, 9);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test49_Arrival_7_20_Exit_17_37() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 20);
       HoursManager.getInstance().info.exitTime = new Timestamp(17, 37);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 20);
        expHoursInfo.exitTime = new Timestamp(17, 37);
        expHoursInfo.totalTime.total = new Timestamp(9, 47);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(1, 23);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test50_Arrival_7_17_Exit_16_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 17);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 17);
        expHoursInfo.exitTime = new Timestamp(16, 30);
        expHoursInfo.totalTime.total = new Timestamp(8, 43);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 19);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test51_Arrival_7_16_Exit_15_14() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 16);
       HoursManager.getInstance().info.exitTime = new Timestamp(15, 14);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 16);
        expHoursInfo.exitTime = new Timestamp(15, 14);
        expHoursInfo.totalTime.total = new Timestamp(7, 28);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(0, 56);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test52_Arrival_7_26_Exit_14_49() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 26);
       HoursManager.getInstance().info.exitTime = new Timestamp(14, 49);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 26);
        expHoursInfo.exitTime = new Timestamp(14, 49);
        expHoursInfo.totalTime.total = new Timestamp(6, 53);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 31);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test53_Arrival_7_21_Exit_17_52() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 21);
       HoursManager.getInstance().info.exitTime = new Timestamp(17, 52);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 21);
        expHoursInfo.exitTime = new Timestamp(17, 52);
        expHoursInfo.totalTime.total = new Timestamp(10, 1);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(1, 37);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test54_Arrival_7_18_Exit_14_46() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 18);
       HoursManager.getInstance().info.exitTime = new Timestamp(14, 46);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 18);
        expHoursInfo.exitTime = new Timestamp(14, 46);
        expHoursInfo.totalTime.total = new Timestamp(6, 58);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 26);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test55_Arrival_7_20_Exit_15_04() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 20);
       HoursManager.getInstance().info.exitTime = new Timestamp(15, 4);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 20);
        expHoursInfo.exitTime = new Timestamp(15, 4);
        expHoursInfo.totalTime.total = new Timestamp(7, 14);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 10);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test56_Arrival_7_24_Exit_14_16() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 24);
       HoursManager.getInstance().info.exitTime = new Timestamp(14, 16);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 24);
        expHoursInfo.exitTime = new Timestamp(14, 16);
        expHoursInfo.totalTime.total = new Timestamp(6, 22);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(2, 2);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test57_Arrival_7_21_Exit_16_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 21);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 21);
        expHoursInfo.exitTime = new Timestamp(16, 31);
        expHoursInfo.totalTime.total = new Timestamp(8, 40);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 16);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test53_Arrival_7_17_Exit_14_53() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 17);
       HoursManager.getInstance().info.exitTime = new Timestamp(14, 53);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 17);
        expHoursInfo.exitTime = new Timestamp(14, 53);
        expHoursInfo.totalTime.total = new Timestamp(7, 6);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 18);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test54_Arrival_7_26_Exit_19_28() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 26);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 28);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 26);
        expHoursInfo.exitTime = new Timestamp(19, 28);
        expHoursInfo.totalTime.total = new Timestamp(11, 32);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test55_Arrival_7_17_Exit_19_29() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 17);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 29);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 17);
        expHoursInfo.exitTime = new Timestamp(19, 29);
        expHoursInfo.totalTime.total = new Timestamp(11, 42);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 18);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test56_Arrival_7_16_Exit_19_27() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 16);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 27);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 16);
        expHoursInfo.exitTime = new Timestamp(19, 27);
        expHoursInfo.totalTime.total = new Timestamp(11, 41);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 17);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test57_Arrival_7_21_Exit_16_53() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 21);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 53);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 21);
        expHoursInfo.exitTime = new Timestamp(16, 53);
        expHoursInfo.totalTime.total = new Timestamp(9, 2);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 38);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test58_Arrival_7_25_Exit_16_27() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 25);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 27);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 25);
        expHoursInfo.exitTime = new Timestamp(16, 27);
        expHoursInfo.totalTime.total = new Timestamp(8, 32);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test59_Arrival_7_13_Exit_19_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 13);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 13);
        expHoursInfo.exitTime = new Timestamp(19, 30);
        expHoursInfo.totalTime.total = new Timestamp(11, 47);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 23);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test60_Arrival_7_21_Exit_19_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 21);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 21);
        expHoursInfo.exitTime = new Timestamp(19, 30);
        expHoursInfo.totalTime.total = new Timestamp(11, 39);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 15);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test61_Arrival_7_13_Exit_16_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 13);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 13);
        expHoursInfo.exitTime = new Timestamp(16, 31);
        expHoursInfo.totalTime.total = new Timestamp(8, 48);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 24);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test62_Arrival_7_42_Exit_19_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 42);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 42);
        expHoursInfo.exitTime = new Timestamp(19, 30);
        expHoursInfo.totalTime.total = new Timestamp(11, 18);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(2, 54);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test63_Arrival_7_31_Exit_19_46() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 31);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 46);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 31);
        expHoursInfo.exitTime = new Timestamp(19, 46);
        expHoursInfo.totalTime.total = new Timestamp(11, 33);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 9);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test64_Arrival_8_36_Exit_18_40() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 36);
       HoursManager.getInstance().info.exitTime = new Timestamp(18, 40);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 36);
        expHoursInfo.exitTime = new Timestamp(18, 40);
        expHoursInfo.totalTime.total = new Timestamp(9, 34);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(1, 10);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test65_Arrival_8_58_Exit_14_06() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 58);
       HoursManager.getInstance().info.exitTime = new Timestamp(14, 6);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 58);
        expHoursInfo.exitTime = new Timestamp(14, 6);
        expHoursInfo.totalTime.total = new Timestamp(4, 38);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(3, 46);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test66_Arrival_8_43_Exit_20_36() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 43);
       HoursManager.getInstance().info.exitTime = new Timestamp(20, 36);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 43);
        expHoursInfo.exitTime = new Timestamp(20, 36);
        expHoursInfo.totalTime.total = new Timestamp(11, 11);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(2, 47);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test67_Arrival_9_17_Exit_19_10() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(9, 17);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 10);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(9, 17);
        expHoursInfo.exitTime = new Timestamp(19, 10);
        expHoursInfo.totalTime.total = new Timestamp(9, 23);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 59);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test68_Arrival_9_4_Exit_18_29() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(9, 4);
       HoursManager.getInstance().info.exitTime = new Timestamp(18,29);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(9, 4);
        expHoursInfo.exitTime = new Timestamp(18,29);
        expHoursInfo.totalTime.total = new Timestamp(8, 55);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 31);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test69_Arrival_7_23_Exit_16_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 23);
       HoursManager.getInstance().info.exitTime = new Timestamp(16,31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 23);
        expHoursInfo.exitTime = new Timestamp(16, 31);
        expHoursInfo.totalTime.total = new Timestamp(8, 38);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 14);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test70_Arrival_7_24_Exit_14_27() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 24);
       HoursManager.getInstance().info.exitTime = new Timestamp(14,27);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 24);
        expHoursInfo.exitTime = new Timestamp(14, 27);
        expHoursInfo.totalTime.total = new Timestamp(6, 33);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(1, 51);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test71_Arrival_7_23_Exit_16_50() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 23);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 50);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 23);
        expHoursInfo.exitTime = new Timestamp(16, 50);
        expHoursInfo.totalTime.total = new Timestamp(8, 57);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 33);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test72_Arrival_7_30_Exit_16_1() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 30);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 1);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 30);
        expHoursInfo.exitTime = new Timestamp(16, 1);
        expHoursInfo.totalTime.total = new Timestamp(8, 1);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(0, 23);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test73_Arrival_7_21_Exit_16_21() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 21);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 21);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 21);
        expHoursInfo.exitTime = new Timestamp(16, 21);
        expHoursInfo.totalTime.total = new Timestamp(8, 30);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 6);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test74_Arrival_7_27_Exit_16_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 27);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 27);
        expHoursInfo.exitTime = new Timestamp(16, 30);
        expHoursInfo.totalTime.total = new Timestamp(8, 33);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 9);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test75_Arrival_7_28_Exit_16_30() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 28);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 30);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 28);
        expHoursInfo.exitTime = new Timestamp(16, 30);
        expHoursInfo.totalTime.total = new Timestamp(8, 32);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 8);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test76_Arrival_7_48_Exit_16_38() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 48);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 38);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 48);
        expHoursInfo.exitTime = new Timestamp(16, 38);
        expHoursInfo.totalTime.total = new Timestamp(8, 20);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(0, 4);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test77_Arrival_7_48_Exit_16_29() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 48);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 29);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 48);
        expHoursInfo.exitTime = new Timestamp(16, 29);
        expHoursInfo.totalTime.total = new Timestamp(8, 11);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(0, 13);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test78_Arrival_8_36_Exit_17_34() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 36);
       HoursManager.getInstance().info.exitTime = new Timestamp(17, 34);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 36);
        expHoursInfo.exitTime = new Timestamp(17, 34);
        expHoursInfo.totalTime.total = new Timestamp(8, 28);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 4);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test77_Arrival_7_30_Exit_19_34() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 30);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 34);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 30);
        expHoursInfo.exitTime = new Timestamp(19, 34);
        expHoursInfo.totalTime.total = new Timestamp(11, 34);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 10);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test78_Arrival_7_28_Exit_16_45() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 28);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 45);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 28);
        expHoursInfo.exitTime = new Timestamp(16, 45);
        expHoursInfo.totalTime.total = new Timestamp(8, 47);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 23);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test79_Arrival_7_33_Exit_17_53() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 33);
       HoursManager.getInstance().info.exitTime = new Timestamp(17, 53);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 33);
        expHoursInfo.exitTime = new Timestamp(17, 53);
        expHoursInfo.totalTime.total = new Timestamp(9, 50);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 26);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test80_Arrival_7_57_Exit_17_15() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 57);
       HoursManager.getInstance().info.exitTime = new Timestamp(17, 15);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 57);
        expHoursInfo.exitTime = new Timestamp(17, 15);
        expHoursInfo.totalTime.total = new Timestamp(8, 48);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 24);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test81_Arrival_7_44_Exit_21_24() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 44);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 44);
        expHoursInfo.exitTime = new Timestamp(21, 24);
        expHoursInfo.totalTime.total = new Timestamp(12, 58);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(4, 34);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test82_Arrival_7_33_Exit_19_55() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 33);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 55);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 33);
        expHoursInfo.exitTime = new Timestamp(19, 55);
        expHoursInfo.totalTime.total = new Timestamp(11, 40);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 16);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test83_Arrival_7_35_Exit_16_57() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 33);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 55);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 33);
        expHoursInfo.exitTime = new Timestamp(19, 55);
        expHoursInfo.totalTime.total = new Timestamp(11, 40);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(3, 16);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test84_Arrival_9_12_Exit_16_31() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(9, 12);
       HoursManager.getInstance().info.exitTime = new Timestamp(16,31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(9, 12);
        expHoursInfo.exitTime = new Timestamp(16, 31);
        expHoursInfo.totalTime.total = new Timestamp(6, 49);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence.setTime(01, 35);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test85_Arrival_7_34_Exit_17_6() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 34);
       HoursManager.getInstance().info.exitTime = new Timestamp(17,6);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 34);
        expHoursInfo.exitTime = new Timestamp(17, 6);
        expHoursInfo.totalTime.total = new Timestamp(9, 2);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours.setTime(0, 38);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test86_Arrival_7_49_Exit_16_38() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 49);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 38);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 49);
        expHoursInfo.exitTime = new Timestamp(16, 38);
        expHoursInfo.totalTime.total = new Timestamp(8, 19);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(0, 5);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test87_Arrival_8_43_Exit_21_9() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 43);
       HoursManager.getInstance().info.exitTime = new Timestamp(21, 9);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 43);
        expHoursInfo.exitTime = new Timestamp(21, 9);
        expHoursInfo.totalTime.total = new Timestamp(11, 44);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours.setTime(3, 20);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test88_Arrival_8_24_Exit_20_15() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 24);
       HoursManager.getInstance().info.exitTime = new Timestamp(20,15);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 24);
        expHoursInfo.exitTime = new Timestamp(20,15);
        expHoursInfo.totalTime.total = new Timestamp(11, 9);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 45);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test89_Arrival_7_41_Exit_19_24() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 41);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 24);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 41);
        expHoursInfo.exitTime = new Timestamp(19, 24);
        expHoursInfo.totalTime.total = new Timestamp(11, 13);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 49);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test90_Arrival_8_41_Exit_19_24() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 41);
       HoursManager.getInstance().info.exitTime = new Timestamp(18, 31);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 41);
        expHoursInfo.exitTime = new Timestamp(18, 31);
        expHoursInfo.totalTime.total = new Timestamp(9, 20);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.zeroHours = new Timestamp(0, 56);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test91_Arrival_8_38_Exit_19_17() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 38);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 17);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 38);
        expHoursInfo.exitTime = new Timestamp(19, 17);
        expHoursInfo.totalTime.total = new Timestamp(10, 9);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 45);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test92_Arrival_7_54_Exit_19_41() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(7, 54);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 41);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(7, 54);
        expHoursInfo.exitTime = new Timestamp(19, 41);
        expHoursInfo.totalTime.total = new Timestamp(11, 5);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(2, 41);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test93_Arrival_8_57_Exit_19_41() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 57);
       HoursManager.getInstance().info.exitTime = new Timestamp(19, 12);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 57);
        expHoursInfo.exitTime = new Timestamp(19, 12);
        expHoursInfo.totalTime.total = new Timestamp(9, 45);
        expHoursInfo.totalTime.isFullDay = true;
        expHoursInfo.totalTime.additionalHours = new Timestamp(1, 21);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }

    @Test 
public void test94_Arrival_8_0_Exit_19_41() {
        
       HoursManager.getInstance().info.arrivalTime = new Timestamp(8, 0);
       HoursManager.getInstance().info.exitTime = new Timestamp(16, 40);
        HoursInfo resHoursInfo = HoursManager.getInstance().CalcDayWithExit();
        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.arrivalTime = new Timestamp(8, 0);
        expHoursInfo.exitTime = new Timestamp(16, 40);
        expHoursInfo.totalTime.total = new Timestamp(8, 10);
        expHoursInfo.totalTime.isFullDay = false;
        expHoursInfo.totalTime.globalAbsence = new Timestamp(0, 14);
        assertEquals(expHoursInfo.toString(), resHoursInfo.toString());
    }



}