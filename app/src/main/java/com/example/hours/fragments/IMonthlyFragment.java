package com.example.hours.fragments;

import com.example.hours.db.DailyReport;

import java.util.ArrayList;

public interface IMonthlyFragment {
    void update(int month, int year, ArrayList<DailyReport> dailyReports);

}
