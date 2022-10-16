package com.example.hours.calcUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomBreak {
    public boolean isEnabled;
    public BreakTimes times;

    public CustomBreak(boolean _isEnabled, BreakTimes _times) {
        isEnabled = _isEnabled;
        times = _times.copy();
    }

    public CustomBreak() {
        isEnabled = false;
        times = new BreakTimes();
    }

    public CustomBreak copy() {
        return new CustomBreak(isEnabled, times);
    }

    public static String serialize(ArrayList<CustomBreak> list){
        String str = "";
        for(int i = 0; i < list.size(); i++){
            str += list.get(i).isEnabled +","
                    + list.get(i).times.start + ","
                    + list.get(i).times.end+ ";";
        }
        return str;
    }

    public static ArrayList<CustomBreak> deseralize(String str){
        ArrayList<CustomBreak> list = new ArrayList<>();
        if(str == "")
            return list;
        String[] breaks = str.split(";");
        for (int i = 0; i < breaks.length; i++){
            String[] breakData = breaks[i].split(",");
            boolean isEnabled = Boolean.parseBoolean(breakData[0]);
            Timestamp start = new Timestamp(breakData[1]);
            Timestamp end = new Timestamp(breakData[2]);
            list.add(new CustomBreak(isEnabled, new BreakTimes(start, end)));
        }
        return list;
    }
}
