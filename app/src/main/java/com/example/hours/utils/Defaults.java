package com.example.hours.utils;

import android.util.Log;

import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.calcUtils.Timestamp;

import java.util.ArrayList;
import java.util.List;

public class Defaults {

    public enum Breaks_e{
        PRE_DEFINED,
        CUSTOM,
    }

    public static boolean useSystem = true;

    public static class System {
        public static final Timestamp ARRIVAL_TIME = new Timestamp(7, 30);

        public static final Timestamp HALF_DAY = new Timestamp(4, 12);
        public static final Timestamp LUNCH_BREAK_START = new Timestamp(13, 30);
        public static final Timestamp LUNCH_BREAK_DURATION = new Timestamp(0, 30);

        public static final Timestamp ZERO_HOURS = new Timestamp(1, 0);
        public static final Timestamp ADDITIONAL_HOURS = new Timestamp(2, 30);
        public static final Timestamp EXTRA_ADDITIONAL_HOURS = new Timestamp(2, 30);
        public static final Timestamp ADDITIONAL_125_HOURS = new Timestamp(2, 0);

        public static final Timestamp EVENING_BREAK_START = new Timestamp(19, 36);
        public static final Timestamp EVENING_BREAK_DURATION = new Timestamp(0, 12);

        public static final Timestamp NIGHT_BREAK_START = new Timestamp(22, 30);
        public static final Timestamp NIGHT_BREAK_DURATION = new Timestamp(0, 30);
    }

    public static class User {
        public static Timestamp ARRIVAL_TIME = System.ARRIVAL_TIME.copy();
        public static Timestamp EXIT_TIME = System.ARRIVAL_TIME.add(System.HALF_DAY).add(System.HALF_DAY).add(System.LUNCH_BREAK_DURATION);
        public static Timestamp HALF_DAY = System.HALF_DAY.copy();
        public static Timestamp LUNCH_BREAK_START = System.LUNCH_BREAK_START.copy();
        public static Timestamp LUNCH_BREAK_DURATION = System.LUNCH_BREAK_DURATION.copy();

        public static Timestamp ZERO_HOURS = System.ZERO_HOURS.copy();
        public static Timestamp ADDITIONAL_HOURS = System.ADDITIONAL_HOURS.copy();
        public static Timestamp EXTRA_ADDITIONAL_HOURS = System.EXTRA_ADDITIONAL_HOURS.copy();
        public static Timestamp ADDITIONAL_125_HOURS = System.ADDITIONAL_125_HOURS.copy();

        public static Timestamp EVENING_BREAK_START = System.EVENING_BREAK_START.copy();
        public static Timestamp EVENING_BREAK_DURATION = System.EVENING_BREAK_DURATION.copy();

        public static Timestamp NIGHT_BREAK_START = System.NIGHT_BREAK_START.copy();
        public static Timestamp NIGHT_BREAK_DURATION = System.NIGHT_BREAK_DURATION.copy();

        public static ArrayList<CustomBreak> CUSTOM_BREAKS_LIST = new ArrayList<>();
    }

    public static Timestamp getArrival(){
        Timestamp temp = User.ARRIVAL_TIME;
        Log.d("Defaults", "arrival is " + temp.toString());
        return User.ARRIVAL_TIME.copy();
    }

    public static Timestamp getHalfDay(){
        if(useSystem)
            return System.HALF_DAY.copy();
        return User.HALF_DAY.copy();
    }

    public static Timestamp getFullDay(){

        return getHalfDay().add(getHalfDay());
    }

    public static Timestamp getLunchStart(){
        if(useSystem)
            return System.LUNCH_BREAK_START.copy();
        return User.LUNCH_BREAK_START.copy();
    }

    public static Timestamp getLunchDuration(){
        if(useSystem)
            return System.LUNCH_BREAK_DURATION.copy();
        return User.LUNCH_BREAK_DURATION.copy();
    }

    public static Timestamp getLunchEnd(){

        return getLunchStart().add(getLunchDuration());
    }

    public static Timestamp getFullDayWithLunchBreak(){
        return getFullDay().add(getLunchDuration());
    }

    public static Timestamp getZeroHours(){
        if(useSystem)
            return System.ZERO_HOURS.copy();
        return User.ZERO_HOURS.copy();
    }

    public static Timestamp getAdditionalHours(){
        if(useSystem)
            return System.ADDITIONAL_HOURS.copy();
        return User.ADDITIONAL_HOURS.copy();
    }

    public static Timestamp getExtraAdditionalHours(){
        if(useSystem)
            return System.EXTRA_ADDITIONAL_HOURS.copy();
        return User.EXTRA_ADDITIONAL_HOURS.copy();
    }

    public static Timestamp getMaxAdditionalHours(){
        return getZeroHours().add(getAdditionalHours()).add(getExtraAdditionalHours());
    }

    public static Timestamp getAdditional125Hours(){
        if(useSystem)
            return System.ADDITIONAL_125_HOURS.copy();
        return User.ADDITIONAL_125_HOURS.copy();
    }

    public static Timestamp getEveningStart(){
        if(useSystem)
            return System.EVENING_BREAK_START.copy();
        return User.EVENING_BREAK_START.copy();
    }

    public static Timestamp getEveningDuration(){
        if(useSystem)
            return System.EVENING_BREAK_DURATION.copy();
        return User.EVENING_BREAK_DURATION.copy();
    }

    public static Timestamp getEveningEnd(){

        return getEveningStart().add(getEveningDuration());
    }

    public static Timestamp getNightStart(){
        if(useSystem)
            return System.NIGHT_BREAK_START.copy();
        return User.NIGHT_BREAK_START.copy();
    }

    public static Timestamp getNightDuration(){
        if(useSystem)
            return System.NIGHT_BREAK_DURATION.copy();
        return User.NIGHT_BREAK_DURATION.copy();
    }

    public static Timestamp getNightEnd(){

        return getNightStart().add(getNightDuration());
    }

    public static Timestamp getExit(){
        Timestamp temp = User.EXIT_TIME;
        Log.d("Defaults", "exit is " + temp.toString());
        return User.EXIT_TIME.copy();
    }

    public static ArrayList<CustomBreak> getCustomBreaksList()
    {
        ArrayList<CustomBreak> list = new ArrayList<>();
        for(int i = 0; i < User.CUSTOM_BREAKS_LIST.size(); i++)
        {
            list.add(User.CUSTOM_BREAKS_LIST.get(i).copy());
        }
        return list;
    }

    public static void clearCustomBreaksList(){

        User.CUSTOM_BREAKS_LIST.clear();
    }

    public static void addBreakToList(CustomBreak breakPref){
        User.CUSTOM_BREAKS_LIST.add(breakPref.copy());
    }

    public static void setCustomBreaks(ArrayList<CustomBreak> list){
        Defaults.clearCustomBreaksList();
        for(int i = 0; i < list.size(); i++)
        {
            Defaults.addBreakToList(list.get(i));
        }
    }
}
