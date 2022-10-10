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
            s += App.getStr(R.string.newline_halfDay_colon_space) + halfDay.toString();
        if(fullDay != null)
            s += App.getStr(R.string.newline_fullDay_colon_space) + fullDay.toString();
        if(zeroHours != null)
            s += App.getStr(R.string.newline_zeroHours_colon_space) + zeroHours.toString();
        if(additional3AndHalfHours != null)
            s += App.getStr(R.string.newline_additional3andhalfHours_colon_space) + additional3AndHalfHours.toString();
        if(additional6Hours != null)
            s += App.getStr(R.string.newline_additional6hours_colon_space) + additional6Hours.toString();
        s += totalTime.toString();
        s += student.toString();
        return s;
    }
}
