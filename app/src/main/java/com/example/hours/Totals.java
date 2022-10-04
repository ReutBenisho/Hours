package com.example.hours;

public class Totals {

    public Timestamp total;
    public boolean isFullDay;
    public Timestamp zeroHours;
    public Timestamp additionalHours;

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
    }

}
