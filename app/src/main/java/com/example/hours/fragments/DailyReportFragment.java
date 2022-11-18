package com.example.hours.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.hours.R;
import com.example.hours.adapters.DailyReportRecyclerAdapter;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.db.DailyReport;
import com.example.hours.db.DataManager;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.DailyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.example.hours.utils.OnSnapPositionChangeListener;
import com.example.hours.utils.SnapOnScrollListener;

import java.util.List;

public class DailyReportFragment extends Fragment implements OnUpdateListener, OnSnapPositionChangeListener {

    private DailyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_daily_report);

    private HoursManager mHoursManager;
    private ICalcDayFragment mFragment;
    private RecyclerView mRecycleDailyReports;
    private LinearLayoutManager mDailyReportsLayoutManager;
    private DailyReportRecyclerAdapter mDailyReportRecyclerAdapter;
    private SnapHelper mSnapHelper;
    private SnapOnScrollListener mSnapOnScrollListener;
    private List<DailyReport> mDailyReports;
    public static final String DAILY_REPORT_ID = "com.example.hours.DAILY_REPORT_ID";
    private int mDailyReportId;


    public static DailyReportFragment newInstance() {
        return new DailyReportFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListenerManager.addListener(this, ListenerManager.ListenerType.INFO_LABELS);
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

        View view = inflater.inflate(R.layout.fragment_daily_report, container, false);

        mHoursManager = HoursManager.getInstance();
        mRecycleDailyReports =  view.findViewById(R.id.list_daily_reports);
        mDailyReportsLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        mRecycleDailyReports.setLayoutManager(mDailyReportsLayoutManager);
        mDailyReports = DataManager.getInstance().getDailyReports();
        mDailyReportRecyclerAdapter = new DailyReportRecyclerAdapter(getContext(), mDailyReports);
        mRecycleDailyReports.setAdapter(mDailyReportRecyclerAdapter);
        mSnapHelper = new LinearSnapHelper();
        //mSnapHelper.attachToRecyclerView(mRecycleDailyReports);
        mSnapHelper.attachToRecyclerView(mRecycleDailyReports);
        mSnapOnScrollListener = new SnapOnScrollListener(mSnapHelper, this, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE);
        mRecycleDailyReports.addOnScrollListener(mSnapOnScrollListener);

        openDailyReportFragment(false);

        updateHours();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDailyReportRecyclerAdapter.notifyDataSetChanged();
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, R.string.empty);

        updateHours();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DailyReportModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ListenerManager.removeListener(this, ListenerManager.ListenerType.INFO_LABELS);
    }

    private void updateHours() {
        if(mHoursManager == null)
            return;
        mHoursManager.info.clearCalculatedInfo();
        int position = mSnapOnScrollListener.getPosition();
        if(position < 0)
            return;
        mHoursManager.info.userInfo.arrivalTime.setTime(mDailyReports.get(position).getArrival());
        mHoursManager.info.userInfo.exitTime.setTime(mDailyReports.get(position).getExit());
//
//        View snapView_og = mDailyReportsLayoutManager.getChildAt(0);
//        //View snapView = mDailyReports.get(position);
//        if(snapView == null)
//            return;
//        String s1 = ((EditText)snapView.findViewById(R.id.txt_arrival_time)).getText().toString();
//        mHoursManager.info.userInfo.arrivalTime.setTime(s1);
//        String s2 = ((EditText)snapView.findViewById(R.id.txt_exit_time)).getText().toString();
       // mHoursManager.info.userInfo.exitTime.setTime(s2);
        mFragment.update(false);
    }

    private void openDailyReportFragment(boolean isExitTimeAdded) {
        Fragment fragment = null;
        Class fragmentClass = WithExitFragment.class;;
        String tag = WithExitFragment.TAG;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set MyFragment Arguments
        if(fragment != null) {

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_daily_report, fragment, tag)
                    .commit();

            mFragment = (ICalcDayFragment) fragment;
            mFragment.update(false);
        }
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(obj == null || !(obj instanceof ListenerManager.Data))
            return;
        ListenerManager.Data data = (ListenerManager.Data) obj;
        switch (data.type)
        {
            case INFO_LABELS:
                updateHours();
                break;
        }
    }

    @Override
    public int onSnapPositionChange(int position) {
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.INFO_LABELS);
        mDailyReportId = (int) mDailyReportRecyclerAdapter.getItemId(position);
        return 0;
    }
}