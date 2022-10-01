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
        mHourInfo.clearGenearlInfo();
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

    public HoursInfo CalcDayWithExit(HoursInfo info) {
        mHourInfo = info;
        mHourInfo.clearGenearlInfo();
        mHourInfo.clearTotalTime();
        if(mHourInfo.mArrivalTime.isAfter(mHourInfo.mExitTime))
            return mHourInfo;
        mHourInfo.mTotalTime.total = removeOvelaps();

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
        mHourInfo = CalcDayNoExit(mHourInfo);
        return mHourInfo;
    }

    private Timestamp removeOvelaps() {
        Timestamp duration = mHourInfo.mExitTime.sub(mHourInfo.mArrivalTime);
        duration = removeOverlap(mHourInfo.mArrivalTime, mHourInfo.mExitTime, Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END, duration);
        duration = removeOverlap(mHourInfo.mArrivalTime, mHourInfo.mExitTime, Defaults.EVENING_BREAK_START, Defaults.EVENING_BREAK_END, duration);
       // duration = removeOverlap(mHourInfo.mArrivalTime, mHourInfo.mExitTime, Defaults.NIGHT_BREAK_START, Defaults.NIGHT_BREAK_END, duration);
        for(int i = 0; i < mHourInfo.mCustomBreaks.size(); i++){
            duration = removeOverlap(mHourInfo.mArrivalTime, mHourInfo.mExitTime, mHourInfo.mCustomBreaks.get(i).exit, mHourInfo.mCustomBreaks.get(i).arrival, duration);
        }
        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        exitTime = adjustToBreak(exitTime, Defaults.Breaks.LAUNCH);
        exitTime = adjustToBreak(exitTime, Defaults.Breaks.EVENING);
        exitTime = adjustToBreak(exitTime, Defaults.Breaks.NIGHT);
        for(int i = 0; i < mHourInfo.mCustomBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, Defaults.Breaks.CUSTOM, i);
        }
        return exitTime;
    }
    private Timestamp adjustToBreak(Timestamp exitTime, Defaults.Breaks breakType) {
        return adjustToBreak(exitTime, breakType, 0);
    }

    private Timestamp adjustToBreak(Timestamp exitTime, Defaults.Breaks breakType, int i) {
        Timestamp startBreak = null;
        Timestamp endBreak = null;
        boolean tookBreak = false;
        switch (breakType){
            case LAUNCH:
                if(mHourInfo.mTookLaunchBreak)
                    return exitTime;
                startBreak = Defaults.LAUNCH_BREAK_START;
                endBreak = Defaults.LAUNCH_BREAK_END;
                break;
            case EVENING:
                if(mHourInfo.mTookEveningBreak)
                    return exitTime;
                startBreak = Defaults.EVENING_BREAK_START;
                endBreak = Defaults.EVENING_BREAK_END;
                break;
            case NIGHT:
                if(mHourInfo.mTookNightBreak)
                    return exitTime;
                startBreak = Defaults.NIGHT_BREAK_START;
                endBreak = Defaults.NIGHT_BREAK_END;
                break;
            case CUSTOM:
                if(mHourInfo.mTookCustomBreak.get(i))
                    return exitTime;
                startBreak = mHourInfo.mCustomBreaks.get(i).exit;
                endBreak = mHourInfo.mCustomBreaks.get(i).arrival;
                break;
        }

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(mHourInfo.mArrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(mHourInfo.mArrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            switch (breakType){
                case LAUNCH:
                    mHourInfo.mTookLaunchBreak = true;
                    break;
                case EVENING:
                    mHourInfo.mTookEveningBreak = true;
                    break;
                case NIGHT:
                    mHourInfo.mTookNightBreak = true;
                    break;
            }
        }
        return exitTime;
    }

    private void adjustArrivalToLaunchBreak() {
        if(mHourInfo.mArrivalTime.isBetween(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END)){
            mHourInfo.mIsArrivalDuringLaunchBreak = true;
            mHourInfo.mArrivalTime = Defaults.LAUNCH_BREAK_END;
        }
        else{
            mHourInfo.mIsArrivalDuringLaunchBreak = false;
        }
    }

    private void AddLaunchBreakToFullDay() {
        if(!mHourInfo.mTookLaunchBreak && Timestamp.isOverlap(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END))
        {
            mHourInfo.mFullDay = Timestamp.addOverlap(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                    Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END);
            mHourInfo.mTookLaunchBreak = true;
        }
    }
}
