package com.example.hours.calcUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Breaks {
    public ArrayList<Break> preDefinedBreaks;
    public ArrayList<Break> customBreaks;
    public ArrayList<Break> allBreaks;
    public boolean tookEveningBreak;

    @Override
    public boolean equals(@Nullable Object object) {
        Breaks obj = (Breaks) object;
        if(preDefinedBreaks.size() != obj.preDefinedBreaks.size())
            return false;
        for(int i = 9; i< preDefinedBreaks.size(); i++){
            if(!preDefinedBreaks.get(i).equals(obj.preDefinedBreaks.get(i)))
                return false;
        }
        if(customBreaks.size() != obj.customBreaks.size())
            return false;
        for(int i = 9; i< customBreaks.size(); i++){
            if(!customBreaks.get(i).equals(obj.customBreaks.get(i)))
                return false;
        }
        if(allBreaks.size() != obj.allBreaks.size())
            return false;
        for(int i = 9; i< allBreaks.size(); i++){
            if(!allBreaks.get(i).equals(obj.allBreaks.get(i)))
                return false;
        }
        if(tookEveningBreak != tookEveningBreak)
            return false;
        return true;
    }

    public Breaks(){
        clear();
    }

    public void clear() {
        if(preDefinedBreaks == null)
            preDefinedBreaks = new ArrayList<>();
        if(customBreaks == null)
            customBreaks = new ArrayList<>();
        if(allBreaks == null)
            allBreaks = new ArrayList<>();
        preDefinedBreaks.clear();
        customBreaks.clear();
        allBreaks.clear();
        tookEveningBreak = false;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "";

        for(int i = 0; i < preDefinedBreaks.size(); i++){
            s += "\nPre defined break # " + i + " " + preDefinedBreaks.get(i).toString();
        }

        for(int i = 0; i < customBreaks.size(); i++){
            s += "\nCustom break # " + i + " " + customBreaks.get(i).toString();
        }

//        for(int i = 0; i < allBreaks.size(); i++){
//            s += "\nAll breaks #" + i + allBreaks.get(i).toString();
//        }
        s += "\nTook evening break: " + tookEveningBreak;

        return s;
    }
}
