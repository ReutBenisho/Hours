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

    public HoursInfo GetInfoByArrivalTime(Timestamp arrivalTime) {
        mHourInfo = new HoursInfo();
        mHourInfo.mArrivalTime = new Timestamp(arrivalTime);
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
        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, Defaults.Breaks breakType) {
        Timestamp startBreak = null;
        Timestamp endBreak = null;
        boolean tookBreak = false;
        switch (breakType){
            case LAUNCH:
                if(mHourInfo.tookLaunchBreak)
                    return exitTime;
                startBreak = mDefaults.LAUNCH_BREAK_START;
                endBreak = mDefaults.LAUNCH_BREAK_END;
                break;
            case EVENING:
                if(mHourInfo.tookEveningBreak)
                    return exitTime;
                startBreak = mDefaults.EVENING_BREAK_START;
                endBreak = mDefaults.EVENING_BREAK_END;
                break;
            case NIGHT:
                if(mHourInfo.tookNightBreak)
                    return exitTime;
                startBreak = mDefaults.NIGHT_BREAK_START;
                endBreak = mDefaults.NIGHT_BREAK_DURATION;
                break;
        }

        if(Timestamp.isOverlapp(mHourInfo.mArrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlapp(mHourInfo.mArrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            switch (breakType){
                case LAUNCH:
                    mHourInfo.tookLaunchBreak = true;
                    break;
                case EVENING:
                    mHourInfo.tookEveningBreak = true;
                    break;
                case NIGHT:
                    mHourInfo.tookNightBreak = true;
                    break;
            }
        }
        return exitTime;
    }

    private void adjustArrivalToLaunchBreak() {
        if(mHourInfo.mArrivalTime.isBetween(mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END)){
            mHourInfo.isArrivalDuringLaunchBreak = true;
            mHourInfo.mArrivalTime = mDefaults.LAUNCH_BREAK_END;
        }
        else{
            mHourInfo.isArrivalDuringLaunchBreak = false;
            mHourInfo.mArrivalTime = mHourInfo.mArrivalTime;
        }
    }

    private void AddLaunchBreakToFullDay() {
        if(!mHourInfo.tookLaunchBreak && Timestamp.isOverlapp(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END))
        {
            mHourInfo.mFullDay = Timestamp.addOverlapp(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                    mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END);
            mHourInfo.tookLaunchBreak = true;
        }
    }
}
