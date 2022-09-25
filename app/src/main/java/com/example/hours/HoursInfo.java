package com.example.hours;

import androidx.annotation.Nullable;

public class HoursInfo {
    public Timestamp mArrivalTime;
    public Timestamp mHalfDay;
    public Timestamp mFullDay;
    public Timestamp mZeroHours;
    public Timestamp m3AndHalfHours;
    public Timestamp m6Hours;
    public boolean mIsArrivalDuringLaunchBreak = false;
    public boolean mTookLaunchBreak = false;
    public boolean mTookEveningBreak = false;
    public boolean mTookNightBreak = false;

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof HoursInfo))
            return false;

        HoursInfo objHoursInfo = (HoursInfo) obj;
        if(!mArrivalTime.equals(objHoursInfo.mArrivalTime))
            return false;
        if(!mHalfDay.equals(objHoursInfo.mHalfDay))
            return false;
        if(!mFullDay.equals(objHoursInfo.mFullDay))
            return false;
        if(!mZeroHours.equals(objHoursInfo.mZeroHours))
            return false;
        if(!m3AndHalfHours.equals(objHoursInfo.m3AndHalfHours))
            return false;
        if(!m6Hours.equals(objHoursInfo.m6Hours))
            return false;
        if(mIsArrivalDuringLaunchBreak != objHoursInfo.mIsArrivalDuringLaunchBreak)
            return false;
        if(mTookLaunchBreak != objHoursInfo.mTookLaunchBreak)
            return false;
        if(mTookEveningBreak != objHoursInfo.mTookEveningBreak)
            return false;
        if(mTookNightBreak != objHoursInfo.mTookNightBreak)
            return false;
        return true;
    }
}
