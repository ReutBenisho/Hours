package com.example.hours.utils;

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
        total.clear();
        isFullDay = false;
        if(zeroHours == null)
            zeroHours = new Timestamp();
        zeroHours.clear();
        if(additionalHours == null)
            additionalHours = new Timestamp();
        additionalHours.clear();
        if(globalAbsence == null)
            globalAbsence = new Timestamp();
        globalAbsence.clear();
        if(unpaidAbsence == null)
            unpaidAbsence = new Timestamp();
        unpaidAbsence.clear();
    }

}
