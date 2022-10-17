package com.example.hours.calcUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionHelper;

import com.example.hours.R;
import com.example.hours.utils.App;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Timestamp {
    private Duration mTime;

    public Timestamp(){

        mTime = Duration.of(0, ChronoUnit.MINUTES);
    }

    public Timestamp(String str) {
        setTime(str);
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

    public static boolean isValid(String str) {
        return str.matches(App.getStr(R.string.regex_24hour_timestamp_format));
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(!(obj instanceof Timestamp))
            return false;
        return mTime.equals(((Timestamp) obj).mTime);
    }

    public Timestamp(int hour){

        mTime = Duration.of(hour* 60, ChronoUnit.MINUTES);
    }

    public Timestamp(int hour, int minute){
        mTime = Duration.of(hour* 60 + minute, ChronoUnit.MINUTES);
    }

    public Timestamp(Duration other){

        mTime = Duration.of(other.toMinutes(), ChronoUnit.MINUTES);
    }

    public Timestamp(Timestamp other){
        mTime = Duration.of(other.mTime.toMinutes(), ChronoUnit.MINUTES);
    }

    public boolean isBefore(Timestamp before){

        return mTime.compareTo(before.mTime) < 0;
    }

    public boolean isAfter(Timestamp after){

        return mTime.compareTo(after.mTime) > 0;
    }

    public boolean isBetween(Timestamp first, Timestamp second){
        return mTime.compareTo(first.mTime) >= 0 && mTime.compareTo(second.mTime) <= 0;
    }

    public Timestamp add(int hour, int minute){
        Timestamp newTime = new Timestamp(mTime);
        newTime.mTime = newTime.mTime.plusHours(hour);
        newTime.mTime = newTime.mTime.plusMinutes(minute);
        return newTime;
    }

    public Timestamp add(Timestamp timeToAdd){
        return new Timestamp(mTime.plusMinutes(timeToAdd.mTime.toMinutes()));
    }

    public Timestamp sub(int hour, int minute){
        return new Timestamp(mTime.minusMinutes(hour * 60 + minute));
    }

    public Timestamp sub(Timestamp timeToRemove){
        return new Timestamp(mTime.minusMinutes(timeToRemove.mTime.toMinutes()));
    }

    @NonNull
    public String toString(){
        return String.format(Locale.getDefault(), "%02d:%02d", mTime.toHours(), mTime.toMinutes() - mTime.toHours() * 60);
    }

    public void setTime(String str){
        int hours = Integer.parseInt(str.substring(0, 2));
        int minutes = Integer.parseInt(str.substring(3, 5));
        mTime = Duration.of(hours * 60 + minutes, ChronoUnit.MINUTES);
    }

    public void setTime(int hour, int minute){
        mTime = Duration.of(hour * 60 + minute, ChronoUnit.MINUTES);
    }
    public void setTime(Timestamp other){

        mTime = Duration.of(other.mTime.toMinutes(), ChronoUnit.MINUTES);
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

    public long getHour() {

        return mTime.toHours();
    }

    public long getMinute() {

        return mTime.toMinutes() - mTime.toHours()* 60;
    }

    public void clear() {
        mTime = Duration.of(0, ChronoUnit.MINUTES);
    }

    public boolean equalsOrGreaterThan(Timestamp other) {
        return equals(other) || isAfter(other);
    }

    public boolean lessThan(Timestamp other) {
        return isBefore(other);
    }

    public Timestamp copy(){
        return new Timestamp(this);
    }

}
