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

        s += App.getStr(R.string.newline_totalTime_colon_space) + total.toString();
        s += App.getStr(R.string.newline_totalZeroHours_colon_space) + zeroHours.toString();
        s += App.getStr(R.string.newline_totalAdditionalHours_colon_space) + additionalHours.toString();
        s += App.getStr(R.string.newline_totalAdditional125hours_colon_space) + additional125Hours.toString();
        s += App.getStr(R.string.newline_totalAddtional150hours_colon_space) + additional150Hours.toString();
        s += App.getStr(R.string.newline_isFullDay_colon_space) + isFullDay;
        s += App.getStr(R.string.newline_totalGlobalAbsence_colon_space) + globalAbsence.toString();
        s += App.getStr(R.string.newline_totalUnpaidAbsence_colon_space) + unpaidAbsence.toString();

        return s;
    }
}
