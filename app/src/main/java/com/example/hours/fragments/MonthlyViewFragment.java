package com.example.hours.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hours.R;
import com.example.hours.decorators.WeekendDecorator;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.report.ReportManager;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.ArrayList;

public class MonthlyViewFragment extends Fragment implements IMonthlyFragment, OnUpdateListener {

    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_view);
    private MaterialCalendarView mCalendarView;
    private boolean mIsInitialized;

    public static MonthlyViewFragment newInstance() {

        return new MonthlyViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_monthly_view, container, false);
        mIsInitialized = false;
        initialize(view);
        ListenerManager.addListener(this, ListenerManager.ListenerType.UPDATED_MONTH_CURSOR);
        return view;
    }

    private void initialize(View view) {
        mCalendarView = view.findViewById(R.id.calendarView);
        mCalendarView.addDecorator(new WeekendDecorator());
        mCalendarView.setHeaderTextAppearance(R.style.CustomTextAppearance);
        mCalendarView.setWeekDayTextAppearance(R.style.CustomTextAppearance);
        mCalendarView.setDateTextAppearance(R.style.CustomTextAppearance);
//        mCalendarView.setLeftArrow(R.drawable.ic_left_arrow);
//        mCalendarView.setRightArrow(R.drawable.ic_right_arrow);
        mCalendarView.setSelectionColor(App.getRes().getColor(R.color.calendar_cell_selected));
        mCalendarView.setTopbarVisible(false);
        mCalendarView.setPagingEnabled(false);
        mCalendarView.setAllowClickDaysOutsideCurrentMonth(false);
        mCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                ListenerManager.NotifyListeners(
                        ListenerManager.ListenerType.CHANGED_MONTH_VIA_MONTH_VIEW,
                        date.getMonth() + "," + date.getYear());
            }
        });
        mIsInitialized = true;
    }

    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MonthlyReportModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void update() {
        //TODO: update stuff by cursor
        if(!mIsInitialized)
            return;
        CalendarDay day = CalendarDay.from(ReportManager.getYear(), ReportManager.getMonth(), 1);
        mCalendarView.setSelectedDate(day);
        updateMonthData();
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case UPDATED_MONTH_CURSOR:{
                    updateMonthData();
                    break;
                }
            }
        }
    }

    private void updateMonthData() {
        //TODO: implement function to show a colored dot in every day of the month according the report
    }

}