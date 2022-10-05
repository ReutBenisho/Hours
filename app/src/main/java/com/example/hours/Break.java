package com.example.hours;

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

}
