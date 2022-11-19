package com.example.hours.calcUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CustomDate {
    private Date mDate;

    CustomDate(String date, String format){
        try {
            mDate = (new SimpleDateFormat(format)).parse(date);
        }
        catch (ParseException ex){
            mDate = new Date(2022 - 1900, 1, 1);
        }
    }

    public String convertToFormat(String format){
        return new SimpleDateFormat(format).format(mDate);
    }

    public static String convertToFormat(String date, String oldFormat, String newFormat){
        return new CustomDate(date, oldFormat).convertToFormat(newFormat);
    }
}
