package com.example.hours.utils;

import com.example.hours.calcUtils.Timestamp;

public class Defaults {

    public static final Timestamp ARRIVAL_TIME = new Timestamp(7, 30);
    public static final Timestamp HALF_DAY = new Timestamp(4, 12);
    public static final Timestamp FULL_DAY = HALF_DAY.add(HALF_DAY);
    public static final Timestamp LUNCH_BREAK_START = new Timestamp(13, 30);
    public static final Timestamp LUNCH_BREAK_DURATION = new Timestamp(0, 30);
    public static final Timestamp LUNCH_BREAK_END = LUNCH_BREAK_START.add(LUNCH_BREAK_DURATION);

    public static final Timestamp ZERO_HOURS = new Timestamp(1, 0);
    public static final Timestamp ADDITIONAL_HOURS = new Timestamp(2, 30);
    public static final Timestamp EXTRA_ADDITIONAL_HOURS = new Timestamp(2, 30);
    public static final Timestamp MAX_ADDITIONAL_HOURS = ZERO_HOURS.add(ADDITIONAL_HOURS).add(EXTRA_ADDITIONAL_HOURS);
    public static final Timestamp ADDITIONAL_125_HOURS = new Timestamp(2, 0);


    public static final Timestamp EVENING_BREAK_START = new Timestamp(19, 36);
    public static final Timestamp EVENING_BREAK_DURATION = new Timestamp(0, 12);
    public static final Timestamp EVENING_BREAK_END = EVENING_BREAK_START.add(EVENING_BREAK_DURATION);

    public static final Timestamp NIGHT_BREAK_START = new Timestamp(22, 30);
    public static final Timestamp NIGHT_BREAK_DURATION = new Timestamp(0, 30);
    public static final Timestamp NIGHT_BREAK_END = NIGHT_BREAK_START.add(NIGHT_BREAK_DURATION);

    public enum Breaks_e{
        PRE_DEFINED,
        CUSTOM,
    }
}
