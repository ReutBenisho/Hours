package com.example.hours;

import android.util.Pair;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HoursInfo {
    public Timestamp mArrivalTime;
    public Timestamp mHalfDay;
    public Timestamp mFullDay;
    public Timestamp mZeroHours;
    public Timestamp m3AndHalfHours;
    public Timestamp m6Hours;
    public boolean mIsArrivalDuringLaunchBreak;
    public boolean mTookLaunchBreak;
    public boolean mTookEveningBreak;
    public boolean mTookNightBreak;
    public ArrayList<Midday> mCustomBreaks;
    public ArrayList<Boolean> mTookCustomBreak;

    public HoursInfo(){
        clear();
    }

    public void clear(){
        if(mArrivalTime == null)
            mArrivalTime = new Timestamp();
        mArrivalTime.clear();
        if(mCustomBreaks == null)
            mCustomBreaks = new ArrayList<>();
        if(mCustomBreaks.size() == 0)
        {
            mCustomBreaks.add(new Midday());
        }
        mCustomBreaks.get(0).exit.setTime(0, 0);
        mCustomBreaks.get(0).arrival.setTime(0, 0);

        clearAllButUserTime();
    }

    public void clearAllButUserTime(){
        if(mHalfDay != null)
            mHalfDay.clear();
        if(mFullDay != null)
            mFullDay.clear();
        if(mZeroHours != null)
            mZeroHours.clear();
        if(m3AndHalfHours != null)
            m3AndHalfHours.clear();
        if(m6Hours != null)
            m6Hours.clear();
        mIsArrivalDuringLaunchBreak = false;
        mTookLaunchBreak = false;
        mTookEveningBreak = false;
        mTookNightBreak = false;
        mTookCustomBreak = new ArrayList<>();
        mTookCustomBreak.add(false);

    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof HoursInfo))
            return false;

        HoursInfo objHoursInfo = (HoursInfo) obj;
        if(!mArrivalTime.equals(objHoursInfo.mArrivalTime))
            return false;
        if(!mCustomBreaks.get(0).exit.equals(objHoursInfo.mCustomBreaks.get(0).exit))
            return false;
        if(!mCustomBreaks.get(0).arrival.equals(objHoursInfo.mCustomBreaks.get(0).arrival))
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
        for(int i = 0; i < mTookCustomBreak.size(); i++){
            if(mTookCustomBreak.get(i) != objHoursInfo.mTookCustomBreak.get(i))
                return false;
        }
        return true;
    }

    public static class Midday{
        public Timestamp exit;
        public Timestamp arrival;

        public Midday(){
            exit = new Timestamp();
            arrival = new Timestamp();
        }

        public Midday(Timestamp exit_time, Timestamp arrival_time){
            exit = exit_time;
            arrival = arrival_time;
        }
    }
}
