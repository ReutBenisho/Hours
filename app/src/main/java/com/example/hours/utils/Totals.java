package com.example.hours.utils;

import androidx.annotation.NonNull;

public class Totals {

    public Timestamp total;
    public boolean isFullDay;
    public Timestamp zeroHours;
    public Timestamp additionalHours;
    public Timestamp globalAbsence;
    public Timestamp unpaidAbsence;

    public Totals(){
        clear();
    }
    public void clear() {
        if(total == null)
            total = new Timestamp();
        if(zeroHours == null)
            zeroHours = new Timestamp();
        if(additionalHours == null)
            additionalHours = new Timestamp();
        if(globalAbsence == null)
            globalAbsence = new Timestamp();
        if(unpaidAbsence == null)
            unpaidAbsence = new Timestamp();
        total.clear();
        isFullDay = false;
        zeroHours.clear();
        additionalHours.clear();
        globalAbsence.clear();
        unpaidAbsence.clear();
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";

        s += "\nTotal time: " + total.toString();
        s += "\nZero hours: " + zeroHours.toString();
        s += "\nAdditional hours: " + additionalHours.toString();
        s += "\nIs full day: " + isFullDay;
        s += "\nGlobal absence: " + globalAbsence.toString();
        s += "\nUnpaid absence: " + unpaidAbsence.toString();

        return s;
    }
}
