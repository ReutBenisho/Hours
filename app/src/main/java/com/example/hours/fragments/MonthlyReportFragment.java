package com.example.hours.fragments;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.hours.R;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.db.DailyReport;
import com.example.hours.db.HoursOpenHelper;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.report.ReportManager;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MonthlyReportFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, OnUpdateListener {

    private static final int LOADER_MONTHLY_DAILY_REPORTS = 0;
    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_report);
    private TextView mLblCurrentMonth;
    private ImageView mImagePreviousMonth;
    private ImageView mImageNextMonth;
    private IMonthlyFragment mFragment;
    private boolean mCreatedLoader;

    public static MonthlyReportFragment newInstance() {

        return new MonthlyReportFragment();
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

        View view = inflater.inflate(R.layout.fragment_monthly_report, container, false);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                    openCalcDayFragment(compoundButton.getId());
                }
            }
        };
        AppCompatRadioButton rdBtnSummary = view.findViewById(R.id.rdBtn_monthly_summary);
        rdBtnSummary.setOnCheckedChangeListener(listener);
        AppCompatRadioButton rdBtnView = view.findViewById(R.id.rdBtn_monthly_view);
        rdBtnView.setOnCheckedChangeListener(listener);
        AppCompatRadioButton rdBtnList = view.findViewById(R.id.rdBtn_monthly_list);
        rdBtnList.setOnCheckedChangeListener(listener);

        mLblCurrentMonth = view.findViewById(R.id.lbl_current_month);
        mImagePreviousMonth = view.findViewById(R.id.img_month_previous);
        mImagePreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.image_click));
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                ReportManager.decreaseMonth();
                mLblCurrentMonth.setText(ReportManager.getCurrentMonthText());
                getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, MonthlyReportFragment.this);
            }
        });
        mImageNextMonth = view.findViewById(R.id.img_month_next);
        mImageNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.image_click));
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                ReportManager.increaseMonth();
                mLblCurrentMonth.setText(ReportManager.getCurrentMonthText());
                getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, MonthlyReportFragment.this);
            }
        });
        rdBtnSummary.setChecked(true);
        mLblCurrentMonth.setText(ReportManager.getCurrentMonthText());
        ListenerManager.addListener(this, ListenerManager.ListenerType.CHANGED_MONTH_VIA_MONTH_VIEW);
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        ReportManager.setCurrentMonth();
        getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, this);
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


    private void openCalcDayFragment(int id) {
        Fragment fragment = null;
        Class fragmentClass;
        String tag;
        if(id == R.id.rdBtn_monthly_summary){
            fragmentClass = MonthlySummaryFragment.class;
            tag = MonthlySummaryFragment.TAG;
        } else if(id == R.id.rdBtn_monthly_view){
            fragmentClass = MonthlyViewFragment.class;
            tag = MonthlyViewFragment.TAG;
        } else {
            fragmentClass = MonthlyListFragment.class;
            tag = MonthlyListFragment.TAG;
        }

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
                    .replace(R.id.nav_host_fragment_monthly_report, fragment, tag)
                    .commit();

            mFragment = (IMonthlyFragment) fragment;
            mFragment.update();
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader loader = null;
        if(id == LOADER_MONTHLY_DAILY_REPORTS){
            loader = ReportManager.getLoader(getContext());
        }
        mCreatedLoader = true;
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (!mCreatedLoader)
            return;
        mCreatedLoader = false;
        if (loader.getId() == LOADER_MONTHLY_DAILY_REPORTS) {
            ReportManager.updateTotals(cursor);
            ListenerManager.NotifyListeners(ListenerManager.ListenerType.UPDATED_MONTH_CURSOR);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if(loader.getId() == LOADER_MONTHLY_DAILY_REPORTS){
            ReportManager.clearReports();
            ListenerManager.NotifyListeners(ListenerManager.ListenerType.UPDATED_MONTH_CURSOR, null);
        }
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case CHANGED_MONTH_VIA_MONTH_VIEW:{
                    mLblCurrentMonth.setText(ReportManager.getCurrentMonthText());
                    getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, this);
                    break;
                }
            }
        }
    }
}