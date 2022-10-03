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
        mHourInfo.mHalfDay = mHourInfo.mArrivalTime.add(Defaults.HALF_DAY);
        mHourInfo.mHalfDay = adjustBreaks(mHourInfo.mHalfDay);
        mHourInfo.mFullDay = mHourInfo.mHalfDay.add(Defaults.HALF_DAY);
        mHourInfo.mFullDay = adjustBreaks(mHourInfo.mFullDay);
        mHourInfo.mZeroHours = mHourInfo.mFullDay.add(Defaults.ZERO_HOURS);
        mHourInfo.mZeroHours = adjustBreaks(mHourInfo.mZeroHours);
        mHourInfo.m3AndHalfHours = mHourInfo.mZeroHours.add(Defaults.ADDITIONAL_HOURS);
        mHourInfo.m3AndHalfHours = adjustBreaks(mHourInfo.m3AndHalfHours);
        mHourInfo.m6Hours = mHourInfo.m3AndHalfHours.add(Defaults.EXTRA_ADDITIONAL_HOURS);
        mHourInfo.m6Hours = adjustBreaks(mHourInfo.m6Hours);

        return mHourInfo;
    }

    private void sumAllBreaks() {
        mHourInfo.mAllBreaks.clear();
        mHourInfo.mAllBreaks.add(new HoursInfo.Break(mHourInfo.mPreDefinedBreaks.get(0)));
        for(int i = 1; i < mHourInfo.mPreDefinedBreaks.size(); i++){
            HoursInfo.Break breakToAdd = mHourInfo.mPreDefinedBreaks.get(i);
            addBreakIfOverlap(breakToAdd);
        }
        for(int i = 0; i < mHourInfo.mCustomBreaks.size(); i++){
            HoursInfo.Break breakToAdd = mHourInfo.mCustomBreaks.get(i);
            addBreakIfOverlap(breakToAdd);
        }

    }

    private void addBreakIfOverlap(HoursInfo.Break breakToAdd) {
        boolean isExpanded = false;
        for(int i = 0; i < mHourInfo.mAllBreaks.size(); i++){
            HoursInfo.Break currBreak = mHourInfo.mAllBreaks.get(i);
            isExpanded = isExpanded || currBreak.expandBreak(breakToAdd);
        }
        if(!isExpanded)
            mHourInfo.mAllBreaks.add(breakToAdd);
    }

    public HoursInfo CalcDayWithExit(HoursInfo info) {
        mHourInfo = info;
        mHourInfo.clearGeneralInfo();
        mHourInfo.clearTotalTime();
        sumAllBreaks();
        if(mHourInfo.mArrivalTime.isAfter(mHourInfo.mExitTime))
            return mHourInfo;
        mHourInfo.mTotalTime.total = removeOvelaps();
        if(mHourInfo.mExitTime.isAfter(Defaults.EVENING_BREAK_START))
            mHourInfo.mTotalTime.total = mHourInfo.mTotalTime.total.sub(Defaults.EVENING_BREAK_DURATION);

        if(mHourInfo.mTotalTime.total.greaterThan(Defaults.FULL_DAY)){
            mHourInfo.mTotalTime.isFullDay = true;
            Timestamp additional = mHourInfo.mTotalTime.total.sub(Defaults.FULL_DAY);
            if(additional.greaterThan(Defaults.ZERO_HOURS)){
                mHourInfo.mTotalTime.zeroHours.clear();
                mHourInfo.mTotalTime.additionalHours.setTime(additional);
            }
            else{
                mHourInfo.mTotalTime.zeroHours.setTime(additional);
                mHourInfo.mTotalTime.additionalHours.clear();
            }
        }
        else {
            mHourInfo.mTotalTime.isFullDay = false;
        }
        //mHourInfo = CalcDayNoExit(mHourInfo);
        return mHourInfo;
    }

    private Timestamp removeOvelaps() {
        Timestamp duration = mHourInfo.mExitTime.sub(mHourInfo.mArrivalTime);
        for(int i = 0; i < mHourInfo.mAllBreaks.size(); i++)
            duration = removeOverlap(mHourInfo.mArrivalTime, mHourInfo.mExitTime,
                    mHourInfo.mAllBreaks.get(i).breakTimes.start, mHourInfo.mAllBreaks.get(i).breakTimes.end,
                    duration);

        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        for(int i = 0; i < mHourInfo.mAllBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, i);
        }
        if(!mHourInfo.mTookEveningBreak && exitTime.isAfter(Defaults.EVENING_BREAK_START)) {
            mHourInfo.mTookEveningBreak = true;
            exitTime = exitTime.add(Defaults.EVENING_BREAK_DURATION);
        }

        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, int i) {
        boolean tookBreak = false;
        if(mHourInfo.mAllBreaks.get(i).tookBreak)
            return exitTime;
        Timestamp startBreak = mHourInfo.mAllBreaks.get(i).breakTimes.start;
        Timestamp endBreak = mHourInfo.mAllBreaks.get(i).breakTimes.end;

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(mHourInfo.mArrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(mHourInfo.mArrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            mHourInfo.mAllBreaks.get(i).tookBreak = true;
        }
        return exitTime;
    }

    private void adjustArrivalToLaunchBreak() {
        if(mHourInfo.mArrivalTime.isBetween(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END)){
            mHourInfo.mArrivalTime = Defaults.LAUNCH_BREAK_END;
        }
    }

    public void clear() {
        mHourInfo.clear();
    }
}
