package com.example.hours;

public class CalcHours {
    private static CalcHours mInstance = null;
    private Defaults mDefaults;
    private HoursInfo mHourInfo;

    private CalcHours()
    {
        mDefaults = Defaults.getInstance();
        mHourInfo = new HoursInfo();
    }

    public static CalcHours getInstance(){
        if(mInstance == null)
            mInstance = new CalcHours();
        return mInstance;
    }

    public HoursInfo GetInfoByArrivalTime(CalcTime arrivalTime) {
        mHourInfo = new HoursInfo();
        mHourInfo.mArrivalTime = new CalcTime(arrivalTime);
        mHourInfo.mHalfDay = mHourInfo.mArrivalTime.add(mDefaults.HALF_DAY);
        mHourInfo.mFullDay = mHourInfo.mHalfDay.add(mDefaults.HALF_DAY);
        mHourInfo.mZeroHours = mHourInfo.mFullDay.add(mDefaults.ZERO_HOURS);
        mHourInfo.m3AndHalfHours = mHourInfo.mZeroHours.add(mDefaults.ADDITIONAL_HOURS);
        mHourInfo.m6Hours = mHourInfo.m3AndHalfHours.add(mDefaults.EXTRA_ADDITIONAL_HOURS);

        return mHourInfo;
    }

    private void AddBreaks(CalcTime startTime, CalcTime endTime) {
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
        if(CalcTime.isOverlapp(mHourInfo.mArrivalTime, mHourInfo.mHalfDay,
                mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END))
        {
            mHourInfo.mHalfDay = CalcTime.addOverlapp(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                    mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END);
            mHourInfo.tookLaunchBreak = true;
        }
    }

    private void AddLaunchBreakToFullDay() {
        if(!mHourInfo.tookLaunchBreak && CalcTime.isOverlapp(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END))
        {
            mHourInfo.mFullDay = CalcTime.addOverlapp(mHourInfo.mArrivalTime, mHourInfo.mFullDay,
                    mDefaults.LAUNCH_BREAK_START, mDefaults.LAUNCH_BREAK_END);
            mHourInfo.tookLaunchBreak = true;
        }
    }
}
