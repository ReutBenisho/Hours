package com.example.hours.calcUtils;

import androidx.annotation.NonNull;

public class BreakTimes {

    public final Timestamp start;
    public final Timestamp end;

    public BreakTimes(){
        start = new Timestamp();
        end = new Timestamp();
    }
    public BreakTimes(int startHour, int startMinute, int endHour, int endMinute){
        start = new Timestamp(startHour, startMinute);
        end = new Timestamp(endHour, endMinute);
    }

    public BreakTimes(Timestamp start_time, Timestamp end_time){
        start = start_time;
        end = end_time;
    }

    public BreakTimes(BreakTimes midday) {
        start = new Timestamp(midday.start);
        end = new Timestamp(midday.end);
    }

    public boolean contain(BreakTimes other) {
        return other.start.isBetween(start, end) && other.end.isBetween(start, end);
    }

    public BreakTimes copy() {
        return new BreakTimes(start.copy(), end.copy());
    }

    @NonNull
    @Override
    public String toString() {
        return start.toString() + " - " + end.toString();
    }
}
