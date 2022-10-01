package com.example.hours;

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
    public Timestamp mExitTime;
    public Totals mTotalTime;

    public HoursInfo(){
        clear();
    }

    public void clear(){
        if(mArrivalTime == null)
            mArrivalTime = new Timestamp();
        mArrivalTime.clear();
        if(mCustomBreaks == null)
            mCustomBreaks = new ArrayList<>();
        for(int i = 0; i < mCustomBreaks.size(); i++){
            mCustomBreaks.remove(i);
        }
        if(mExitTime == null)
            mExitTime = new Timestamp();
        mExitTime.clear();

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
        if(mTookCustomBreak == null)
            mTookCustomBreak = new ArrayList<>();
        for(int i = 0; i < mTookCustomBreak.size(); i++)
        {
            mTookCustomBreak.set(i, false);
        }
        if(mTotalTime == null)
            mTotalTime = new Totals();
        mTotalTime.clear();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof HoursInfo))
            return false;

        HoursInfo objHoursInfo = (HoursInfo) obj;
        if(!mArrivalTime.equals(objHoursInfo.mArrivalTime))
            return false;
        if(mCustomBreaks.size() != objHoursInfo.mCustomBreaks.size())
            return false;
        for(int i = 9; i< mCustomBreaks.size(); i++){
            if(!mCustomBreaks.get(i).exit.equals(objHoursInfo.mCustomBreaks.get(i).exit))
                return false;
            if(!mCustomBreaks.get(i).arrival.equals(objHoursInfo.mCustomBreaks.get(i).arrival))
                return false;
        }
        if(!mExitTime.equals(objHoursInfo.mExitTime))
            return false;
        if(!mTotalTime.equals(objHoursInfo.mTotalTime))
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
        public final Timestamp exit;
        public final Timestamp arrival;

        public Midday(){
            exit = new Timestamp();
            arrival = new Timestamp();
        }

        public Midday(Timestamp exit_time, Timestamp arrival_time){
            exit = exit_time;
            arrival = arrival_time;
        }
    }

    public static class Totals{
        public Timestamp total;
        public boolean isFullDay;
        public Timestamp zeroHours;
        public Timestamp additionalHours;

        public Totals(){
            clear();
        }
        public void clear() {
            if(total == null)
                total = new Timestamp();
            total.clear();
            isFullDay = false;
            if(zeroHours == null)
                zeroHours = new Timestamp();
            zeroHours.clear();
            if(additionalHours == null)
                additionalHours = new Timestamp();
            additionalHours.clear();
        }
    }

}
