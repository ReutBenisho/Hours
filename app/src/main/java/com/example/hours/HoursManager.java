package com.example.hours;

public class HoursManager {
    private static HoursManager mInstance = null;
    private final Defaults mDefaults;
    private HoursInfo mHourInfo;

    private HoursManager()
    {
        mDefaults = Defaults.getInstance();
        mHourInfo = new HoursInfo();
    }

    public static HoursManager getInstance(){
        if(mInstance == null)
            mInstance = new HoursManager();
        return mInstance;
    }

    public HoursInfo CalcDay(HoursInfo info) {
        mHourInfo = info;
        mHourInfo.clearAllButUserTime();
        adjustArrivalToLaunchBreak();
        mHourInfo.mHalfDay = mHourInfo.mArrivalTime.add(mDefaults.HALF_DAY);
        mHourInfo.mHalfDay = adjustBreaks(mHourInfo.mHalfDay);
        mHourInfo.mFullDay = mHourInfo.mHalfDay.add(mDefaults.HALF_DAY);
        mHourInfo.mFullDay = adjustBreaks(mHourInfo.mFullDay);
        mHourInfo.mZeroHours = mHourInfo.mFullDay.add(mDefaults.ZERO_HOURS);
        mHourInfo.mZeroHours = adjustBreaks(mHourInfo.mZeroHours);
        mHourInfo.m3AndHalfHours = mHourInfo.mZeroHours.add(mDefaults.ADDITIONAL_HOURS);
        mHourInfo.m3AndHalfHours = adjustBreaks(mHourInfo.m3AndHalfHours);
        mHourInfo.m6Hours = mHourInfo.m3AndHalfHours.add(mDefaults.EXTRA_ADDITIONAL_HOURS);
        mHourInfo.m6Hours = adjustBreaks(mHourInfo.m6Hours);

        return mHourInfo;
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
                startBreak = mDefaults.LAUNCH_BREAK_START;
                endBreak = mDefaults.LAUNCH_BREAK_END;
                break;
            case EVENING:
                if(mHourInfo.mTookEveningBreak)
                    return exitTime;
                startBreak = mDefaults.EVENING_BREAK_START;
                endBreak = mDefaults.EVENING_BREAK_END;
                break;
            case NIGHT:
                if(mHourInfo.mTookNightBreak)
                    return exitTime;
                startBreak = mDefaults.NIGHT_BREAK_START;
                endBreak = mDefaults.NIGHT_BREAK_END;
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
        if(mHourInfo.mArrivalTime.isBetween(mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END)){
            mHourInfo.mIsArrivalDuringLaunchBreak = true;
            mHourInfo.mArrivalTime = mDefaults.LAUNCH_BREAK_END;
        }
        else{
            mHourInfo.mIsArrivalDuringLaunchBreak = false;
        }
    }

    private void AddLaunchBreakToFullDay() {
        if(!mHourInfo.mTookLaunchBreak && Timestamp.isOverlap(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END))
        {
            mHourInfo.mFullDay = Timestamp.addOverlap(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                    mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END);
            mHourInfo.mTookLaunchBreak = true;
        }
    }
}
