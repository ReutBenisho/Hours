package com.example.hours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HoursInfo {
    public Timestamp arrivalTime;
    public Timestamp exitTime;
    public Timestamp halfDay;
    public Timestamp fullDay;
    public Timestamp zeroHours;
    public Timestamp additional3AndHalfHours;
    public Timestamp additional6Hours;
    public ArrayList<Break> preDefinedBreaks;
    public ArrayList<Break> customBreaks;
    public ArrayList<Break> allBreaks;
    public Totals totalTime;
    public boolean tookEveningBreak;
    public boolean isFriday;

    public HoursInfo(){
        clear();
    }

    public void clear(){
        if(arrivalTime == null)
            arrivalTime = new Timestamp();
        arrivalTime.clear();
        if(exitTime == null)
            exitTime = new Timestamp();
        exitTime.clear();
        isFriday = false;
        if(preDefinedBreaks == null)
            preDefinedBreaks = new ArrayList<>();
        preDefinedBreaks.clear();
        preDefinedBreaks.add(new Break(Defaults.LAUNCH_BREAK_START, Defaults.LAUNCH_BREAK_END, false));

        if(customBreaks == null)
            customBreaks = new ArrayList<>();
        customBreaks.clear();
        if(allBreaks == null)
            allBreaks = new ArrayList<>();
        allBreaks.clear();



        //Not really a breaks - if you reach 19:36, they'll subtrack automatically 12 minutes of your time.
        //it doesn't matter if you left a minute later...
        //mPreDefinedBreaks.add(new Break(Defaults.EVENING_BREAK_START, Defaults.EVENING_BREAK_END, false));
        //Not sure if this is a real break...
        //mPreDefinedBreaks.add(new Break(Defaults.NIGHT_BREAK_START, Defaults.NIGHT_BREAK_END, false));

        clearGeneralInfo();
        clearTotalTime();
    }

    public void clearTotalTime() {
        if(totalTime == null)
            totalTime = new Totals();
        totalTime.clear();
    }

    public void clearGeneralInfo(){
        if(halfDay == null)
            halfDay = new Timestamp();
        halfDay.clear();
        if(fullDay == null)
            fullDay = new Timestamp();
        fullDay.clear();
        if(zeroHours == null)
            zeroHours = new Timestamp();
        zeroHours.clear();
        if(additional3AndHalfHours == null)
            additional3AndHalfHours = new Timestamp();
        additional3AndHalfHours.clear();
        if(additional6Hours == null)
            additional6Hours = new Timestamp();
        additional6Hours.clear();
        if(preDefinedBreaks == null)
            preDefinedBreaks = new ArrayList<>();
        for(int i = 0; i < preDefinedBreaks.size(); i++)
        {
            Break current = preDefinedBreaks.get(i);
            current.tookBreak = false;
            preDefinedBreaks.set(i, current);
        }
        if(customBreaks == null)
            customBreaks = new ArrayList<>();
        for(int i = 0; i < customBreaks.size(); i++)
        {
            Break current = customBreaks.get(i);
            current.tookBreak = false;
        }
        if(allBreaks == null)
            allBreaks = new ArrayList<>();
        allBreaks.clear();
        tookEveningBreak = false;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof HoursInfo))
            return false;

        HoursInfo objHoursInfo = (HoursInfo) obj;
        if(!arrivalTime.equals(objHoursInfo.arrivalTime))
            return false;
        if(customBreaks.size() != objHoursInfo.customBreaks.size())
            return false;
        for(int i = 9; i< customBreaks.size(); i++){
            if(!customBreaks.get(i).breakTimes.start.equals(objHoursInfo.customBreaks.get(i).breakTimes.start))
                return false;
            if(!customBreaks.get(i).breakTimes.end.equals(objHoursInfo.customBreaks.get(i).breakTimes.end))
                return false;
            if(customBreaks.get(i).tookBreak != objHoursInfo.customBreaks.get(i).tookBreak)
                return false;
        }
        if(!exitTime.equals(objHoursInfo.exitTime))
            return false;
        if(isFriday != objHoursInfo.isFriday)
            return false;
        if(!totalTime.equals(objHoursInfo.totalTime))
            return false;
        if(!halfDay.equals(objHoursInfo.halfDay))
            return false;
        if(!fullDay.equals(objHoursInfo.fullDay))
            return false;
        if(!zeroHours.equals(objHoursInfo.zeroHours))
            return false;
        if(!additional3AndHalfHours.equals(objHoursInfo.additional3AndHalfHours))
            return false;
        if(!additional6Hours.equals(objHoursInfo.additional6Hours))
            return false;

        return true;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";
        if(arrivalTime != null)
            s += "Arrival: " + arrivalTime.toString();
        if(halfDay != null)
            s += "\nHalf day: " + halfDay.toString();
        if(fullDay != null)
            s += "\nFull day: " + fullDay.toString();
        if(zeroHours != null)
            s += "\nZero hours: " + zeroHours.toString();
        if(additional3AndHalfHours != null)
            s += "\nAdditional 3 and half hours: " + additional3AndHalfHours.toString();
        if(additional6Hours != null)
            s += "\nAdditional 6 hours: " + additional6Hours.toString();

        for(int i = 0; i < preDefinedBreaks.size(); i++){
            s += "\nPre defined break #" + i + " start: " + preDefinedBreaks.get(i).breakTimes.start + " end: " + preDefinedBreaks.get(i).breakTimes.end;
        }

        for(int i = 0; i < customBreaks.size(); i++){
            s += "\nCustom break #" + i + " start: " + customBreaks.get(i).breakTimes.start + " end: " + customBreaks.get(i).breakTimes.end;
        }

        if(exitTime != null)
            s += "\nExit time: " + exitTime.toString();

        s += "\nFriday: " + isFriday;

        if(totalTime != null) {
            s += "\nTotal time: " + totalTime.total.toString();
            s += "\nZero hours: " + totalTime.zeroHours.toString();
            s += "\nAdditional hours: " + totalTime.additionalHours.toString();
            s += "\nIs full day: " + totalTime.isFullDay;
            s += "\nGlobal absence: " + totalTime.globalAbsence.toString();
            s += "\nUnpaid absence: " + totalTime.unpaidAbsence.toString();
        }
        return s;
    }

}
