package com.example.hours.calcUtils;

import androidx.annotation.NonNull;

import com.example.hours.R;
import com.example.hours.utils.App;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomBreak {
    public boolean isEnabled;
    public BreakTimes times;
    public boolean[] days;

    public CustomBreak(boolean _isEnabled, BreakTimes _times, boolean[] _days) {
        isEnabled = _isEnabled;
        times = _times.copy();
        days = new boolean[6];
        for(int i = 0; i < 6; i ++)
            days[i] = _days[i];
    }

    public CustomBreak() {
        isEnabled = false;
        times = new BreakTimes();
        days = new boolean[6];
        for(int i = 0; i < 6; i ++)
            days[i] = false;

    }

    public CustomBreak copy() {
        return new CustomBreak(isEnabled, times, days);
    }

    public static String serialize(ArrayList<CustomBreak> list){
        String str = "";
        for(int i = 0; i < list.size(); i++){
            str += list.get(i).toString() + ";";
        }
        return str;
    }

    public static ArrayList<CustomBreak> deseralize(String str){
        ArrayList<CustomBreak> list = new ArrayList<>();
        if(str == null || str == "")
            return list;
        String[] breaks = str.split(";");
        for (int i = 0; i < breaks.length; i++){
            String[] breakData = breaks[i].split(",");
            boolean isEnabled = Boolean.parseBoolean(breakData[0]);
            Timestamp start = new Timestamp(breakData[1]);
            Timestamp end = new Timestamp(breakData[2]);
            boolean[] days = new boolean[6];
            for(int j = 0; j < 6; j ++) {
                days[j] = Boolean.parseBoolean(breakData[3 + j]);
            }

            list.add(new CustomBreak(isEnabled, new BreakTimes(start, end), days));
        }
        return list;
    }

    @NonNull
    @Override
    public String toString() {
        String s = isEnabled + "," + times.start + "," + times.end;
        for(int i = 0; i < 6; i ++)
            s += "," + days[i];
        return s;
    }
    public String customBreakString()
    {
        String s = times.start + "-" + times.end + ": ";
        if(days[0])
            s += App.getStr(R.string.sunday_abbriviated) + ", ";
        if(days[1])
            s += App.getStr(R.string.monday_abbriviated) + ", ";
        if(days[2])
            s += App.getStr(R.string.tuesday_abbriviated) + ", ";
        if(days[3])
            s += App.getStr(R.string.wednesday_abbriviated) + ", ";
        if(days[4])
            s += App.getStr(R.string.thursday_abbriviated) + ", ";
        if(days[5])
            s += App.getStr(R.string.friday_abbriviated) + ", ";
        return s;
    }
}
