package com.example.hours;

import java.time.LocalTime;
import java.util.Locale;

public class CalcTime {
    private LocalTime mTime;

    public CalcTime(){
        mTime = LocalTime.of(0, 0);
    }

    public CalcTime(int hour){
        mTime = LocalTime.of(hour, 0);
    }

    public CalcTime(int hour, int minute){
        mTime = LocalTime.of(hour, minute);
    }

    public CalcTime(LocalTime other){
        mTime = LocalTime.of(other.getHour(), other.getMinute());
    }

    public CalcTime(CalcTime other){
        mTime = LocalTime.of(other.mTime.getHour(), other.mTime.getMinute());
    }

    public LocalTime toLocalTime(){
        return mTime;
    }

    public boolean isBefore(CalcTime beforeTime){
        return mTime.isBefore(beforeTime.mTime);
    }

    public boolean isAfter(CalcTime beforeTime){
        return mTime.isAfter(beforeTime.mTime);
    }

    public boolean isBetween(CalcTime first, CalcTime second){
        return mTime.isAfter(first.mTime) && mTime.isBefore(second.mTime);
    }

    public CalcTime add(int hour, int minute){
        CalcTime newTime = new CalcTime(mTime);
        newTime.mTime = newTime.mTime.plusHours(hour);
        newTime.mTime = newTime.mTime.plusMinutes(minute);
        return newTime;
    }

    public CalcTime add(CalcTime timeToAdd){
        CalcTime newTime = new CalcTime(mTime).add(timeToAdd.mTime.getHour(), timeToAdd.mTime.getMinute());
        return newTime;
    }

    public CalcTime sub(int hour, int minute){
        CalcTime newTime = new CalcTime(mTime);
        newTime.mTime = newTime.mTime.minusHours(hour);
        newTime.mTime = newTime.mTime.minusMinutes(minute);
        return newTime;
    }

    public CalcTime sub(CalcTime timeToAdd){
        CalcTime newTime = new CalcTime(mTime).sub(timeToAdd.mTime.getHour(), timeToAdd.mTime.getMinute());
        return newTime;
    }

    public String toString(){
        return String.format(Locale.getDefault(), "%02d:%02d", mTime.getHour(), mTime.getMinute());
    }

    public void setTime(int hour, int minute){
        mTime = LocalTime.of(hour, minute);
    }

    public static boolean isOverlapp(CalcTime startRange1, CalcTime endRange1,
                                     CalcTime startRange2, CalcTime endRange2){
        return !(endRange1.isBefore(startRange2) || endRange2.isBefore(startRange1));
    }

    public static CalcTime addOverlapp(CalcTime startRange1, CalcTime endRange1,
                                     CalcTime startRange2, CalcTime endRange2){
        CalcTime newTime = new CalcTime(endRange1);
        if(isOverlapp(startRange1, endRange1, startRange2, endRange2)){
            CalcTime startOverlap = CalcTime.getLatest(startRange1, startRange2);
            CalcTime endOverlap = CalcTime.getEarliest(endRange1, endRange2);
            CalcTime totalOverlap = endOverlap.sub(startOverlap);
            newTime.add(totalOverlap);
        }
        return newTime;
    }

    public static CalcTime toCalcTime(LocalTime timeToConvert){
        return new CalcTime(timeToConvert);
    }

    public static LocalTime toLocalTime(CalcTime timeToConvert){
        return timeToConvert.mTime;
    }

    public static CalcTime getEarliest(CalcTime first, CalcTime second){
        if(first.isBefore(second)){
            return first;
        }
        else
        {
            return second;
        }
    }

    public static CalcTime getLatest(CalcTime first, CalcTime second) {
        if (first.isAfter(second)) {
            return first;
        } else {
            return second;
        }
    }

    public int getHour() {
        return mTime.getHour();
    }

    public int getMinute() {
        return mTime.getMinute();
    }
}
