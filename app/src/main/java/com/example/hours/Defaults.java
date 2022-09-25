package com.example.hours;

public class Defaults {
    private static Defaults mInstance = null;

    public Timestamp ARRIVAL_TIME;
    public Timestamp HALF_DAY;
    public Timestamp FULL_DAY;

    public Timestamp LAUNCH_BREAK_START;
    public Timestamp LAUNCH_BREAK_DURATION;
    public Timestamp LAUNCH_BREAK_END;

    public Timestamp ZERO_HOURS;
    public Timestamp ADDITIONAL_HOURS;
    public Timestamp EXTRA_ADDITIONAL_HOURS;

    public Timestamp EVENING_BREAK_START;
    public Timestamp EVENING_BREAK_DURATION;
    public Timestamp EVENING_BREAK_END;

    public Timestamp NIGHT_BREAK_START;
    public Timestamp NIGHT_BREAK_DURATION;
    public Timestamp NIGHT_BREAK_END;

    public enum Breaks{
        LAUNCH,
        EVENING,
        NIGHT
    }

    private Defaults(){
        ARRIVAL_TIME = new Timestamp(7, 30);
        HALF_DAY = new Timestamp(4, 12);
        FULL_DAY = HALF_DAY.add(HALF_DAY);

        LAUNCH_BREAK_START = new Timestamp(13, 30);
        LAUNCH_BREAK_DURATION = new Timestamp(0, 30);
        LAUNCH_BREAK_END = new Timestamp(LAUNCH_BREAK_START).add(LAUNCH_BREAK_DURATION);

        ZERO_HOURS = new Timestamp(1, 0);
        ADDITIONAL_HOURS = new Timestamp(2, 30);
        EXTRA_ADDITIONAL_HOURS = new Timestamp(2, 30);

        EVENING_BREAK_START = new Timestamp(19, 36);
        EVENING_BREAK_DURATION = new Timestamp(0, 12);
        EVENING_BREAK_END = new Timestamp(EVENING_BREAK_START).add(EVENING_BREAK_DURATION);

        NIGHT_BREAK_START = new Timestamp(22, 30);
        NIGHT_BREAK_DURATION = new Timestamp(0, 30);
        NIGHT_BREAK_END = new Timestamp(NIGHT_BREAK_START).add(NIGHT_BREAK_DURATION);
    }

    public static Defaults getInstance(){
        if(mInstance== null){
            mInstance = new Defaults();
        }
        return mInstance;
    }
}
