package com.example.hours.calcUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hours.R;
import com.example.hours.utils.App;

public class UserInfo {
    public Timestamp arrivalTime;
    public Timestamp exitTime;
    public boolean isFriday;
    public boolean isStudent;

    public UserInfo(){
        clear();
    }

    @Override
    public boolean equals(@Nullable Object object) {
        UserInfo obj = (UserInfo) object;
        if(!arrivalTime.equals(obj.arrivalTime))
            return false;
        if(!exitTime.equals(obj.exitTime))
            return false;
        if(isFriday != obj.isFriday)
            return false;
        if(isStudent != obj.isStudent)
            return false;
        return true;
    }

    public void clear() {
        if(arrivalTime == null)
            arrivalTime = new Timestamp();
        if(exitTime == null)
            exitTime = new Timestamp();
        arrivalTime.clear();
        exitTime.clear();
        isFriday = false;
        isStudent = false;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";
        if(arrivalTime != null)
            s += App.getStr(R.string.arrival_colon_space) + arrivalTime.toString();
        if(exitTime != null)
            s += App.getStr(R.string.newline_exitTime_colon_space) + exitTime.toString();
        s += App.getStr(R.string.newline_friday_colon_space) + isFriday;
        s += App.getStr(R.string.newline_student_colon_space) + isStudent;
        return s;
    }
}
