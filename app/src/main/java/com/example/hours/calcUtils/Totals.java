package com.example.hours.calcUtils;

import androidx.annotation.NonNull;

import com.example.hours.R;
import com.example.hours.utils.App;

public class Totals {

    public Timestamp total;
    public boolean isFullDay;
    public Timestamp zeroHours;
    public Timestamp additionalHours;
    public Timestamp additional125Hours;
    public Timestamp additional150Hours;
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
        if(additional125Hours == null)
            additional125Hours = new Timestamp();
        if(additional150Hours == null)
            additional150Hours = new Timestamp();
        if(globalAbsence == null)
            globalAbsence = new Timestamp();
        if(unpaidAbsence == null)
            unpaidAbsence = new Timestamp();
        total.clear();
        isFullDay = false;
        zeroHours.clear();
        additionalHours.clear();
        additional125Hours.clear();
        additional150Hours.clear();
        globalAbsence.clear();
        unpaidAbsence.clear();
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";

        s += "\nTotal time: " + total.toString();
        s += "\nTotal zero hours: " + zeroHours.toString();
        s += "\nTotal additional hours: " + additionalHours.toString();
        s += "\nTotal additional 125% hours: " + additional125Hours.toString();
        s += "\nTotal additional 150% hours: " + additional150Hours.toString();
        s += "\nIs full day: " + isFullDay;
        s += "\nTotal global absence hours: " + globalAbsence.toString();
        s += "\nTotal unpaid absence hours: " + unpaidAbsence.toString();

        return s;
    }
}
