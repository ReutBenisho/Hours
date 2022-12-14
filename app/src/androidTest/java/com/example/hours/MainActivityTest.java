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

import com.example.hours.activities.MainActivity;
import com.example.hours.calcUtils.HoursInfo;
import com.example.hours.calcUtils.Timestamp;

import org.junit.Rule;
import org.junit.Test;


public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testCalculationByArrival(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.RIGHT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_calc_day));

        HoursInfo expHoursInfo = new HoursInfo();
        expHoursInfo.calcInfo.halfDay = new Timestamp(11, 42);
        expHoursInfo.calcInfo.fullDay = new Timestamp(16, 24);
        expHoursInfo.calcInfo.zeroHours = new Timestamp(17, 24);
        expHoursInfo.calcInfo.additional3AndHalfHours = new Timestamp(20, 6);
        expHoursInfo.calcInfo.additional6Hours = new Timestamp(22, 36);

        onView(withId(R.id.lbl_txt_half_day)).check(matches(withText(expHoursInfo.calcInfo.halfDay.toString())));
        onView(withId(R.id.lbl_txt_full_day)).check(matches(withText(expHoursInfo.calcInfo.fullDay.toString())));
        onView(withId(R.id.lbl_txt_zero_hours)).check(matches(withText(expHoursInfo.calcInfo.zeroHours.toString())));
        onView(withId(R.id.lbl_txt_3_and_half_hours)).check(matches(withText(expHoursInfo.calcInfo.additional3AndHalfHours.toString())));
        onView(withId(R.id.lbl_txt_6_hours)).check(matches(withText(expHoursInfo.calcInfo.additional6Hours.toString())));

    }

}