package com.example.hours.decorators;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

public class WeekendDecorator implements DayViewDecorator {
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        LocalDate date = LocalDate.of(day.getYear(), day.getMonth(), day.getDay());
        return date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }
}
