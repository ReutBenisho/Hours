package com.example.hours.calcUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hours.R;
import com.example.hours.utils.App;

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
        if(object == null || !(object instanceof CalcInfo))
            return false;
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
            s += "Hald day: " + halfDay.toString();
        if(fullDay != null)
            s += "Full day: " + fullDay.toString();
        if(zeroHours != null)
            s += "Zero hours: " + zeroHours.toString();
        if(additional3AndHalfHours != null)
            s += "Additional 3.5: " + additional3AndHalfHours.toString();
        if(additional6Hours != null)
            s += "Additional 6: " + additional6Hours.toString();
        s += totalTime.toString();
        s += student.toString();
        return s;
    }
}
