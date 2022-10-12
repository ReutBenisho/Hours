package com.example.hours.utils;

import com.example.hours.calcUtils.Timestamp;

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
        public static Timestamp ARRIVAL_TIME = new Timestamp(System.ARRIVAL_TIME);
        public static Timestamp EXIT_TIME = System.ARRIVAL_TIME.add(System.HALF_DAY).add(System.HALF_DAY).add(System.LUNCH_BREAK_DURATION);
        public static Timestamp HALF_DAY = new Timestamp(System.HALF_DAY);
        public static Timestamp LUNCH_BREAK_START = new Timestamp(System.LUNCH_BREAK_START);
        public static Timestamp LUNCH_BREAK_DURATION = new Timestamp(System.LUNCH_BREAK_DURATION);

        public static Timestamp ZERO_HOURS = new Timestamp(System.ZERO_HOURS);
        public static Timestamp ADDITIONAL_HOURS = new Timestamp(System.ADDITIONAL_HOURS);
        public static Timestamp EXTRA_ADDITIONAL_HOURS = new Timestamp(System.EXTRA_ADDITIONAL_HOURS);
        public static Timestamp ADDITIONAL_125_HOURS = new Timestamp(System.ADDITIONAL_125_HOURS);

        public static Timestamp EVENING_BREAK_START = new Timestamp(System.EVENING_BREAK_START);
        public static Timestamp EVENING_BREAK_DURATION = new Timestamp(System.EVENING_BREAK_DURATION);

        public static Timestamp NIGHT_BREAK_START = new Timestamp(System.NIGHT_BREAK_START);
        public static Timestamp NIGHT_BREAK_DURATION = new Timestamp(System.NIGHT_BREAK_DURATION);
    }

    public static Timestamp getArrival(){
        return User.ARRIVAL_TIME;
    }

    public static Timestamp getHalfDay(){
        if(useSystem)
            return System.HALF_DAY;
        return User.HALF_DAY;
    }

    public static Timestamp getFullDay(){
        return getHalfDay().add(getHalfDay());
    }

    public static Timestamp getLunchStart(){
        if(useSystem)
            return System.LUNCH_BREAK_START;
        return User.LUNCH_BREAK_START;
    }

    public static Timestamp getLunchDuration(){
        if(useSystem)
            return System.LUNCH_BREAK_DURATION;
        return User.LUNCH_BREAK_DURATION;
    }

    public static Timestamp getLunchEnd(){
        return getLunchStart().add(getLunchDuration());
    }

    public static Timestamp getFullDayWithLunchBreak(){
        return getFullDay().add(getLunchDuration());
    }

    public static Timestamp getZeroHours(){
        if(useSystem)
            return System.ZERO_HOURS;
        return User.ZERO_HOURS;
    }

    public static Timestamp getAdditionalHours(){
        if(useSystem)
            return System.ADDITIONAL_HOURS;
        return User.ADDITIONAL_HOURS;
    }

    public static Timestamp getExtraAdditionalHours(){
        if(useSystem)
            return System.EXTRA_ADDITIONAL_HOURS;
        return User.EXTRA_ADDITIONAL_HOURS;
    }

    public static Timestamp getMaxAdditionalHours(){
        return getZeroHours().add(getAdditionalHours()).add(getExtraAdditionalHours());
    }

    public static Timestamp getAdditional125Hours(){
        if(useSystem)
            return System.ADDITIONAL_125_HOURS;
        return User.ADDITIONAL_125_HOURS;
    }

    public static Timestamp getEveningStart(){
        if(useSystem)
            return System.EVENING_BREAK_START;
        return User.EVENING_BREAK_START;
    }

    public static Timestamp getEveningDuration(){
        if(useSystem)
            return System.EVENING_BREAK_DURATION;
        return User.EVENING_BREAK_DURATION;
    }

    public static Timestamp getEveningEnd(){
        return getEveningStart().add(getEveningDuration());
    }

    public static Timestamp getNightStart(){
        if(useSystem)
            return System.NIGHT_BREAK_START;
        return User.NIGHT_BREAK_START;
    }

    public static Timestamp getNightDuration(){
        if(useSystem)
            return System.NIGHT_BREAK_DURATION;
        return User.NIGHT_BREAK_DURATION;
    }

    public static Timestamp getNightEnd(){
        return getNightStart().add(getNightDuration());
    }

    public static Timestamp getExit(){
        return User.EXIT_TIME;
    }

}
