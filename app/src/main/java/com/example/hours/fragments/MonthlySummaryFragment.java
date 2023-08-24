package com.example.hours.fragments;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hours.R;
import com.example.hours.calcUtils.CalcInfo;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.calcUtils.Totals;
import com.example.hours.calcUtils.UserInfo;
import com.example.hours.db.DailyReport;
import com.example.hours.db.HoursDbContract;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.report.ReportManager;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.example.hours.utils.Utils;

import java.util.ArrayList;

public class MonthlySummaryFragment extends Fragment implements IMonthlyFragment, OnUpdateListener {

    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_summary);
    private boolean mIsInitialized = false;
    private HoursManager mHoursManager;
    private TextView mLblTxtRegularHours;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxtAdditionalHours;
    private TextView mLblTxtAdditional125Hours;
    private TextView mLblTxtAdditional150Hours;
    private TextView mLblTxtGlobalAbsenceHours;
    private TextView mLblTxtUnpaidAbsenceHours;
    private View mView;
    private ArrayList<DailyReport> mDailyReports;

    public static MonthlySummaryFragment newInstance() {

        return new MonthlySummaryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void initialize() {
        mLblTxtRegularHours = mView.findViewById(R.id.lbl_txt_regular_hours);
        mLblTxtZeroHours = mView.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxtZeroHours.setTextColor(getResources().getColor(R.color.yellow));
        mLblTxtAdditionalHours = mView.findViewById(R.id.lbl_txt_additional_hours);
        mLblTxtAdditionalHours.setTextColor(getResources().getColor(R.color.green));
        mLblTxtAdditional125Hours = mView.findViewById(R.id.lbl_txt_additional_125_hours);
        mLblTxtAdditional125Hours.setTextColor(getResources().getColor(R.color.green));
        mLblTxtAdditional150Hours = mView.findViewById(R.id.lbl_txt_additional_150_hours);
        mLblTxtAdditional150Hours.setTextColor(getResources().getColor(R.color.green));
        mLblTxtGlobalAbsenceHours = mView.findViewById(R.id.lbl_txt_global_absence_hours);
        mLblTxtGlobalAbsenceHours.setTextColor(getResources().getColor(R.color.red));
        mLblTxtUnpaidAbsenceHours = mView.findViewById(R.id.lbl_txt_unpaid_absence_hours);
        mLblTxtUnpaidAbsenceHours.setTextColor(getResources().getColor(R.color.red));

        mHoursManager = HoursManager.getInstance();

        mIsInitialized = true;
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

        mView = inflater.inflate(R.layout.fragment_monthly_summary, container, false);

        initialize();
        ListenerManager.addListener(this, ListenerManager.ListenerType.UPDATED_MONTH_CURSOR);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        calcSummaryInBackground();
    }

    private void calcSummaryInBackground() {
        if(!mIsInitialized)
            return;
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                ReportManager.updateTotals(mDailyReports);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                updateGUI();
            }
        };
        task.execute();
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

    private void updateGUI() {
        if(!mIsInitialized)
            return;
        updateLabels();
        updateVisibility();
    }


    private void updateLabels() {
        mLblTxtRegularHours.setText(ReportManager.getRegularHours());
        mLblTxtZeroHours.setText(ReportManager.getZeroHours());
        mLblTxtAdditionalHours.setText(ReportManager.getAdditionalHours());
        mLblTxtAdditional125Hours.setText(ReportManager.getAdditional125Hours());
        mLblTxtAdditional150Hours.setText(ReportManager.getAdditional150Hours());
        mLblTxtGlobalAbsenceHours.setText(ReportManager.getGlobalAbsence());
        mLblTxtUnpaidAbsenceHours.setText(ReportManager.getUnpaidAbsence());
        //TODO: add labels for remaining time of zero / additional / absence
    }

    private void updateVisibility() {
        Utils.updateViewVisibility(mLblTxtRegularHours);
        Utils.updateViewVisibility(mLblTxtZeroHours);
        Utils.updateViewVisibility(mLblTxtAdditionalHours);
        Utils.updateViewVisibility(mLblTxtAdditional125Hours);
        Utils.updateViewVisibility(mLblTxtAdditional150Hours);
        Utils.updateViewVisibility(mLblTxtGlobalAbsenceHours);
        Utils.updateViewVisibility(mLblTxtUnpaidAbsenceHours);
    }

    @Override
    public void update() {
        calcSummaryInBackground();
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case UPDATED_MONTH_CURSOR:{
                    mDailyReports = (ArrayList<DailyReport>) data.obj;
                    calcSummaryInBackground();
                    break;
                }
            }
        }
    }
}