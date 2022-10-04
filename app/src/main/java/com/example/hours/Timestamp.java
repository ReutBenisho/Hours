package com.example.hours;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalTime;
import java.util.Locale;

public class Timestamp {
    private LocalTime mTime;

    public Timestamp(){
        mTime = LocalTime.of(0, 0);
    }

    public static Timestamp removeOverlap(Timestamp startRange1, Timestamp endRange1, Timestamp startRange2, Timestamp endRange2, Timestamp duration) {
        if(isOverlap(startRange1, endRange1, startRange2, endRange2)){
            Timestamp startOverlap = Timestamp.getLatest(startRange1, startRange2);
            Timestamp endOverlap = Timestamp.getEarliest(endRange1, endRange2);
            Timestamp totalOverlap = endOverlap.sub(startOverlap);
            duration = duration.sub(totalOverlap);
        }
        return duration;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Timestamp))
            return false;
        return mTime.equals(((Timestamp) obj).mTime);
    }

    public Timestamp(int hour){
        mTime = LocalTime.of(hour, 0);
    }

    public Timestamp(int hour, int minute){
        mTime = LocalTime.of(hour, minute);
    }

    public Timestamp(LocalTime other){
        mTime = LocalTime.of(other.getHour(), other.getMinute());
    }

    public Timestamp(Timestamp other){
        mTime = LocalTime.of(other.mTime.getHour(), other.mTime.getMinute());
    }

    public LocalTime toLocalTime(){
        return mTime;
    }

    public boolean isBefore(Timestamp before){
        return mTime.isBefore(before.mTime);
    }

    public boolean isAfter(Timestamp after){
        return mTime.isAfter(after.mTime);
    }

    public boolean isBetween(Timestamp first, Timestamp second){
        return mTime.isAfter(first.mTime) && mTime.isBefore(second.mTime);
    }

    public Timestamp add(int hour, int minute){
        Timestamp newTime = new Timestamp(mTime);
        newTime.mTime = newTime.mTime.plusHours(hour);
        newTime.mTime = newTime.mTime.plusMinutes(minute);
        return newTime;
    }

    public Timestamp add(Timestamp timeToAdd){
        return new Timestamp(mTime).add(timeToAdd.mTime.getHour(), timeToAdd.mTime.getMinute());
    }

    public Timestamp sub(int hour, int minute){
        Timestamp newTime = new Timestamp(mTime);
        newTime.mTime = newTime.mTime.minusHours(hour);
        newTime.mTime = newTime.mTime.minusMinutes(minute);
        return newTime;
    }

    public Timestamp sub(Timestamp timeToRemove){
        return new Timestamp(mTime).sub(timeToRemove.mTime.getHour(), timeToRemove.mTime.getMinute());
    }

    @NonNull
    public String toString(){
        return String.format(Locale.getDefault(), "%02d:%02d", mTime.getHour(), mTime.getMinute());
    }

    public void setTime(String str){
        mTime = mTime.withHour(Integer.parseInt(str.substring(0, 2)));
        mTime = mTime.withMinute(Integer.parseInt(str.substring(3, 5)));
    }

    public void setTime(int hour, int minute){
        mTime = mTime.withHour(hour);
        mTime = mTime.withMinute(minute);
    }
    public void setTime(Timestamp other)
    {
        mTime = mTime.withHour(other.getHour());
        mTime = mTime.withMinute(other.getMinute());
    }

    public static boolean isOverlap(Timestamp startRange1, Timestamp endRange1,
                                    Timestamp startRange2, Timestamp endRange2){
        return !(endRange1.isBefore(startRange2) || endRange2.isBefore(startRange1));
    }

    public static Timestamp addOverlap(Timestamp startRange1, Timestamp endRange1,
                                       Timestamp startRange2, Timestamp endRange2){
        Timestamp newTime = new Timestamp(endRange1);
        if(isOverlap(startRange1, endRange1, startRange2, endRange2)){
            Timestamp startOverlap = Timestamp.getLatest(startRange1, startRange2);
            Timestamp endOverlap = Timestamp.getEarliest(endRange1, endRange2);
            Timestamp totalOverlap = endOverlap.sub(startOverlap);
            newTime.setTime(Timestamp.getLatest(endRange1, endRange2).add(totalOverlap));
        }
        return newTime;
    }

    public static Timestamp toCalcTime(LocalTime timeToConvert){
        return new Timestamp(timeToConvert);
    }

    public static LocalTime toLocalTime(Timestamp timeToConvert){
        return timeToConvert.mTime;
    }

    public static Timestamp getEarliest(Timestamp first, Timestamp second){
        if(first.isBefore(second)){
            return first;
        }
        else
        {
            return second;
        }
    }

    public static Timestamp getLatest(Timestamp first, Timestamp second) {
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

    public void clear() {
        mTime = mTime.withHour(0);
        mTime = mTime.withMinute(0);
    }

    public boolean equalsOrGreaterThan(Timestamp other) {
        return equals(other) || isAfter(other);
    }
}
