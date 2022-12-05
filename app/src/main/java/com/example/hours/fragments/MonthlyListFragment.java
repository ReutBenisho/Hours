package com.example.hours.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.adapters.MonthlyDailyReportRecyclerAdapter;
import com.example.hours.db.HoursDbContract.DailyReportEntry;
import com.example.hours.db.HoursOpenHelper;
import com.example.hours.decorators.WeekendDecorator;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class MonthlyListFragment extends Fragment implements IMonthlyFragment, OnUpdateListener {

    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_list);
    private RecyclerView mRecycleMonthlyDailyReports;
    private LinearLayoutManager mMonthlyDailyReportsLayoutManager;
    private MonthlyDailyReportRecyclerAdapter mMonthlyDailyReportRecyclerAdapter;
    private boolean mIsInitialized;
    private Cursor mCursor;

    public static MonthlyListFragment newInstance() {

        return new MonthlyListFragment();
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

        View view = inflater.inflate(R.layout.fragment_monthly_list, container, false);
        mIsInitialized = false;
        initialize(view);

        ListenerManager.addListener(this, ListenerManager.ListenerType.UPDATED_MONTH_CURSOR);
        return view;
    }

    private void initialize(View view) {
        mRecycleMonthlyDailyReports =  view.findViewById(R.id.list_monthly_daily_reports);
        mMonthlyDailyReportsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycleMonthlyDailyReports.setLayoutManager(mMonthlyDailyReportsLayoutManager);

        mMonthlyDailyReportRecyclerAdapter = new MonthlyDailyReportRecyclerAdapter(getContext(), null);
        mRecycleMonthlyDailyReports.setAdapter(mMonthlyDailyReportRecyclerAdapter);

        mMonthlyDailyReportRecyclerAdapter.changeCursor(mCursor);

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
        ListenerManager.removeListener(this, ListenerManager.ListenerType.CHANGED_MONTH);
        super.onDestroy();
    }

    @Override
    public void update(int month, int year, Cursor cursor) {
        // TODO: upadte list by cursor
        mCursor = cursor;
        if(!mIsInitialized)
            return;
        mMonthlyDailyReportRecyclerAdapter.changeCursor(cursor);

    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case UPDATED_MONTH_CURSOR:{
                    mCursor = (Cursor) data.obj;
                    int count = mCursor.getCount();
                    updateList();
                    mMonthlyDailyReportRecyclerAdapter.changeCursor(mCursor);
                    break;
                }
            }
        }
    }

    private void updateList() {

    }
}