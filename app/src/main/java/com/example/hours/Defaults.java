package com.example.hours;

public class Defaults {
    private static Defaults mInstance = null;
    public CalcTime ARRIVAL_TIME;
    public CalcTime HALF_DAY;
    public CalcTime FULL_DAY;
    public CalcTime LAUNCH_BREAK_START;
    public CalcTime LAUNCH_BREAK_DURATION;
    public CalcTime LAUNCH_BREAK_END;
    public CalcTime ZERO_HOURS;
    public CalcTime ADDITIONAL_HOURS;
    public CalcTime EXTRA_ADDITIONAL_HOURS;
    public CalcTime EVENING_BREAK;
    public CalcTime EVENING_BREAK_DURATION;
    private Defaults(){
        ARRIVAL_TIME = new CalcTime(7, 30);
        HALF_DAY = new CalcTime(4, 12);
        FULL_DAY = HALF_DAY.add(HALF_DAY);
        LAUNCH_BREAK_START = new CalcTime(13, 30);
        LAUNCH_BREAK_DURATION = new CalcTime(0, 30);
        LAUNCH_BREAK_END = new CalcTime(LAUNCH_BREAK_START).add(LAUNCH_BREAK_DURATION);
        ZERO_HOURS = new CalcTime(1, 0);
        ADDITIONAL_HOURS = new CalcTime(2, 30);
        EXTRA_ADDITIONAL_HOURS = new CalcTime(2, 30);
        EVENING_BREAK = new CalcTime(19, 36);
        EVENING_BREAK_DURATION = new CalcTime(0, 12);
    }
    public static Defaults getInstance(){
        if(mInstance== null){
            mInstance = new Defaults();
        }
        return mInstance;
    }
}
