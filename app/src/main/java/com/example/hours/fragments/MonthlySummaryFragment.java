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
    private Totals mMonthTotals;

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

        mMonthTotals = new Totals();
        mMonthTotals.regularHours = new Timestamp(186, 20);
        mMonthTotals.zeroHours = new Timestamp(2, 13);
        mMonthTotals.additionalHours = new Timestamp(52, 14);
        mMonthTotals.additional125Hours = new Timestamp(10, 42);
        mMonthTotals.unpaidAbsence = new Timestamp(4, 13);

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
                mMonthTotals.clear();
                if(mDailyReports != null)
                {
                    for (DailyReport report: mDailyReports) {
                        UserInfo userInfo = new UserInfo();
                        userInfo.arrivalTime = report.getArrival();
                        userInfo.exitTime = report.getExit();
                        CalcInfo calcInfo = mHoursManager.CalcDayWithExit(userInfo).calcInfo;
                        mMonthTotals.zeroHours = mMonthTotals.zeroHours.add(calcInfo.totalTime.zeroHours);
                        mMonthTotals.additionalHours = mMonthTotals.additionalHours.add(calcInfo.totalTime.additionalHours);
                        mMonthTotals.additional125Hours = mMonthTotals.additional125Hours.add(calcInfo.totalTime.additional125Hours);
                        mMonthTotals.additional150Hours = mMonthTotals.additional150Hours.add(calcInfo.totalTime.additional150Hours);
                        mMonthTotals.unpaidAbsence = mMonthTotals.unpaidAbsence.add(calcInfo.totalTime.unpaidAbsence);
                        mMonthTotals.globalAbsence = mMonthTotals.globalAbsence.add(calcInfo.totalTime.globalAbsence);
                        mMonthTotals.regularHours = mMonthTotals.regularHours.add(calcInfo.totalTime.total);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                update();
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

    private void update() {
        if(!mIsInitialized)
            return;
        updateLabels();
        updateVisibility();
    }


    private void updateLabels() {
        mLblTxtRegularHours.setText(mMonthTotals.regularHours.toString());
        mLblTxtZeroHours.setText(mMonthTotals.zeroHours.toString());
        mLblTxtAdditionalHours.setText(mMonthTotals.additionalHours.toString());
        mLblTxtAdditional125Hours.setText(mMonthTotals.additional125Hours.toString());
        mLblTxtAdditional150Hours.setText(mMonthTotals.additional150Hours.toString());
        mLblTxtGlobalAbsenceHours.setText(mMonthTotals.globalAbsence.toString());
        mLblTxtUnpaidAbsenceHours.setText(mMonthTotals.unpaidAbsence.toString());
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
    public void update(int month, int year, ArrayList<DailyReport> dailyReports) {
        mDailyReports = dailyReports;
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