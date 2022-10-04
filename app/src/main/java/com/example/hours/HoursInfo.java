package com.example.hours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HoursInfo {
    public Timestamp mArrivalTime;
    public Timestamp mExitTime;
    public Timestamp mHalfDay;
    public Timestamp mFullDay;
    public Timestamp mZeroHours;
    public Timestamp m3AndHalfHours;
    public Timestamp m6Hours;
    public ArrayList<Break> mPreDefinedBreaks;
    public ArrayList<Break> mCustomBreaks;
    public ArrayList<Break> mAllBreaks;
    public Totals mTotalTime;
    public boolean mTookEveningBreak;

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
        if(mPreDefinedBreaks == null)
            mPreDefinedBreaks = new ArrayList<>();
        mPreDefinedBreaks.add(new Break(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END, false));
        //Not really a breaks - if you reach 19:36, they'll subtrack automatically 12 minutes of your time.
        //it doesn't matter if you left a minute later...
        //mPreDefinedBreaks.add(new Break(Defaults.EVENING_BREAK_START, Defaults.EVENING_BREAK_END, false));
        //Not sure if this is a real break...
        //mPreDefinedBreaks.add(new Break(Defaults.NIGHT_BREAK_START, Defaults.NIGHT_BREAK_END, false));

        clearGeneralInfo();
        clearTotalTime();
    }

    public void clearTotalTime() {
        if(mTotalTime == null)
            mTotalTime = new Totals();
        mTotalTime.clear();
    }

    public void clearGeneralInfo(){
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
        for(int i = 0; i < mPreDefinedBreaks.size(); i++)
        {
            Break current = mPreDefinedBreaks.get(i);
            current.tookBreak = false;
            mPreDefinedBreaks.set(i, current);
        }
        if(mCustomBreaks == null)
            mCustomBreaks = new ArrayList<>();
        for(int i = 0; i < mCustomBreaks.size(); i++)
        {
            Break current = mCustomBreaks.get(i);
            current.tookBreak = false;
            mCustomBreaks.set(i, current);
        }
        if(mAllBreaks == null)
            mAllBreaks = new ArrayList<>();
        mAllBreaks.clear();
        mTookEveningBreak = false;
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
            if(!mCustomBreaks.get(i).breakTimes.start.equals(objHoursInfo.mCustomBreaks.get(i).breakTimes.start))
                return false;
            if(!mCustomBreaks.get(i).breakTimes.end.equals(objHoursInfo.mCustomBreaks.get(i).breakTimes.end))
                return false;
            if(mCustomBreaks.get(i).tookBreak != objHoursInfo.mCustomBreaks.get(i).tookBreak)
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

        return true;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";
        if(mArrivalTime != null)
            s += "Arrival: " + mArrivalTime.toString();
        if(mHalfDay != null)
            s += "\nHalf day: " + mHalfDay.toString();
        if(mFullDay != null)
            s += "\nFull day: " + mFullDay.toString();
        if(mZeroHours != null)
            s += "\nZero hours: " + mZeroHours.toString();
        if(m3AndHalfHours != null)
            s += "\n3 and half hours: " + m3AndHalfHours.toString();
        if(m6Hours != null)
            s += "\n6 hours: " + m6Hours.toString();

        for(int i = 0; i < mPreDefinedBreaks.size(); i++){
            s += "\nPre defined break #" + i + " start: " + mPreDefinedBreaks.get(i).breakTimes.start + " end: " + mPreDefinedBreaks.get(i).breakTimes.end;
        }

        for(int i = 0; i < mCustomBreaks.size(); i++){
            s += "\nCustom break #" + i + " start: " + mCustomBreaks.get(i).breakTimes.start + " end: " + mCustomBreaks.get(i).breakTimes.end;
        }

        if(mExitTime != null)
            s += "\nExit time: " + mExitTime.toString();
        if(mTotalTime != null) {
            s += "\nTotal time: " + mTotalTime.total.toString();
            s += "\nZero hours: " + mTotalTime.zeroHours.toString();
            s += "\nAdditional hours: " + mTotalTime.additionalHours.toString();
            s += "\nIs full day: " + mTotalTime.isFullDay;
        }
        return s;
    }

}
