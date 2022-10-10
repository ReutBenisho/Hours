package com.example.hours.calcUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Break {

    public BreakTimes breakTimes;
    public Boolean tookBreak;
    public Break(BreakTimes pair, Boolean _tookBreak){
        breakTimes = new BreakTimes(pair);
        tookBreak = _tookBreak;
    }

    public Break(int startHour, int startMinute, int endHour, int endMinute){
        breakTimes = new BreakTimes(startHour, startMinute, endHour, endMinute);
        tookBreak = false;
    }
    public Break() {
        breakTimes = new BreakTimes();
        tookBreak = false;
    }

    public Break(Timestamp breakStart, Timestamp breakEnd, boolean _tookBreak) {
        breakTimes = new BreakTimes(breakStart, breakEnd);
        tookBreak = _tookBreak;
    }

    public Break(Break aBreak) {
        breakTimes = new BreakTimes(aBreak.breakTimes);
        tookBreak = aBreak.tookBreak;
    }

    public Break(Timestamp start, Timestamp end) {
        breakTimes = new BreakTimes(start, end);
        tookBreak = false;
    }

    public boolean expandBreak(Break other)
    {
        boolean isExpand = false;

        if(breakTimes.start.isBetween(other.breakTimes.start, other.breakTimes.end)){
            breakTimes.start.setTime(other.breakTimes.start);
            isExpand = true;
        }

        if(breakTimes.end.isBetween(other.breakTimes.start, other.breakTimes.end)){
            breakTimes.end.setTime(other.breakTimes.end);
            isExpand = true;
        }
        if(breakTimes.contain(other.breakTimes)){
            isExpand = true;
        }

        return isExpand;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        Break obj = (Break) object;
        if(!breakTimes.start.equals(obj.breakTimes.start))
            return false;
        if(!breakTimes.end.equals(obj.breakTimes.end))
            return false;
        if(tookBreak != obj.tookBreak)
            return false;
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";
        s += "start: " + breakTimes.start + " end: " + breakTimes.end + " took: " + tookBreak;
        return s;
    }

    public Timestamp duration()
    {
        return breakTimes.end.sub(breakTimes.start);
    }
}
