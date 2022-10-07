package com.example.hours;

public class HoursManager {
    private static HoursManager mInstance = null;
    public HoursInfo info;

    private HoursManager()
    {
        info = new HoursInfo();
    }

    public static HoursManager getInstance(){
        if(mInstance == null)
            mInstance = new HoursManager();
        return mInstance;
    }

    public void CalcDayNoExit() {
        info.clearGeneralInfo();
        sumAllBreaks();
        if(info.isFriday)
        {
            info.additional6Hours = info.arrivalTime.add(Defaults.MAX_ADDITIONAL_HOURS);
            info.additional6Hours = adjustBreaks(info.additional6Hours);
        }
        else {
            adjustArrivalToLaunchBreak();
            info.halfDay = info.arrivalTime.add(Defaults.HALF_DAY);
            info.halfDay = adjustBreaks(info.halfDay);
            info.fullDay = info.halfDay.add(Defaults.HALF_DAY);
            info.fullDay = adjustBreaks(info.fullDay);
            info.zeroHours = info.fullDay.add(Defaults.ZERO_HOURS);
            info.zeroHours = adjustBreaks(info.zeroHours);
            info.additional3AndHalfHours = info.zeroHours.add(Defaults.ADDITIONAL_HOURS);
            info.additional3AndHalfHours = adjustBreaks(info.additional3AndHalfHours);
            info.additional6Hours = info.additional3AndHalfHours.add(Defaults.EXTRA_ADDITIONAL_HOURS);
            info.additional6Hours = adjustBreaks(info.additional6Hours);
        }
    }

    private void sumAllBreaks() {
        info.allBreaks.clear();
        if(info.isFriday){
            for(int i = 0; i < info.customBreaks.size(); i++){
                Break breakToAdd = info.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
        else{
            for(int i = 0; i < info.preDefinedBreaks.size(); i++){
                Break breakToAdd = info.preDefinedBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
            for(int i = 0; i < info.customBreaks.size(); i++){
                Break breakToAdd = info.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
    }

    private void addBreakIfOverlap(Break breakToAdd) {
        boolean isExpanded = false;
        for(int i = 0; i < info.allBreaks.size(); i++){
            Break currBreak = info.allBreaks.get(i);
            isExpanded = isExpanded || currBreak.expandBreak(breakToAdd);
        }
        if(!isExpanded)
            info.allBreaks.add(new Break(breakToAdd));
    }

    public HoursInfo CalcDayWithExit() {
        info.clearGeneralInfo();
        info.clearTotalTime();
        sumAllBreaks();
        if(info.arrivalTime.isAfter(info.exitTime))
            return info;
        if(info.isFriday)
        {
            info.totalTime.additionalHours = removeOvelaps();
        }
        else
        {
            info.totalTime.total = removeOvelaps();
            if(info.exitTime.isAfter(Defaults.EVENING_BREAK_START)
                    && info.customBreaks.size() == 0)
                info.totalTime.total = info.totalTime.total.sub(Defaults.EVENING_BREAK_DURATION);

            if(info.totalTime.total.equalsOrGreaterThan(Defaults.FULL_DAY)){
                info.totalTime.isFullDay = true;
                Timestamp additional = info.totalTime.total.sub(Defaults.FULL_DAY);
                if(additional.equalsOrGreaterThan(Defaults.ZERO_HOURS)){
                    info.totalTime.additionalHours.setTime(additional);
                }
                else{
                    info.totalTime.zeroHours.setTime(additional);
                }
            }
            else {
                info.totalTime.isFullDay = false;
                if(info.totalTime.total.lessThan(Defaults.HALF_DAY))
                    info.totalTime.unpaidAbsence.setTime(Defaults.FULL_DAY.sub(info.totalTime.total));
                else
                    info.totalTime.globalAbsence.setTime(Defaults.FULL_DAY.sub(info.totalTime.total));
            }
        }
        //mHourInfo = CalcDayNoExit(mHourInfo);
        return info;
    }

    private Timestamp removeOvelaps() {
        Timestamp duration = info.exitTime.sub(info.arrivalTime);
        for(int i = 0; i < info.allBreaks.size(); i++)
            duration = removeOverlap(info.arrivalTime, info.exitTime,
                    info.allBreaks.get(i).breakTimes.start, info.allBreaks.get(i).breakTimes.end,
                    duration);

        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        for(int i = 0; i < info.allBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, i);
        }
        if(!info.tookEveningBreak && exitTime.isAfter(Defaults.EVENING_BREAK_START)) {
            info.tookEveningBreak = true;
            exitTime = exitTime.add(Defaults.EVENING_BREAK_DURATION);
        }

        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, int i) {
        boolean tookBreak = false;
        if(info.allBreaks.get(i).tookBreak)
            return exitTime;
        Timestamp startBreak = info.allBreaks.get(i).breakTimes.start;
        Timestamp endBreak = info.allBreaks.get(i).breakTimes.end;

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(info.arrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(info.arrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            info.allBreaks.get(i).tookBreak = true;
        }
        return exitTime;
    }

    private void adjustArrivalToLaunchBreak() {
        if(info.arrivalTime.isBetween(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END)){
            info.arrivalTime = Defaults.LAUNCH_BREAK_END;
        }
    }

    public void clear() {
        info.clear();
    }
}
