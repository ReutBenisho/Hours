package com.example.hours.calcUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hours.utils.Defaults;

public class HoursInfo {
    public UserInfo userInfo;
    public CalcInfo calcInfo;
    public Breaks breaks;

    public HoursInfo(){
        clear();
    }

    public void clear(){
        if(userInfo == null)
            userInfo = new UserInfo();
        if(calcInfo == null)
            calcInfo = new CalcInfo();
        if(breaks == null)
            breaks = new Breaks();
        userInfo.clear();
        calcInfo.clear();
        breaks.clear();
        breaks.preDefinedBreaks.add(new Break(Defaults.getLunchStart(), Defaults.getLunchEnd()));

        //Not really a breaks - if you reach 19:36, they'll subtract automatically 12 minutes of your time.
        //it doesn't matter if you left a minute later...
        //and by some of the testings - not always??
        //mPreDefinedBreaks.add(new Break(Defaults.EVENING_BREAK_START, Defaults.EVENING_BREAK_END, false));
        //Not sure if this is a real break...
        //mPreDefinedBreaks.add(new Break(Defaults.NIGHT_BREAK_START, Defaults.NIGHT_BREAK_END, false));
        clearCalculatedInfo();
    }

    public void clearCalculatedInfo() {
        for(int i = 0; breaks.preDefinedBreaks != null && i < breaks.preDefinedBreaks.size(); i++)
        {
            Break current = breaks.preDefinedBreaks.get(i);
            current.tookBreak = false;
        }
        for(int i = 0; breaks.customBreaks != null && i < breaks.customBreaks.size(); i++)
        {
            Break current = breaks.customBreaks.get(i);
            current.tookBreak = false;
        }
        breaks.tookEveningBreak = false;
        breaks.allBreaks.clear();
        calcInfo.clear();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof HoursInfo))
            return false;

        HoursInfo objHoursInfo = (HoursInfo) obj;
        if(!userInfo.equals(objHoursInfo.userInfo))
            return false;
        if(!breaks.equals(objHoursInfo.breaks))
            return false;
        if(!calcInfo.equals(objHoursInfo.calcInfo))
            return false;
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";
        s += userInfo.toString();
        s += breaks.toString();
        s += calcInfo.toString();
        return s;
    }

}
