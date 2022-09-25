package com.example.hours;

public class HoursManager {
    private static HoursManager mInstance = null;
    private Defaults mDefaults;
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
        mHourInfo.mHalfDay = mHourInfo.mArrivalTime.add(mDefaults.HALF_DAY);
        mHourInfo.mFullDay = mHourInfo.mHalfDay.add(mDefaults.HALF_DAY);
        mHourInfo.mZeroHours = mHourInfo.mFullDay.add(mDefaults.ZERO_HOURS);
        mHourInfo.m3AndHalfHours = mHourInfo.mZeroHours.add(mDefaults.ADDITIONAL_HOURS);
        mHourInfo.m6Hours = mHourInfo.m3AndHalfHours.add(mDefaults.EXTRA_ADDITIONAL_HOURS);

        return mHourInfo;
    }

    private void AddBreaks(Timestamp startTime, Timestamp endTime) {
        AddLaunchBreak();
        AddEveningBreak();
        AddNightBreak();
    }

    private void AddNightBreak() {
    }

    private void AddEveningBreak() {
        
    }

    private void AddLaunchBreak() {
        
    }

    private void AddLaunchBreakToHalfDay() {
        if(Timestamp.isOverlapp(mHourInfo.mArrivalTime, mHourInfo.mHalfDay,
                mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END))
        {
            mHourInfo.mHalfDay = Timestamp.addOverlapp(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                    mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END);
            mHourInfo.tookLaunchBreak = true;
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
