package com.example.hours.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.adapters.MonthlyDailyReportRecyclerAdapter;
import com.example.hours.db.DailyReport;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.report.ReportManager;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;

import java.util.ArrayList;

public class MonthlyListFragment extends Fragment implements IMonthlyFragment, OnUpdateListener {

    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_list);
    private RecyclerView mRecycleMonthlyDailyReports;
    private LinearLayoutManager mMonthlyDailyReportsLayoutManager;
    private MonthlyDailyReportRecyclerAdapter mMonthlyDailyReportRecyclerAdapter;
    private boolean mIsInitialized;

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

        mMonthlyDailyReportRecyclerAdapter = new MonthlyDailyReportRecyclerAdapter(getContext(), ReportManager.getReports());
        mRecycleMonthlyDailyReports.setAdapter(mMonthlyDailyReportRecyclerAdapter);

        mIsInitialized = true;
    }

    @Override
    public void onResume() {

        super.onResume();
        mMonthlyDailyReportRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MonthlyReportModel.class);
    }

    @Override
    public void onDestroy() {
        ListenerManager.removeListener(this, ListenerManager.ListenerType.UPDATED_MONTH_CURSOR);
        super.onDestroy();
    }

    @Override
    public void update() {
        if(!mIsInitialized)
            return;
        mMonthlyDailyReportRecyclerAdapter.changeList(ReportManager.getReports());
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case UPDATED_MONTH_CURSOR:{
                    mMonthlyDailyReportRecyclerAdapter.changeList(ReportManager.getReports());
                    break;
                }
            }
        }
    }

}