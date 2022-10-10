package com.example.hours.calcUtils;

import androidx.annotation.NonNull;

import com.example.hours.R;
import com.example.hours.utils.App;

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
            s += App.getStr(R.string.newline_additional125_colon_space) + additional125Hours.toString();
        if(additional150Hours != null)
            s += App.getStr(R.string.newline_additional150_colon_space) + additional150Hours.toString();
        return s;
    }
}
