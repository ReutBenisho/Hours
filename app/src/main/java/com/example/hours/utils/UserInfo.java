package com.example.hours.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
            s += "Arrival: " + arrivalTime.toString();
        if(exitTime != null)
            s += "\nExit time: " + exitTime.toString();
        s += "\nFriday: " + isFriday;
        s += "\nStudent: " + isStudent;
        return s;
    }
}
