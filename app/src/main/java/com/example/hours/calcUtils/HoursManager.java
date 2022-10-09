package com.example.hours.calcUtils;

import com.example.hours.utils.Defaults;

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
        info.clearCalculatedInfo();
        sumAllBreaks();
        if(info.userInfo.isFriday)
        {
            info.calcInfo.additional6Hours = info.userInfo.arrivalTime.add(Defaults.MAX_ADDITIONAL_HOURS);
            info.calcInfo.additional6Hours = adjustBreaks(info.calcInfo.additional6Hours);
        }
        else {
            adjustArrivalToLaunchBreak();
            info.calcInfo.halfDay = info.userInfo.arrivalTime.add(Defaults.HALF_DAY);
            info.calcInfo.halfDay = adjustBreaks(info.calcInfo.halfDay);
            info.calcInfo.fullDay = info.calcInfo.halfDay.add(Defaults.HALF_DAY);
            info.calcInfo.fullDay = adjustBreaks(info.calcInfo.fullDay);
            if(!info.userInfo.isStudent){
                info.calcInfo.zeroHours = info.calcInfo.fullDay.add(Defaults.ZERO_HOURS);
                info.calcInfo.zeroHours = adjustBreaks(info.calcInfo.zeroHours);
                info.calcInfo.additional3AndHalfHours = info.calcInfo.zeroHours.add(Defaults.ADDITIONAL_HOURS);
            }
            else
                info.calcInfo.additional3AndHalfHours = info.calcInfo.fullDay.add(Defaults.ZERO_HOURS).add(Defaults.ADDITIONAL_HOURS);

            info.calcInfo.additional3AndHalfHours = adjustBreaks(info.calcInfo.additional3AndHalfHours);
            info.calcInfo.additional6Hours = info.calcInfo.additional3AndHalfHours.add(Defaults.EXTRA_ADDITIONAL_HOURS);
            info.calcInfo.additional6Hours = adjustBreaks(info.calcInfo.additional6Hours);
        }
    }

    private void sumAllBreaks() {
        info.breaks.allBreaks.clear();
        if(info.userInfo.isFriday){
            for(int i = 0; i < info.breaks.customBreaks.size(); i++){
                Break breakToAdd = info.breaks.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
        else{
            for(int i = 0; i < info.breaks.preDefinedBreaks.size(); i++){
                Break breakToAdd = info.breaks.preDefinedBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
            for(int i = 0; i < info.breaks.customBreaks.size(); i++){
                Break breakToAdd = info.breaks.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
    }

    private void addBreakIfOverlap(Break breakToAdd) {
        boolean isExpanded = false;
        for(int i = 0; i < info.breaks.allBreaks.size(); i++){
            Break currBreak = info.breaks.allBreaks.get(i);
            isExpanded = isExpanded || currBreak.expandBreak(breakToAdd);
        }
        if(!isExpanded)
            info.breaks.allBreaks.add(new Break(breakToAdd));
    }

    public HoursInfo CalcDayWithExit() {
        info.clearCalculatedInfo();
        sumAllBreaks();
        if(info.userInfo.arrivalTime.isAfter(info.userInfo.exitTime))
            return info;
        if(info.userInfo.isFriday)
        {
            info.calcInfo.totalTime.additionalHours = removeOvelaps();
        }
        else
        {
            info.calcInfo.totalTime.total = removeOvelaps();
            if(info.userInfo.exitTime.isAfter(Defaults.EVENING_BREAK_START)
                    && info.breaks.customBreaks.size() == 0)
                info.calcInfo.totalTime.total = info.calcInfo.totalTime.total.sub(Defaults.EVENING_BREAK_DURATION);

            if(info.calcInfo.totalTime.total.equalsOrGreaterThan(Defaults.FULL_DAY)){
                info.calcInfo.totalTime.isFullDay = true;
                Timestamp additional = info.calcInfo.totalTime.total.sub(Defaults.FULL_DAY);
                if(additional.equalsOrGreaterThan(Defaults.ZERO_HOURS) || info.userInfo.isStudent){
                    info.calcInfo.totalTime.additionalHours.setTime(additional);
                }
                else{
                    info.calcInfo.totalTime.zeroHours.setTime(additional);
                }
            }
            else {
                info.calcInfo.totalTime.isFullDay = false;
                if(info.calcInfo.totalTime.total.lessThan(Defaults.HALF_DAY))
                    info.calcInfo.totalTime.unpaidAbsence.setTime(Defaults.FULL_DAY.sub(info.calcInfo.totalTime.total));
                else
                    info.calcInfo.totalTime.globalAbsence.setTime(Defaults.FULL_DAY.sub(info.calcInfo.totalTime.total));
            }
        }
        //mHourInfo = CalcDayNoExit(mHourInfo);
        return info;
    }

    private Timestamp removeOvelaps() {
        Timestamp duration = info.userInfo.exitTime.sub(info.userInfo.arrivalTime);
        for(int i = 0; i < info.breaks.allBreaks.size(); i++)
            duration = removeOverlap(info.userInfo.arrivalTime, info.userInfo.exitTime,
                    info.breaks.allBreaks.get(i).breakTimes.start, info.breaks.allBreaks.get(i).breakTimes.end,
                    duration);

        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        for(int i = 0; i < info.breaks.allBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, i);
        }
        if(!info.breaks.tookEveningBreak && exitTime.isAfter(Defaults.EVENING_BREAK_START)) {
            info.breaks.tookEveningBreak = true;
            exitTime = exitTime.add(Defaults.EVENING_BREAK_DURATION);
        }

        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, int i) {
        boolean tookBreak = false;
        if(info.breaks.allBreaks.get(i).tookBreak)
            return exitTime;
        Timestamp startBreak = info.breaks.allBreaks.get(i).breakTimes.start;
        Timestamp endBreak = info.breaks.allBreaks.get(i).breakTimes.end;

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(info.userInfo.arrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(info.userInfo.arrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            info.breaks.allBreaks.get(i).tookBreak = true;
        }
        return exitTime;
    }

    private void adjustArrivalToLaunchBreak() {
        if(info.userInfo.arrivalTime.isBetween(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END)){
            info.userInfo.arrivalTime = Defaults.LAUNCH_BREAK_END;
        }
    }

    public void clear() {
        info.clear();
    }
}
