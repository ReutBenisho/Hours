package com.example.hours;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCalculationByArrival(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_calc_by_arrival));

        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.mHalfDay = new Timestamp(11, 42);
        expHoursInfo.mFullDay = new Timestamp(16, 24);
        expHoursInfo.mZeroHours = new Timestamp(17, 24);
        expHoursInfo.m3AndHalfHours = new Timestamp(20, 6);
        expHoursInfo.m6Hours = new Timestamp(22, 36);

        onView(withId(R.id.lbl_txt_half_day)).check(matches(withText(expHoursInfo.mHalfDay.toString())));
        onView(withId(R.id.lbl_txt_full_day)).check(matches(withText(expHoursInfo.mFullDay.toString())));
        onView(withId(R.id.lbl_txt_zero_hours)).check(matches(withText(expHoursInfo.mZeroHours.toString())));
        onView(withId(R.id.lbl_txt_3_and_half_hours)).check(matches(withText(expHoursInfo.m3AndHalfHours.toString())));
        onView(withId(R.id.lbl_txt_6_hours)).check(matches(withText(expHoursInfo.m6Hours.toString())));

    }

}