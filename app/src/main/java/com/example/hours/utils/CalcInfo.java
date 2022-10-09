package com.example.hours.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CalcInfo {
    public Timestamp halfDay;
    public Timestamp fullDay;
    public Timestamp zeroHours;
    public Timestamp additional3AndHalfHours;
    public Timestamp additional6Hours;
    public Totals totalTime;
    public Student student;

    public CalcInfo(){
        clear();
    }

    public void clear() {
        if(halfDay == null)
            halfDay = new Timestamp();
        if(fullDay == null)
            fullDay = new Timestamp();
        if(zeroHours == null)
            zeroHours = new Timestamp();
        if(additional3AndHalfHours == null)
            additional3AndHalfHours = new Timestamp();
        if(additional6Hours == null)
            additional6Hours = new Timestamp();
        if(student == null)
            student = new Student();
        if(totalTime == null)
            totalTime = new Totals();

        student.clear();
        totalTime.clear();
        halfDay.clear();
        fullDay.clear();
        zeroHours.clear();
        additional6Hours.clear();
        additional3AndHalfHours.clear();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        CalcInfo obj = (CalcInfo) object;
        if(!totalTime.equals(obj.totalTime))
            return false;
        if(!halfDay.equals(obj.halfDay))
            return false;
        if(!fullDay.equals(obj.fullDay))
            return false;
        if(!zeroHours.equals(obj.zeroHours))
            return false;
        if(!additional3AndHalfHours.equals(obj.additional3AndHalfHours))
            return false;
        if(!additional6Hours.equals(obj.additional6Hours))
            return false;
        if(!student.equals(obj.student))
            return false;
        return true;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";

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
        s += totalTime.toString();
        s += student.toString();
        return s;
    }
}
