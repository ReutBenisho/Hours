package com.example.hours;

public class Defaults {
    private static Defaults mInstance = null;

    public final Timestamp HALF_DAY;

    public final Timestamp LAUNCH_BREAK_START;
    public final Timestamp LAUNCH_BREAK_DURATION;
    public final Timestamp LAUNCH_BREAK_END;

    public final Timestamp ZERO_HOURS;
    public final Timestamp ADDITIONAL_HOURS;
    public final Timestamp EXTRA_ADDITIONAL_HOURS;

    public final Timestamp EVENING_BREAK_START;
    public final Timestamp EVENING_BREAK_DURATION;
    public final Timestamp EVENING_BREAK_END;

    public final Timestamp NIGHT_BREAK_START;
    public final Timestamp NIGHT_BREAK_DURATION;
    public final Timestamp NIGHT_BREAK_END;

    public enum Breaks{
        LAUNCH,
        EVENING,
        NIGHT,
        CUSTOM,
    }

    private Defaults(){
        HALF_DAY = new Timestamp(4, 12);

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
