package com.example.hours.fragments;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.hours.R;
import com.example.hours.adapters.DailyReportRecyclerAdapter;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.db.DailyReport;
import com.example.hours.db.HoursOpenHelper;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.DailyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.example.hours.utils.OnSnapPositionChangeListener;
import com.example.hours.utils.SnapOnScrollListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyReportFragment extends Fragment implements OnUpdateListener, OnSnapPositionChangeListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_DAILY_REPORTS = 0;
    private DailyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_daily_report);

    private HoursManager mHoursManager;
    private ICalcDayFragment mFragment;
    private RecyclerView mRecycleDailyReports;
    private LinearLayoutManager mDailyReportsLayoutManager;
    private DailyReportRecyclerAdapter mDailyReportRecyclerAdapter;
    private SnapHelper mSnapHelper;
    private SnapOnScrollListener mSnapOnScrollListener;
    public static final String DAILY_REPORT_ID = "com.example.hours.DAILY_REPORT_ID";
    private HoursOpenHelper mDbOpenHelper;
    private DailyReport mDailyReport;
    private boolean mCreatedLoader;
    private int mDailyReportPos;


    public static DailyReportFragment newInstance() {
        return new DailyReportFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbOpenHelper = new HoursOpenHelper(getContext());
        ListenerManager.addListener(this, ListenerManager.ListenerType.INFO_LABELS);
        ListenerManager.addListener(this, ListenerManager.ListenerType.UPDATE_DAILY_REPORT_IN_DB);
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

        mDailyReportRecyclerAdapter = new DailyReportRecyclerAdapter(getContext(), null);
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
        getActivity().getSupportLoaderManager().restartLoader(LOADER_DAILY_REPORTS, null, this);
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, R.string.empty);

        updateHours();
    }

//    private void loadDailyReports() {
//        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
//        final String[] noteColumns = {
//                DailyReportEntry._ID,
//                DailyReportEntry.COLUMN_DATE,
//                DailyReportEntry.COLUMN_ARRIVAL,
//                DailyReportEntry.COLUMN_EXIT};
//        String noteOrderBy = DailyReportEntry.COLUMN_DATE;
//        final Cursor noteCursor = db.query(DailyReportEntry.TABLE_NAME, noteColumns,
//                null, null, null, null, noteOrderBy);
//        mDailyReportRecyclerAdapter.changeCursor(noteCursor);
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DailyReportModel.class);
    }

    @Override
    public void onDestroy() {
        ListenerManager.removeListener(this, ListenerManager.ListenerType.INFO_LABELS);
        ListenerManager.removeListener(this, ListenerManager.ListenerType.UPDATE_DAILY_REPORT_IN_DB);
        super.onDestroy();
    }

    private void updateHours() {
        if(mHoursManager == null)
            return;
        mHoursManager.mInfo.clearCalculatedInfo();
        int position = mSnapOnScrollListener.getPosition();
        if(position < 0)
            return;

        mHoursManager.mInfo.userInfo.arrivalTime.setTime(mDailyReport.getArrival());
        mHoursManager.mInfo.userInfo.exitTime.setTime(mDailyReport.getExit());
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
                mDailyReport = getCurrentDailyReport(mDailyReportPos);
                if(mDailyReport != null)
                    updateHours();
                break;
            case UPDATE_DAILY_REPORT_IN_DB:
                mDailyReport = getCurrentDailyReport(mDailyReportPos);
                if(mDailyReport != null)
                    saveDailyReportToDatabase();
                break;
        }
    }

    private void saveDailyReportToDatabase() {
        final ContentValues values = new ContentValues();
        values.put(HoursProviderContract.DailyReports.COLUMN_ARRIVAL, mDailyReport.getArrival().toString());
        values.put(HoursProviderContract.DailyReports.COLUMN_EXIT, mDailyReport.getExit().toString());

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Uri uri = ContentUris.withAppendedId(HoursProviderContract.DailyReports.CONTENT_URI, mDailyReport.getId());
                getContext().getContentResolver().update(uri, values, null, null);
                return null;
            }
        };
        task.execute();
    }

    @Override
    public int onSnapPositionChange(int position) {
        mDailyReportPos = position;
        mDailyReport = getCurrentDailyReport(position);
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.INFO_LABELS);
        return 0;
    }

    private DailyReport getCurrentDailyReport(int position) {
        DailyReportRecyclerAdapter.ViewHolder holder =
                (DailyReportRecyclerAdapter.ViewHolder) mRecycleDailyReports.findViewHolderForAdapterPosition(position);
        if(holder == null)
            return null;
        int id = holder.mId;
        Date date;
        try {
            date = (new SimpleDateFormat("yyyyMMdd")).parse(holder.mLblDate.getText().toString());
        }
        catch (ParseException ex){
            date = new Date(2022 - 1900, 1, 1);
        }
        Timestamp arrival = new Timestamp(holder.mTxtArrival.getText().toString());
        Timestamp exit = new Timestamp(holder.mTxtExit.getText().toString());
        return new DailyReport(id, date, arrival, exit);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader loader = null;
        if(id == LOADER_DAILY_REPORTS){
            final String[] columns = {
                    HoursProviderContract.DailyReports._ID,
                    HoursProviderContract.DailyReports.COLUMN_DATE,
                    HoursProviderContract.DailyReports.COLUMN_ARRIVAL,
                    HoursProviderContract.DailyReports.COLUMN_EXIT,};
            String orderBy = HoursProviderContract.DailyReports.COLUMN_DATE;

            loader = new CursorLoader(getContext(), HoursProviderContract.DailyReports.CONTENT_URI,
                    columns, null, null, orderBy);
        }
        mCreatedLoader = true;
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (!mCreatedLoader)
            return;
        mCreatedLoader = false;
        if (loader.getId() == LOADER_DAILY_REPORTS) {
            mDailyReportRecyclerAdapter.changeCursor(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if(loader.getId() == LOADER_DAILY_REPORTS){
            mDailyReportRecyclerAdapter.changeCursor(null);
        }
    }
}