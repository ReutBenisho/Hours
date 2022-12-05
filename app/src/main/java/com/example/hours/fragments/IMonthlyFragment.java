package com.example.hours.fragments;

import android.database.Cursor;

public interface IMonthlyFragment {
    void update(int month, int year, Cursor cursor);

}
