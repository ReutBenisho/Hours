package com.example.hours.utils;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Student {
    public Timestamp additional125Hours;
    public Timestamp additional150Hours;

    public Student(){
        clear();
    }

    public void clear() {
        if (additional125Hours == null)
            additional125Hours = new Timestamp();
        if (additional150Hours == null)
            additional150Hours = new Timestamp();

        additional125Hours.clear();
        additional150Hours.clear();
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";
        if(additional125Hours != null)
            s += "\nAdditional 125%: " + additional125Hours.toString();
        if(additional150Hours != null)
            s += "\nAdditional 150%: " + additional150Hours.toString();
        return s;
    }
}
