package com.example.hours;

public class HoursManager {
    private static HoursManager mInstance = null;
    private HoursInfo mHourInfo;

    private HoursManager()
    {
        mHourInfo = new HoursInfo();
    }

    public static HoursManager getInstance(){
        if(mInstance == null)
            mInstance = new HoursManager();
        return mInstance;
    }

    public HoursInfo CalcDayNoExit(HoursInfo info) {
        mHourInfo = info;
        mHourInfo.clearGeneralInfo();
        sumAllBreaks();
        adjustArrivalToLaunchBreak();
        mHourInfo.halfDay = mHourInfo.arrivalTime.add(Defaults.HALF_DAY);
        mHourInfo.halfDay = adjustBreaks(mHourInfo.halfDay);
        mHourInfo.fullDay = mHourInfo.halfDay.add(Defaults.HALF_DAY);
        mHourInfo.fullDay = adjustBreaks(mHourInfo.fullDay);
        mHourInfo.zeroHours = mHourInfo.fullDay.add(Defaults.ZERO_HOURS);
        mHourInfo.zeroHours = adjustBreaks(mHourInfo.zeroHours);
        mHourInfo.additional3AndHalfHours = mHourInfo.zeroHours.add(Defaults.ADDITIONAL_HOURS);
        mHourInfo.additional3AndHalfHours = adjustBreaks(mHourInfo.additional3AndHalfHours);
        mHourInfo.additional6Hours = mHourInfo.additional3AndHalfHours.add(Defaults.EXTRA_ADDITIONAL_HOURS);
        mHourInfo.additional6Hours = adjustBreaks(mHourInfo.additional6Hours);

        return mHourInfo;
    }

    private void sumAllBreaks() {
        mHourInfo.allBreaks.clear();
        mHourInfo.allBreaks.add(new Break(mHourInfo.preDefinedBreaks.get(0)));
        for(int i = 1; i < mHourInfo.preDefinedBreaks.size(); i++){
            Break breakToAdd = mHourInfo.preDefinedBreaks.get(i);
            addBreakIfOverlap(breakToAdd);
        }
        for(int i = 0; i < mHourInfo.customBreaks.size(); i++){
            Break breakToAdd = mHourInfo.customBreaks.get(i);
            addBreakIfOverlap(breakToAdd);
        }

    }

    private void addBreakIfOverlap(Break breakToAdd) {
        boolean isExpanded = false;
        for(int i = 0; i < mHourInfo.allBreaks.size(); i++){
            Break currBreak = mHourInfo.allBreaks.get(i);
            isExpanded = isExpanded || currBreak.expandBreak(breakToAdd);
        }
        if(!isExpanded)
            mHourInfo.allBreaks.add(breakToAdd);
    }

    public HoursInfo CalcDayWithExit(HoursInfo info) {
        mHourInfo = info;
        mHourInfo.clearGeneralInfo();
        mHourInfo.clearTotalTime();
        sumAllBreaks();
        if(mHourInfo.arrivalTime.isAfter(mHourInfo.exitTime))
            return mHourInfo;
        mHourInfo.totalTime.total = removeOvelaps();
        if(mHourInfo.exitTime.isAfter(Defaults.EVENING_BREAK_START)
        && mHourInfo.customBreaks.size() == 0)
            mHourInfo.totalTime.total = mHourInfo.totalTime.total.sub(Defaults.EVENING_BREAK_DURATION);

        if(mHourInfo.totalTime.total.equalsOrGreaterThan(Defaults.FULL_DAY)){
            mHourInfo.totalTime.isFullDay = true;
            Timestamp additional = mHourInfo.totalTime.total.sub(Defaults.FULL_DAY);
            if(additional.equalsOrGreaterThan(Defaults.ZERO_HOURS)){
                mHourInfo.totalTime.additionalHours.setTime(additional);
            }
            else{
                mHourInfo.totalTime.zeroHours.setTime(additional);
            }
        }
        else {
            mHourInfo.totalTime.isFullDay = false;
            if(mHourInfo.totalTime.total.lessThan(Defaults.HALF_DAY))
                mHourInfo.totalTime.unpaidAbsence.setTime(Defaults.FULL_DAY.sub(mHourInfo.totalTime.total));
            else
                mHourInfo.totalTime.globalAbsence.setTime(Defaults.FULL_DAY.sub(mHourInfo.totalTime.total));
        }
        //mHourInfo = CalcDayNoExit(mHourInfo);
        return mHourInfo;
    }

    private Timestamp removeOvelaps() {
        Timestamp duration = mHourInfo.exitTime.sub(mHourInfo.arrivalTime);
        for(int i = 0; i < mHourInfo.allBreaks.size(); i++)
            duration = removeOverlap(mHourInfo.arrivalTime, mHourInfo.exitTime,
                    mHourInfo.allBreaks.get(i).breakTimes.start, mHourInfo.allBreaks.get(i).breakTimes.end,
                    duration);

        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        for(int i = 0; i < mHourInfo.allBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, i);
        }
        if(!mHourInfo.tookEveningBreak && exitTime.isAfter(Defaults.EVENING_BREAK_START)) {
            mHourInfo.tookEveningBreak = true;
            exitTime = exitTime.add(Defaults.EVENING_BREAK_DURATION);
        }

        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, int i) {
        boolean tookBreak = false;
        if(mHourInfo.allBreaks.get(i).tookBreak)
            return exitTime;
        Timestamp startBreak = mHourInfo.allBreaks.get(i).breakTimes.start;
        Timestamp endBreak = mHourInfo.allBreaks.get(i).breakTimes.end;

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(mHourInfo.arrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(mHourInfo.arrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            mHourInfo.allBreaks.get(i).tookBreak = true;
        }
        return exitTime;
    }

    private void adjustArrivalToLaunchBreak() {
        if(mHourInfo.arrivalTime.isBetween(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END)){
            mHourInfo.arrivalTime = Defaults.LAUNCH_BREAK_END;
        }
    }

    public void clear() {
        mHourInfo.clear();
    }
}
